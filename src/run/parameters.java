package run;

import run.framework.handleLimitBehaviur;
import run.framework.typeDiffusion;

public interface parameters {
	
	final static int numThread = 4 ;
	// common parameters
	final static int stepToStore = 10 , 
				stepToAnalyze = 10 ,
				stepToPrint = 1000 ,
				stepMax = 5000 ;	

	// parameters multi sim 
	final static  double incremKill = 0.001 , 
			incremFeed = 0.001 ,
			minFeed = 0.005 ,
			maxFeed = 0.095 , 
			minKill = 0.005  ,
			maxKill = 0.095 ;
	final static int numberOfSimulations =  (int) ((((maxFeed - minFeed ) / incremFeed ) + 1 ) * ( ( (maxKill - minKill ) / incremKill ) + 1 ) )  ;  ;
		
	final static  String  path = "D://results_08/" ; //"/home/researcher/multiSim/results_08/" ;
		
	// store and analysis parameters 
	final static boolean  
			runStoreSimNet = false , 
			runStoreNet = false ,
			runSimNet = true , 
			runAnalysisNet = true ,
			runAnalysisSimNet = true ;
	
	// layer Rd
	final static int sizeGridX = 100, 
			sizeGridY = 100 ;
	final static double Da = 0.2 , 
			Db = 0.1 ,
			initVal0 = 1 ,
			initVal1 = 0 ,
			perturVal0 = 1 ,
			perturVal1 = 1 ;	
	final static typeDiffusion tyDif = typeDiffusion.mooreCost ;
	final static boolean ceckReachBord = false ;
	final static double deltaCech = 0.05 ;
	final static handleLimitBehaviur handleLimit = handleLimitBehaviur.toroid ; // not implem
	
	// layer seed and vector field
	final static double r = 2 ,
			minDistSeed = 1 , 
			alfa = 2 ;

	// initialize circle seeds
	final static int numNodes = 20 , 
			radiusRd = 2 , 
			radiusNet = 4 ;
		
}
