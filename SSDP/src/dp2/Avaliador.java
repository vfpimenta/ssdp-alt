/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp2;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.apache.commons.math3.util.Combinations;

import util.Binary;
import util.Const;


/**
 *
 * @author Marianna
 */
public class Avaliador {
    
    public static final String TIPO_QG = "Qg";
    public static final String TIPO_SUB = "Sub";
    public static final String TIPO_WRACC = "WRAcc";
    
    public static double similaridade(Pattern p1, Pattern p2, String metricaSimilaridade){
        //REF: A Survey of Binary Similarity and Distance Measures (http://www.iiisci.org/Journal/CV$/sci/pdfs/GS315JG.pdf)
        double onlyA = 0.0;
        double onlyB = 0.0;
        double bothAB = 0.0;
        double neitherAB = 0.0;
        double Acount = 0.0;
        double Bcount =0.0;
        
        //POSITIVO
        boolean[] A = p1.getVrP();
        boolean[] B = p2.getVrP();
        for(int i = 0; i < A.length; i++){
            if(A[i]){
                Acount++;
            }
            if(B[i]){
                Bcount++;
            }
            if(A[i] && !B[i]){
                onlyA++;
            }
            if(B[i] && !A[i]){
                onlyB++;
            }
            if(A[i] && B[i]){
                bothAB++;
            }
            if(!A[i] && !B[i]){
                neitherAB++;                        
            }
        }
        
        
        //NEGATIVO
        A = p1.getVrN();
        B = p2.getVrN();
        for(int i = 0; i < A.length; i++){
            if(A[i]){
                Acount++;
            }
            if(B[i]){
                Bcount++;
            }
            if(A[i] && !B[i]){
                onlyA++;
            }
            if(B[i] && !A[i]){
                onlyB++;
            }
            if(A[i] && B[i]){
                bothAB++;
            }
            if(!A[i] && !B[i]){
                neitherAB++;                        
            }
        }
        
        double valor = 0;
        switch(metricaSimilaridade){
            case Const.SIMILARIDADE_JACCARD://
                valor = bothAB/(onlyA + onlyB + bothAB);
                break;            
            case Const.SIMILARIDADE_SOKAL_MICHENER://
                valor = (bothAB + neitherAB) / (onlyA + onlyB + bothAB + neitherAB);
                break;            
        }
        
        return valor;
        
    }
    
    //Avaliação individual   
    public static double avaliar(int TP, int FP, String tipo){
        double qualidade = 0.0;
        
        switch(tipo){
            case Avaliador.TIPO_QG:
                qualidade = Avaliador.Qg(TP, FP);
                break;
            case Avaliador.TIPO_SUB:
                qualidade = Avaliador.sub(TP, FP);
                break;
            case Avaliador.TIPO_WRACC:
                qualidade = Avaliador.WRAcc(TP, FP);
                break;          
        }      
        return qualidade;
    }   
    
    private static double WRAcc(int TP, int FP){
        if(TP==0){
            return 0.0;
        }
        double sup = (double)(TP+FP) / (double)D.numeroExemplos;
        double conf = (double)TP / (double)(TP+FP);
        double confD = (double)D.numeroExemplosPositivo / (double)D.numeroExemplos;
        double wracc = sup * ( conf  - confD);
                       
        return wracc;
    }
    
    private static double Qg(int TP, int FP){
        double qg = (double)TP/(double)(FP+1);
        return qg;
    }
    
    private static double sub(int TP, int FP){
        double sub = TP-FP;
        return sub;
    }
    
    public static double H(Pattern[] subgroupSet) {
    	double summation = 0.0;
    	int numRows = (int)Math.pow(2, subgroupSet.length);
    	for (int row = 0; row < numRows; row++){
            boolean[] B = Binary.list(subgroupSet.length, row);
            double p = fraction(subgroupSet, B);
            if(p == 0.0) p = 1.0;
            summation += p * Math.log(p);
        }
    	
    	return -summation;
    }
    
    private static double fraction(Pattern[] subgroupSet, boolean[] b) {
    	int fraction = 0;
    	for(int t = 0; t < D.numeroExemplos; t++) {
    		int match = 0;
    		for(int i = 0; i < b.length; i++) {
				Pattern g = subgroupSet[i];
				if(t < D.numeroExemplosPositivo) {
					if (patternContemplaExemplo(g.getItens(), D.Dp[t]) != b[i]) {
						break;
					}
					match++;
				} else {
					if (patternContemplaExemplo(g.getItens(), D.Dn[t-D.numeroExemplosPositivo]) != b[i]) {
						break;
					}
					match++;
				}
    		}
    		
    		fraction += match == b.length ? 1 : 0;
    	}
    	
    	return (double) fraction/D.numeroExemplos;
	}

