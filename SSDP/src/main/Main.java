/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import dp2.Avaliador;
import dp2.D;
import dp2.Pattern;
import evolucionario.SSDP_MxC_Auto_3x3;
import util.Const;
import util.Time;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import aco.SSDP_ACO;

/**
 *
 * @author TARCISIO
 */
public class Main {
    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException, InterruptedException{
    	int[] ks = {5, 10, 20, 50};
    	int[] mcprs = {10, 15, 20};
    	int[] noas = {50, 75, 100};
    	/*String[] databases = {"alon-pn-freq-2.CSV","christensen-pn-freq-2.CSV",
    			"yeoh-pn-freq-2.CSV","burczynski-pn-freq-2.CSV",
    			"gravier-pn-freq-2.CSV","zsun-pn-freq-2.CSV",
    			"chiaretti-pn-freq-2.CSV","nakayama-pn-freq-2.CSV",
    			"chin-pn-freq-2.CSV","tian-pn-freq-2.CSV"};*/
    	String[] databases = {"audiology-pn.CSV","nursery-pn.CSV","breast-cancer-pn.CSV",
    			"postoperative-patient-data-pn.CSV","bridges-version2-pn.CSV","primary-tumor-pn.CSV",
    			"shuttle-landing-control-pn.CSV","car-pn.CSV","solar-flare-2-pn.CSV","soybean-pn.CSV",
    			"kr-vs-kp-pn.CSV","spect-test-pn.CSV","lung-cancer-pn.CSV","splice-pn.CSV",
    			"molecular-biology-promoters-pn.CSV","tic-tac-toe-pn.CSV","monks-problems-1-train-pn.CSV",
    			"trains-pn.CSV","mushroom-pn.CSV","vote-pn.CSV"
    			};
    	
    	SSDP_ACO.No_rules_converg = 10;
    	SSDP_ACO.DEBUG = false;
    	SSDP_ACO.INFO = false;
    	SSDP_ACO.TRACK = false;
    	
//    	for (int mcpr : mcprs) {
//			for (int noa : noas) {
//				for (String string : databases) {
//					int k = ks[0];
//					System.out.println("Starting test (k:"+k+",db:"+string+",mcpr:"+mcpr+",noa:"+noa+")");
//					SSDP_ACO.Min_cases_per_rule = mcpr;
//					SSDP_ACO.No_of_ants = noa;
//					test(k, string);	
//				}
//			}
//		}
    	
    	SSDP_ACO.Min_cases_per_rule = 1;
		SSDP_ACO.No_of_ants = 50;
		SSDP_ACO.No_of_batches = 100;
		SSDP_ACO.Max_stall = 100;
		for (String string : databases) {
			System.out.println("Starting test (db:"+string+")");
			test(5, string);
		}
    	
//    	for (Long seed : Const.SEEDS) {
//    		Const.random = new Random(seed);
//    		test(5, databases[0], seed);
//    	}
    	
//    	test(5, "audiology-pn.CSV");
    }
    
    public static void test(int k, String database) throws FileNotFoundException, UnsupportedEncodingException {
    	//====================================================================
        //== CONFIGURATION ===================================================
        //====================================================================
        //CSV database path
        String caminho = "/home/victor/dev/ssdp/data/Bases_UCI_20/"; 
        //String caminho = "/home/victor/dev/ssdp/data/microarray_discretizedBYwidth/";
        //String nomeBase = "audiology_pn.CSV";
        //String nomeBase = "amazon_cells_labelled.csv";
        String nomeBase = database;
        String caminhoBase = caminho + nomeBase;
       
        D.SEPARADOR = ","; //separator database
        Const.random = new Random(Const.SEEDS[0]); //Seed - 30 options
        
        //Parameters of the algorithm
        //int k = 10; //number of DPs
        String tipoAvaliacao = Avaliador.TIPO_WRACC; //Fitness
        //String tipoAvaliacao = Avaliador.TIPO_QG; //Fitness
        //String tipoAvaliacao = Avaliador.TIPO_SUB; //Fitness
        D.valorAlvo = "p"; //target value of dataset
        
        //min_similarity
        double similaridade = 0.90;
        //Similarity function
        Pattern.medidaSimilaridade = Const.SIMILARIDADE_JACCARD; //similarity 
        //====================================================================
        //= END ==============================================================
        //====================================================================
        
        
        
        D.CarregarArquivo(caminhoBase, D.TIPO_CSV); //Loading database         
        Pattern.numeroIndividuosGerados = 0; //Initializing count of generated individuals by SSDP
                            
        //Rodando SSDP
        long t0 = System.currentTimeMillis(); //Initial time
        //Pattern[] p = SSDP_MxC_Auto_3x3.run(k, tipoAvaliacao); //run SSDP
        Pattern[] p = SSDP_ACO.run(k, tipoAvaliacao, similaridade); //run SSDP
        double r = Avaliador.CR(p);
        double h = Avaliador.H(p);
        double tempo = (System.currentTimeMillis() - t0)/1000.0; //time
        
        //Creating output file
        PrintWriter writer = new PrintWriter("/home/victor/dev/ssdp/SSDP/pastas/testes/" + nomeBase + "-k" + k + "-No_of_ants" + SSDP_ACO.No_of_ants + "-Min_cases_per_rule" + SSDP_ACO.Min_cases_per_rule + ".out", "UTF-8");
        
        //Informations about top-k DPs:  
        writer.println("### Base:" + D.nomeBase); //database name
        writer.println("Average " + tipoAvaliacao + ": " + Avaliador.avaliarMedia(p, k));
        writer.println("Average " + Avaliador.TIPO_QG + ": " + Avaliador.avaliarMedia(p, k, Avaliador.TIPO_QG));
        writer.println("Time(s): " + tempo);
        writer.println("Average size: " + Avaliador.avaliarMediaDimensoes(p, k));        
        writer.println("Coverage of all k DPs in relation to D+: " + Avaliador.coberturaPositivo(p, k)*100 + "%");
        writer.println("Number of individuals generated: " + Pattern.numeroIndividuosGerados);
        writer.println("TP: " + Avaliador.avgTP(p, k));
        writer.println("FP: " + Avaliador.avgFP(p, k));
        writer.println("Chi_Quad: " + Avaliador.avgCQ(p, k));
        writer.println("p_value: " + Avaliador.avgP(p, k));
        writer.println("conf: " + Avaliador.avgconf(p, k));
        writer.println("cov: " + Avaliador.avgcov(p, k));
        writer.println("Lift: " + Avaliador.avgLift(p, k));
        writer.println("supp: " + Avaliador.avgsupp(p, k));
        writer.println("suppP: " + Avaliador.avgsuppP(p, k));
        writer.println("suppN: " + Avaliador.avgsuppN(p, k));
        writer.println("DiffSupp: " + (Avaliador.avgsuppP(p, k) - Avaliador.avgsuppN(p, k)));
        writer.println("\n### Top-"+k+" DPs:");
        Avaliador.imprimirRegras(p, k, writer); 
        
        writer.close();
    }
}
