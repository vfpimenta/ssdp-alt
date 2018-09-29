package util;

import org.apache.commons.math3.distribution.CauchyDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

public class PDF {
		private static RandomGenerator rng = new Well19937c(); 
		private static RealDistribution pdf = new CauchyDistribution();
		
		public static void setMedian(double median) {
			rng.setSeed(Const.SEEDS[0]);
			pdf = new CauchyDistribution(rng, median, 1);
		}
		
		public static double sample() {
			return pdf.sample();
		}
}
