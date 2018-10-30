package aco;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.math3.distribution.CauchyDistribution;

import dp2.Avaliador;
import dp2.D;
import dp2.Pattern;
import util.Const;
import util.PDF;
import util.RandomCollection;
import util.Time;

public class RULE_BUILDING {
	
	// Limpa os valores negativos e zeros
	private static double[] clearN(double[] arg) {
		boolean hasN = false;
		double bigN = 0.0;
		for (double d : arg) {
			if(d <= 0.0) {
				hasN = true;
				if(d < bigN) {
					bigN = d;
				}
			}
		}
		
		if(hasN) {
			for (int i = 0; i < arg.length; i++) {
				arg[i] += Math.abs(bigN) + 1;
			}
		}
		
		return arg;
	}
	
	private static double[] P(double[] trails, double[] weigths, int[] X, String tipoAvaliacao) {
		double[] P = new double[D.numeroItens];
		double[] Pup = new double[D.numeroItens];
		double Pdown = 0.0;
		
		// Construção do numerador da ista de probabilidades
		for(int i = 0; i < D.numeroItens; i++) {
			if(!D.itemQualidade.containsKey(i)) {
				HashSet<Integer> itens = new HashSet<Integer>(); itens.add(i);
				Pattern Pi = new Pattern(itens, tipoAvaliacao);
				D.itemQualidade.put(i, Pi.getQualidade());
			}
			
			Pup[i] = D.itemQualidade.get(i) * trails[i] * weigths[i];
		}
		
		Pup = clearN(Pup);
		
		boolean hasN = false;
		for (double i : Pup) {
			if(i<0.0) {
				hasN = true;
				break;
			}
		}
		
		// Construção do denominador da lista de probabilidades
		int i = 0;
		int attr = D.itemAtributo[0];
		for(int j = 0; j < D.numeroItens; j++) {
			if(D.itemAtributo[j] != attr) {
				attr = D.itemAtributo[j];
				i++;
			}
			int xi = X[i];
			Pdown += xi * Pup[j];
		}
		
		// Divisão final
		for(i = 0; i < D.numeroItens; i++) {
			P[i] = Pup[i] / Pdown;
		}
		
		return P;
	}
	
	public static boolean containsItem(HashSet<Integer> itens, Integer i) {
		boolean contains = false;
		for(Integer item : itens) {
			if(D.itemAtributo[item] == D.itemAtributo[i]) {
				contains = true;
			}
		}
		
		return contains;
	}
	
	public static Pattern Rt(double[] trails, double[] weigths, int[] X, int Min_cases_per_rule, String tipoAvaliacao, int b, List<Pattern> RuleList) {
		HashSet<Integer> itens = new HashSet<Integer>();
		double[] P = P(trails, weigths, X, tipoAvaliacao);
		RandomCollection<Integer> rc = new RandomCollection<>();
		rc.addAll(P, IntStream.rangeClosed(0, D.numeroItens-1).boxed().toArray(Integer[]::new));
		
		while(itens.size() < D.numeroAtributos) {
			Integer selected = null;
			
			if(PDF.sample() < 0) {
				selected = Const.random.nextInt(P.length);
				rc.delete(selected);
				
				if(SSDP_ACO.DEBUG) {
					System.out.println(SSDP_ACO.BLANK+" Added item " + selected + " randomly");
				}
			}else{
				selected = rc.next();
				if(SSDP_ACO.DEBUG) {
					System.out.println(SSDP_ACO.BLANK+" Added item " + selected + " by P rule");
				}
			}
			
			
			if(selected == null) {
				break;
			}
			
			if(!containsItem(itens, selected)) {
				HashSet<Integer> tmpItens = new HashSet<Integer>();
				itens.forEach(x->{tmpItens.add(x);}); tmpItens.add(selected);
				Pattern tmp = new Pattern(tmpItens, tipoAvaliacao);
				int coverage = tmp.getTP();
				if(coverage >= Min_cases_per_rule && SSDP_ACO.relevant(tmp, RuleList)) {
					itens.add(selected);
					X[D.itemAtributo[selected]] = 0;
				}
			}
		}
		
		Pattern Rt = new Pattern(itens, tipoAvaliacao);
		return Rt;
	}
	
}
