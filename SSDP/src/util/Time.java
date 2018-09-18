package util;

public class Time {
	private static long startTime;
	private static long endTime;

	public static void tic() {
		startTime = System.nanoTime();
	}
	
	public static void toc() {
		endTime = System.nanoTime();
	}
	
	public static void elapsed(String name) {
		double duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
		System.out.println("Elapsed time on `"+name+"': "+duration+"ms");
	}
}
