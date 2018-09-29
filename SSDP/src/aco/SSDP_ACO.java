package aco;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.DoubleStream;

import org.apache.commons.math3.distribution.CauchyDistribution;

import dp2.D;
import dp2.Pattern;

public class SSDP_ACO {
	//public static int Max_uncovered_cases;
	public static int Min_cases_per_rule;
	public static int No_of_ants;
	public static int No_rules_converg;
	public static int No_of_batches;
	
	public static boolean DEBUG = false;
	public static boolean INFO = false;
	public static String BLANK = "    ";
	
	public static int coverage() {
		return 0;
	}
	
	public static Pattern[] run(int k, String tipoAvaliacao) throws FileNotFoundException{
		List<Pattern> DiscoveredRuleList = new ArrayList<>();
		D.PadroesExcluidos = new HashSet<>();
		D.itemQualidade = new HashMap<>();
		
		//while(D.numeroExemplos > Max_uncovered_cases && D.numeroExemplosNegativo > 0 && D.numeroExemplosPositivo > 0) {
		while(DiscoveredRuleList.size() < k) {
			int t = 1; /* ant index */
			int j = 1; /* convergence test index */
			int b = 1; /* batch num */
			
			// Initialize all trails with the same amount of pheromone;
			double[] trails = new double[D.numeroItens];
			Arrays.fill(trails, (double) 1/D.numeroItens);
			
			List<Pattern> R = new ArrayList<>();
			
			while (b < No_of_batches) {
				t = 1; j = 1;
				if(DEBUG) {
					System.out.println("[DEBUG] Starting batch "+b);
				}
				
				do {
					if(DEBUG) {
						System.out.println("[DEBUG] Starting do-while for ant "+t);
					}
					// Initialize array of attribute usage
					int[] X = new int[D.numeroAtributos];
					Arrays.fill(X, 1);
					
					/*
					AntT starts with an empty rule and incrementally
					constructs a classification rule Rt by adding
					one term at a time to the current rule;
					 */
					Pattern Rt = RULE_BUILDING.Rt(trails, X, Min_cases_per_rule, tipoAvaliacao, b);
					if(DEBUG) {
						System.out.println("[DEBUG] Built NEW rule: "+Rt.toString2());
					}
					
					// Prune rule Rt
					CauchyDistribution cd = new CauchyDistribution(b - 5.5, 1);
					if(cd.sample() > 0) {
						Rt = PRUNNING.prune(Rt, tipoAvaliacao, DiscoveredRuleList, b);
						if(DEBUG) {
							System.out.println("[DEBUG] Prunned NEW rule into: "+Rt.toString2());
						}
					} else if (DEBUG) {
						System.out.println("[DEBUG] Decided to NOT prune rule");
					}
					
					
					/*
					 Update the pheromone of all trails by increasing 
					 pheromone in the trail followed by AntT (pro-
					 portional to the quality of Rt) and decreasing
					 pheromone in the other trails (simulating
					 pheromone evaporation); 
					 */
					for(Integer i : Rt.getItens()) {
						trails[i] = trails[i] + trails[i] * Rt.getQualidade();
					}
					
//					double sumTrails = DoubleStream.of(trails).sum();
//					for (int i = 0; i < trails.length; i++) {
//						trails[i] = trails[i] / sumTrails;
//					}
					
					if(DEBUG) {
						System.out.println("[DEBUG] Trails updated");
						System.out.println(BLANK+Arrays.toString(trails));
					}
					
					/* update convergence rule */
					if(t > 1 && Rt.getItens().containsAll(R.get(t-2).getItens()))
						j = j + 1;
					else
						j = 1;
					
					t = t + 1;
					R.add(Rt);
					if(DEBUG) {
						System.out.println("[DEBUG] Current rule list:");
						for (int i = 0; i < R.size(); i++) {
							System.out.println(BLANK+R.get(i).toString2());
						}
					}
				} while(t < No_of_ants && j < No_rules_converg);
				
				b++;
			}
			
			// Choose the best rule Rbest among all rules Rt constructed by all the ants;
			Collections.sort(R);
			Pattern Rbest = null;
			Pattern Rbackup = null;
			for (Pattern r : R) {
				if(relevant(r, DiscoveredRuleList)) {
					Rbest = r;
					break;
				} else if(Rbackup == null && different(r, DiscoveredRuleList)) {
					Rbackup = r;
				}
			}
			
			if(Rbest != null) {
				DiscoveredRuleList.add(Rbest);
			}else if(Rbackup != null){
				DiscoveredRuleList.add(Rbackup);
			}
			
			if(DEBUG || INFO) {
				System.out.println("[DEBUG] Iteration finished, best rule selected:");
				if(Rbest != null) {
					System.out.println(BLANK+Rbest.toString2());
				}else if(Rbackup != null){
					System.out.println(BLANK+Rbackup.toString2());
				}
				
				if(t == No_of_ants) {
					System.out.println("[DEBUG] Finished by reaching max number of iterations");
				}else if(j == No_rules_converg) {
					System.out.println("[DEBUG] Finished by convergence (ants="+t+")");
				}
				
				System.out.println("[DEBUG] Current DRL:");
				for (int i = 0; i < DiscoveredRuleList.size(); i++) {
					System.out.println(BLANK+DiscoveredRuleList.get(i).toString2());
				}
			}
			
			// TrainingSet = TrainingSet-{set of cases correctly covered by Rbest};
//			if(D.PadroesExcluidos == null) {
//				D.PadroesExcluidos = new HashSet<Pattern>(DiscoveredRuleList);
//			} else {
//				D.PadroesExcluidos.addAll(DiscoveredRuleList);
//			}
			//D.RecarregaArquivo();
		}
		
		return DiscoveredRuleList.toArray(new Pattern[0]);
	}
	
	public static boolean relevant(Pattern p, List<Pattern> Pk){
        for(int i = 0; i  < Pk.size(); i++){
            if(Pk.get(i).sobrescreve(p) != -1){
                return false;
            }
        }
        return true;
    }
	
	public static boolean different(Pattern p, List<Pattern> Pk) {
		for(int i = 0; i  < Pk.size(); i++){
            if(p.equals(Pk.get(i))){
                return false;
            }
        }
        return true;
	}
}
