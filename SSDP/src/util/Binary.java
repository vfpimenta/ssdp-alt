package util;

public class Binary {
	public static boolean[][] list(int byteSize){
	    int numRows = (int)Math.pow(2, byteSize);
	    boolean[][] bools = new boolean[numRows][byteSize];
	    for(int i = 0;i<bools.length;i++)
	    {
	        for(int j = 0; j < bools[i].length; j++)
	        {
	            int val = bools.length * j + i;
	            int ret = (1 & (val >>> j));
	            bools[i][j] = ret != 0;
	        }
	    }
	    
	    return bools;
	}
}