	public static double CR(Pattern[] subgroupSet) {
    	double summation = 0.0;
    	for(int t = 0; t < D.numeroExemplos; t++) {
    		double c_ = expectedCoverCount(subgroupSet);
    		summation += Math.abs(coverCount(t, subgroupSet) - c_) / c_;
    	}
    	
    	return (double) 1/D.numeroExemplos * summation;
    }
    
    private static double expectedCoverCount(Pattern[] subgroupSet) {
    	int summation = 0;
    	for(int t = 0; t < D.numeroExemplos; t++) {
    		summation += coverCount(t, subgroupSet);
    	} 
		return (double) 1/D.numeroExemplos * summation;
	}

	private static int coverCount(int t, Pattern[]subgroupSet) {
		int coverCount = 0;
		for(Pattern g : subgroupSet) {
			if(t < D.numeroExemplosPositivo) {
				coverCount += patternContemplaExemplo(g.getItens(), D.Dp[t]) ? 1 : 0;
			}else {
				coverCount += patternContemplaExemplo(g.getItens(), D.Dn[t-D.numeroExemplosPositivo]) ? 1 : 0;
			}
		}
		
		return coverCount;
	}

	public static double chi_quad(int TP, int FP){
        //Só é preciso isso para calcular via função pronta!
        long[][] n = new long[2][2];
        n[0][0] = TP;
        n[1][0] = D.numeroExemplosPositivo - n[0][0];
        n[0][1] = FP;
        n[1][1] = D.numeroExemplosNegativo - n[0][1];
        

        ChiSquareTest chiTest = new ChiSquareTest();
        double chi_quad = chiTest.chiSquare(n);
        //System.out.println("Chi_quad: " + chi + "/" + chi_quad);
        //System.out.println("chi_quad: " + chi_quad);
               
        return chi_quad;
    }
    
    public static double p_value(int TP, int FP){
        //Só é preciso isso para calcular via função pronta!
        long[][] n = new long[2][2];
        n[0][0] = TP;
        n[1][0] = D.numeroExemplosPositivo - n[0][0];
        n[0][1] = FP;
        n[1][1] = D.numeroExemplosNegativo - n[0][1];
        ChiSquareTest chiTest = new ChiSquareTest();
        //Returns the observed significance level, or p-value, associated with a chi-square test of independence based on the input counts array, viewed as a two-way table.
        double p_value = chiTest.chiSquareTest(n);
        //System.out.println("pvalue: " + p_value);
        return p_value;        
    }
    
    public static double supp(Pattern p){
        double TP = p.getTP();
        double numeroExemplos = D.numeroExemplos;
        
        double valor = TP / numeroExemplos;
        return valor;
    }
    
    public static double suppPositivo(Pattern p){
        double TP = p.getTP();
        double numeroExemplosPositivo = D.numeroExemplosPositivo;
        
        double valor = TP / numeroExemplosPositivo;
        return valor;
    }
    
    public static double suppNegativo(Pattern p){
        double FP = p.getFP();
        double numeroExemplosNegativo = D.numeroExemplosNegativo;
        
        double valor = FP / numeroExemplosNegativo;
        return valor;
    }
    
    public static double cov(Pattern p){
        double TP = p.getTP();
        double FP = p.getFP();
        double numeroExemplos = D.numeroExemplos;
          
        double valor = (TP + FP) / numeroExemplos;
        
        return valor;
    }
    
    public static double lift(Pattern p){
        double TP = p.getTP();
        double FP = p.getFP();
        double supCond = (double)(TP + FP) / (double)D.numeroExemplos; //Suporte antecedente: número de exemplos da regra sobre o total |D|
        double supTarget = (double)D.numeroExemplosPositivo / (double)D.numeroExemplos;  //Suporte consequente: número de exemplos com rótulo em relação ao total |Dp| / |D|
        double supDP = supp(p); //Suporte antecedente e consequente: count()
        
        double valor = supDP / (supCond * supTarget);
        
        return valor;
    }
       
    public static int TP(boolean[] vrP){
        int TP = 0;
        for(int i = 0; i < vrP.length; i++){            
            if(vrP[i]){
                TP++;
            }                       
        }        
        return TP;
    }
    
    public static int FP(boolean[] vrN){
        int FP = 0;
        for(int i = 0; i < vrN.length; i++){            
            if(vrN[i]){
                FP++;
            }                       
        }        
        return FP;
    }
    
    

