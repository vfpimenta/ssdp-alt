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
import util.NumberUtils;
import util.Time;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    	String[] models = {"MESDIF-SD", "NMEEF-SD", "SDIGA-SD"};
    	
    	SSDP_ACO.No_rules_converg = 10;
    	SSDP_ACO.DEBUG = false;
    	SSDP_ACO.INFO = false;
    	SSDP_ACO.TRACK = false;
    	
    	SSDP_ACO.Min_cases_per_rule = 1;
		SSDP_ACO.No_of_ants = 50;
		SSDP_ACO.No_of_batches = 100;
		SSDP_ACO.Max_stall = 100;
//		for (String string : databases) {
//			for (String model : models) {
//				System.out.println("Starting test (db:"+string+", model:"+model+")");
//				try {
//					test(5, string, model);
//				}catch(OutOfMemoryError e) {
//					e.printStackTrace();
//				}
//
//			}
//		}
		
		for (String string : databases) {
			System.out.println("Starting test (db:"+string+")");
			test(5, string, null);
		}
    	
//    	test(5, "lung-cancer-pn.CSV", "NMEEF-SD");
    }
    
    public static void test(int k, String database, String modelName) throws FileNotFoundException, UnsupportedEncodingException {
    	//====================================================================
        //== CONFIGURATION ===================================================
        //====================================================================
        //CSV database path
		String caminho = "C:\\Users\\Victor\\Documents\\GitHub\\ssdp-alt\\data\\Bases_UCI_20\\";
        //String caminho = "/home/victor/dev/ssdp/data/Bases_UCI_20/";
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
        //Pattern[] p = SSDP_ACO.run(k, tipoAvaliacao, similaridade); //run ACODP
        //Pattern[] p = loadFromFileKeel(modelName, database, tipoAvaliacao);
		Pattern[] p = loadFromFileDSSD(database, tipoAvaliacao, k);
		//Pattern[] p = loadFromFileMCTS4DM(database, tipoAvaliacao, k);
        k = p.length;
        double r = Avaliador.CR(p);
        double h = Avaliador.H(p);
        double tempo = (System.currentTimeMillis() - t0)/1000.0; //time
        
        //Creating output file
		PrintWriter writer = new PrintWriter("C:\\Users\\Victor\\Documents\\GitHub\\ssdp-alt\\SSDP\\pastas\\testes\\" + nomeBase + "-k" + k + ".out", "UTF-8");
        //PrintWriter writer = new PrintWriter("/home/victor/dev/ssdp/SSDP/pastas/testes/" + nomeBase + "-k" + k + ".out", "UTF-8");
		//PrintWriter writer = new PrintWriter("/home/victor/dev/ssdp/SSDP/pastas/testes/" + modelName + "-" + nomeBase + "-k" + k + ".out", "UTF-8");
        
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
        writer.println("Similarity: " + (Avaliador.avgSimilaridade(p, k, Pattern.medidaSimilaridade)));
        writer.println("CR: " + r);
        writer.println("H: " + h);
        writer.println("\n### Top-"+k+" DPs:");
        Avaliador.imprimirRegras(p, k, writer); 
        
        writer.close();
    }

    private static Pattern[] loadFromFileMCTS4DM(String database, String tipoAvaliacao, int k) {
		List<Pattern> patterns = new ArrayList<>();

		database = database.substring(0,database.lastIndexOf('-'));
		String path = "C:\\Users\\Victor\\Documents\\GitHub\\MCTS4DM\\results\\"+database;
		String file = path+"\\"+new File(path).listFiles()[0].getName()+"\\result.log";

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			HashSet<Integer> itens = new HashSet<>();
			while ((line = br.readLine()) != null && patterns.size() < k) {
				if(line.contains("[")) {
					String[] itemDescriptions = line.split("\t")[0].split(",");
					for (String itemDescription: itemDescriptions) {
						Integer item = itemFromDescription(itemDescription.trim().replace("[", "").replace("]",""));
						itens.add(item);
					}
					patterns.add(new Pattern(itens, tipoAvaliacao));
					itens = new HashSet<>();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return patterns.toArray(new Pattern[0]);
	}

    private static Pattern[] loadFromFileDSSD(String database, String tipoAvaliacao, int k) {
		List<Pattern> patterns = new ArrayList<>();

		database = database.split("-")[0];
		String file = "";
		String path = "C:\\Users\\Victor\\Downloads\\dssdSrc-120501-org\\dssdSrc\\trunk\\xps\\dssd";
		File[] dirs = new File(path).listFiles();
		for (File dir : dirs){
			if(dir.getName().contains(database)){
				String[] segments = dir.getName().split("-");
				String hash = segments[segments.length-1];
				file = path+"\\"+dir.getName()+"\\"+"stats3-"+hash+".csv";
			}
		}
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			HashSet<Integer> itens = new HashSet<>();
			while ((line = br.readLine()) != null && patterns.size() < k) {
				String[] columns = line.split(";");
				if(columns.length > 0) {
					if(NumberUtils.isNumeric(columns[0]) && !columns[4].contains("!") && !columns[4].contains(">") && !columns[4].contains("<")) {
						String[] itemDescriptions = columns[4].split("&&");
						for (String itemDescription: itemDescriptions) {
							Integer item = itemFromDescription(itemDescription.trim());
							itens.add(item);
						}
						patterns.add(new Pattern(itens, tipoAvaliacao));
						itens = new HashSet<>();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return patterns.toArray(new Pattern[0]);
	}

	private static Pattern[] loadFromFileKeel(String modelName, String database, String tipoAvaliacao) {
		List<Pattern> patterns = new ArrayList<>();
		
		//String file = "/home/victor/dev/ssdp/experiments/final_0/results/"+modelName+"."+database.split("\\.")[0]+"/result0e0.txt";
		//String file = "/home/victor/dev/ssdp/experiments/final_1/"+modelName.split("-")[0]+"/results/"+modelName+"."+database.split("\\.")[0]+"/result0e0.txt";
		String file = "C:\\Users\\Victor\\Documents\\GitHub\\ssdp-alt\\experiments\\final_1\\"+modelName.split("-")[0]+"\\results\\"+modelName+"."+database.split("\\.")[0]+"\\result0e0.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    HashSet<Integer> itens = new HashSet<>();
		    while ((line = br.readLine()) != null) {
		       if(line.contains("Variable")) {
		    	   String itemDescription = line.trim().substring(line.trim().indexOf(" ")).trim();
		    	   Integer item = itemFromDescription(itemDescription);
		    	   itens.add(item);
		       }else if(line.contains("Consecuent")) {
		    	   if(line.trim().split(":")[1].contains("p")){
		    		   patterns.add(new Pattern(itens, tipoAvaliacao));
		    		   itens = new HashSet<>();
		    	   }else {
		    		   itens = new HashSet<>();
		    	   }
		       }
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return patterns.toArray(new Pattern[0]);
	}

	private static Integer itemFromDescription(String itemDescription) {
        for(int j = 0; j <= D.numeroItens; j++){
            //String comp = D.itemAtributoStr[j] + " = '" + D.itemValorStr[j] + "'";
			String comp = D.itemAtributoStr[j] + " = " + D.itemValorStr[j] + "";
            if(itemDescription.equals(comp)) {
            	return j;
            }
        }
        return -1;
	}
}
