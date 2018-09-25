package util;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * @see https://stackoverflow.com/questions/6409652/random-weighted-selection-in-java
 * @author Peter Lawrey
 *
 * @param <E>
 */
public class RandomCollection<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(Const.random);
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }
    
    /**
     * @author Victor Pimenta 
     * @param weights
     * @param results
     * @return
     */
    public RandomCollection<E> addAll(double[] weights, E[] results) {
    	if (weights == null || results == null || weights.length != results.length || weights.length == 0)
    		return this;
    	for (int i = 0; i < weights.length; i++) {
    		this.add(weights[i], results[i]);
    	}
    	return this;
    }

    public E next() {
    	if (map.size() == 0) return null;
        double value = random.nextDouble() * total;
        E result = map.higherEntry(value).getValue();
        total -= map.higherEntry(value).getKey();
        map.remove(map.higherEntry(value).getKey());
        return result;
    }
}