    //Vetor resultante
    public static boolean[] vetorResultantePositivo(HashSet<Integer> itens){
        boolean[] vetorResultantePositivo = new boolean[D.numeroExemplosPositivo];
        
        for(int i = 0; i < D.numeroExemplosPositivo; i++){            
            vetorResultantePositivo[i] = Avaliador.patternContemplaExemplo(itens, D.Dp[i]);                        
            if(vetorResultantePositivo[i]){
                Pattern.vrPCount[i]++;
            }
        }      
        
        return vetorResultantePositivo;
    }
    
    public static boolean[] vetorResultanteNegativo(HashSet<Integer> itens){
        boolean[] vetorResultanteNegativo = new boolean[D.numeroExemplosNegativo];
        
        for(int i = 0; i < D.numeroExemplosNegativo; i++){            
            vetorResultanteNegativo[i] = Avaliador.patternContemplaExemplo(itens, D.Dn[i]);            
            if(vetorResultanteNegativo[i]){
                Pattern.vrNCount[i]++;
            }
        }      
        
        return vetorResultanteNegativo;
    }
    
    private static boolean patternContemplaExemplo(HashSet<Integer> itens, int[] exemplo){
        Iterator iterator = itens.iterator();
        while(iterator.hasNext()){
            int item = (int)iterator.next();
            int itemAtributo = D.itemAtributo[item];
            int itemValor = D.itemValor[item];
            if(exemplo[itemAtributo] != itemValor){
                return false;                    
            } 
        }       
        return true; 
    }
    
    public static boolean patternContemplaExemplo(HashSet<Integer> itens, String[] exemplo){
        Iterator iterator = itens.iterator();
        while(iterator.hasNext()){
            int item = (int)iterator.next();
            int itemAtributo = D.itemAtributo[item];
            String itemValor = D.itemValorStr[item];
            if(!exemplo[itemAtributo].equals(itemValor)){
                return false;                    
            } 
        }       
        return true; 
    }
    
    //Valor médio de qualidade
    public static double avaliarMedia(Pattern[] p, int k){
        double total = 0.0;
        int i = 0;
        for(; i < k; i++){
            total += p[i].getQualidade();
        }
        return total/(double)i;
    }
    
    //Valor médio de qualidade
    public static double avaliarMedia(Pattern[] p, int k, String tipoAvaliacao){
        double total = 0.0;
        int i = 0;
        for(; i < k; i++){
            total += avaliar(p[i].getTP(), p[i].getFP(), tipoAvaliacao);
        }
        return total/(double)i;
    }
    
    //Valor médio de dimensões
    public static double avaliarMediaDimensoes(Pattern[] p, int k){
        int total = 0;
        int i = 0;
        for(; i < k; i++){
            total += p[i].getItens().size();
        }
        return (double)total/(double)i;
    }
    
    public static double coberturaPositivo(Pattern[] p, int k){
        double coberturaP = 0.0;
        boolean[] vrpGrupo = new boolean[D.numeroExemplosPositivo];
        
        for(int i = 0; i < k; i++){
            boolean[] vrpItem = p[i].getVrP();
            for(int j = 0; j < vrpItem.length; j++){
                if(vrpItem[j]){
                    vrpGrupo[j] = true;
                }
            }
        }
        
        for(int i = 0; i < vrpGrupo.length; i++){
            if(vrpGrupo[i]){
                coberturaP = coberturaP + 1;
            }
        }
        //System.out.println("Numero P: " + coberturaP);
        coberturaP = coberturaP/(double)vrpGrupo.length;
        return coberturaP;
    }
    
    public static double avgSimilaridade(Pattern[] p, int k, String metricaSimilaridade) {
    	if(k <= 1) return 0.0;
    		
    	Iterator<int[]> tuples = new Combinations(k, 2).iterator();
    	double sim = 0.0;
    	while(tuples.hasNext()) {
    		int[] tuple = tuples.next();
    		sim += similaridade(p[tuple[0]], p[tuple[1]], metricaSimilaridade);
    	}
    	
    	return sim/k;
    }
    
    public static double avgTP(Pattern[] p, int k) {
    	int total = 0;
        int i = 0;
        for(; i < k; i++){
            total += p[i].getTP();
        }
        return (double)total/(double)i;
    }
    
    public static double avgFP(Pattern[] p, int k) {
    	int total = 0;
        int i = 0;
        for(; i < k; i++){
            total += p[i].getFP();
        }
        return (double)total/(double)i;
    }
    
    public static double avgCQ(Pattern[] p, int k) {
    	double total = 0;
        int i = 0;
        for(; i < k; i++){
            total += chi_quad(p[i].getTP(), p[i].getFP());
        }
        return (double)total/(double)i;
    }
    
