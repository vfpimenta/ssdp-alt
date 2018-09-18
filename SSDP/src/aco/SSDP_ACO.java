package aco;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.DoubleStream;

import dp2.D;
import dp2.Pattern;

public class SSDP_ACO {
	//public static int Max_uncovered_cases;
	public static int Min_cases_per_rule;
	public static int No_of_ants;
	public static int No_rules_converg;
	public static boolean DEBUG = false;
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
			
			// Initialize all trails with the same amount of pheromone;
			double[] trails = new double[D.numeroItens];
			Arrays.fill(trails, (double) 1/D.numeroItens);
			
			List<Pattern> R = new ArrayList<>();
			
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
				Pattern Rt = RULE_BUILDING.Rt(trails, X, Min_cases_per_rule, tipoAvaliacao);
				if(DEBUG) {
					System.out.println("[DEBUG] Built NEW rule: "+Rt.toString2());
				}
				
				// Prune rule Rt
				Rt = PRUNNING.prune(Rt, tipoAvaliacao);
				if(DEBUG) {
					System.out.println("[DEBUG] Prunned NEW rule into: "+Rt.toString2());
				}
				
				/*
				 Update the pheromone of all trails by increasing 
				 pheromone in the trail followed by AntT (pro-
				 portional to the quality of Rt) and decreasing
				 pheromone in the other trails (simulating
				 pheromone evaporation); 
				 */
				for(Integer i : Rt.getItens()) {
					trails[i] = trails[i] + trails[i] * Math.log(Rt.getQualidade());
				}
				
//				double sumTrails = DoubleStream.of(trails).sum();
//				for (int i = 0; i < trails.length; i++) {
//					trails[i] = trails[i] / sumTrails;
//				}
				
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
			
			// Choose the best rule Rbest among all rules Rt constructed by all the ants;
			Collections.sort(R);
			Pattern Rbest = R.get(0);
			DiscoveredRuleList.add(Rbest);
			
			if(DEBUG) {
				System.out.println("[DEBUG] Iteration finished, best rule selected:");
				System.out.println(BLANK+Rbest.toString2());
				
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
}
