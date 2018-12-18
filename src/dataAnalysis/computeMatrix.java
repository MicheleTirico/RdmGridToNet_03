package dataAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class computeMatrix {

	protected static String path = "D:\\java_data_analysis\\data_test";
	static File[] files = new File(path).listFiles();
	
	static BufferedReader br ;
	
	public static void main(String[] args) throws IOException  {
	    	
    	for ( File file : files ) {		// System.out.println(file);
    		FileReader fr = new FileReader(file);
    		br = new BufferedReader(fr);
    		
    		String lineIndex = br.readLine();
    		String lineParams = br.readLine() ;
    		String[] startParams = lineParams.split(";") ;
    		
    		double
	    		Da = Double.parseDouble(startParams[0]),
	    		Db = Double.parseDouble(startParams[1]) ,
	    		f = Double.parseDouble(startParams[2]),
	    		k = Double.parseDouble(startParams[3]) ,
	    		numStartSeed = Double.parseDouble(startParams[4]) ,
	    		sizeGrid = Double.parseDouble(startParams[5]) ,
	    		stepStore = Double.parseDouble(startParams[6]) ;
    		
    		int posLine = 0 ;
    		String lineIndicator = br.readLine() ,
    				nameIndStr = lineIndicator.split(";")[0],
    				line = br.readLine() ;
    	
    	}
	  }
	
	
}