    public static double avgP(Pattern[] p, int k) {
    	double total = 0;
        int i = 0;
        for(; i < k; i++){
            total += p_value(p[i].getTP(), p[i].getFP());
        }
        return (double)total/(double)i;
    }
    
    public static double avgconf(Pattern[] p, int k) {
    	double total = 0;
        int i = 0;
        for(; i < k; i++){
        	double conf = (double)p[i].getTP() / (double)(p[i].getTP()+p[i].getFP());
            total += conf;
        }
        return (double)total/(double)i;
    }
    
    public static double avgcov(Pattern[] p, int k) {
    	double total = 0;
        int i = 0;
        for(; i < k; i++){
        	double cov = cov(p[i]);
            total += cov;
        }
        return (double)total/(double)i;
    }
    
    public static double avgLift(Pattern[] p, int k) {
    	double total = 0;
        int i = 0;
        for(; i < k; i++){
        	double lift = lift(p[i]);
            total += lift;
        }
        return (double)total/(double)i;
    }
    
    public static double avgsupp(Pattern[] p, int k) {
    	double total = 0;
        int i = 0;
        for(; i < k; i++){
        	double supp = supp(p[i]);
            total += supp;
        }
        return (double)total/(double)i;
    }
    
    public static double avgsuppP(Pattern[] p, int k) {
    	double total = 0;
        int i = 0;
        for(; i < k; i++){
        	double suppP = suppPositivo(p[i]);
            total += suppP;
        }
        return (double)total/(double)i;
    }
    
    public static double avgsuppN(Pattern[] p, int k) {
    	double total = 0;
        int i = 0;
        for(; i < k; i++){
        	double suppN = suppNegativo(p[i]);
            total += suppN;
        }
        return (double)total/(double)i;
    }
    
    public static void imprimir(Pattern[] p, int kPrimeiros){
        for(int i = 0; i < kPrimeiros; i++){
            System.out.println(p[i].toString());        
        }
    }
    
    //Imprime regras em texto
    public static void imprimirRegras(Pattern[] p, int kPrimeiros, PrintWriter writer){
    	Arrays.sort(p);
        for(int i = 0; i < kPrimeiros; i++){
        	if (writer==null) {
        		System.out.println(p[i].toString2());
        	} else {
        		writer.println(p[i].toString2());
        	}
                    
        }
    }
    
    public static void imprimirDimensaoQuantidade(Pattern[] p, int kPrimeiros, int dDimensoes){
        int[] dimensaoQuantidade = new int[dDimensoes]; //Até dimensão 10
        for(int i = 0; i < kPrimeiros; i++){
            int dimensao = p[i].getItens().size();
            dimensaoQuantidade[dimensao]++;
        }
               
        for(int i = 0; i < dDimensoes; i++){
            System.out.println(/*"D" + i + ":" +*/ dimensaoQuantidade[i]/* + ", "*/);
        }
        
        System.out.println();
    }

    //Compactar dados: foco em tirar redundância
    public static Pattern[] comprimir(Pattern[] pOrdenado){
        
        int numeroSinonimos = 0;
        int numeroSub = 0;
        for(int i = 0; i < pOrdenado.length; i++){
            Pattern p = pOrdenado[i];
            if(p == null){
                    continue;
            }
            for(int j = i+1; j < pOrdenado.length; j++){
            //System.out.println("i:" + i + ",j:" + j);
                if(pOrdenado[j] == null){
                    continue;
                }
                int resultadoSobrescreve = p.sobrescreve(pOrdenado[j]);
                if(resultadoSobrescreve == -1){
                
                }else if(resultadoSobrescreve == 1){
                    p.addSub(pOrdenado[j]);
                    numeroSub++;                    
                    System.out.println("Sub:" + i + "/" + j);
                    //System.out.println(p.toString());
                    //System.out.println(pOrdenado[j].toString());
                    
                    pOrdenado[j] = null;
                }else{
                    p.addSinonimo(pOrdenado[j]);
                    numeroSinonimos++;
                    
                    System.out.println("Sin:" + i + "/" + j);
                    System.out.println(p.toString());
                    System.out.println(pOrdenado[j].toString());
                    pOrdenado[j] = null;
                }
            }
        }
        
        Pattern[] pComprimido = new Pattern[pOrdenado.length - numeroSinonimos - numeroSub];
        int indice = 0;
        for(int i = 0; i < pOrdenado.length; i++){
            if(pOrdenado[i] != null){
                pComprimido[indice++] = pOrdenado[i];
            }
        }
        System.out.println("\n\nSinonimos: " + numeroSinonimos + ", Sub: " + numeroSub);
        return pComprimido;
    }

    
}
