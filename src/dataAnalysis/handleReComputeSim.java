package dataAnalysis;

import java.io.File;

import dataAnalysis.indicatorSet.indicator;

public class handleReComputeSim {
	private analyzeNetwork aN ;
	String folder , nameFile , path ; 
	File f ;
	public handleReComputeSim (  String path , analyzeNetwork aN ) {
		this.path = path  ;
		this.aN = aN ; 
	}
	
	public handleReComputeSim (  String path ) {
		this.path = path  ;
	}
	
	public static boolean isAllIndicatorInDirectory (String nameFile , String path ) {
		indicator[] list = indicatorSet.getAllIndicators ();
	//	System.out.println(path + nameFile+ list[0] + ".csv" );
		boolean test = false ;
		int pos = 0 ;
		while ( test == false && pos < list.length ) {
			String pathFile = path + nameFile+ list[pos] + ".csv" ;			
			File f = new File(pathFile); 
			if ( f.exists() )  
				test = true ;
			pos ++ ;
		}
		return test ;
		
//		for ( indicator in : indicatorSet.getAllIndicators () ) {
//			String pathFile = path + nameFile+ in + ".csv" ;			
//			// handle file if exist 
//			File f = new File(pathFile); 
//			if ( f.exists() )  
//				break ;
//		}
	}
	
	public boolean isFileInDirectory ( String nameFile ) {
		f = new File(path + "/" + nameFile);
		if ( f.exists() == true ) 
			return true ;
		else 
			return false ;
	}
}
