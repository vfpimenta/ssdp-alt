package aco;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.math3.distribution.CauchyDistribution;

import dp2.Avaliador;
import dp2.Pattern;

public class PRUNNING {
	public static Pattern prune(Pattern Rt, String tipoAvaliacao, List<Pattern> DiscoveredRuleList, int b) {
		
		double bestQuality;
		do {
			bestQuality = Rt.getQualidade();
			for(Integer item : Rt.getItens()) {
				HashSet<Integer> itens = new HashSet<>();
				Rt.getItens().forEach(i->{
					if(i!=item) itens.add(i);
				});
				
				Pattern _Rt = new Pattern(itens, tipoAvaliacao);
				if(_Rt.getQualidade() >= Rt.getQualidade() && _Rt.getItens().size() > 0 && SSDP_ACO.different(_Rt, DiscoveredRuleList)) {
					Rt = _Rt;
				}
			}
		} while(Rt.getQualidade() > bestQuality);
		
		return Rt;
	}
}
