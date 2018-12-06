package run;

import java.util.ArrayList;
import java.util.Collections;

import org.graphstream.graph.Node;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;

import dataAnalysis.storeInfo;
import layers.symplifyNetwork;

public abstract class framework implements parameters {
	
	public enum typeDiffusion { mooreCost, mooreWeigthed , vonNewmannCost }	
	
	public enum typeInit {test, // centreRd , allPointActive , noInizialization
	};
	public enum typeComp {test, wholeGrid , aroundNetGraph };
	public enum handleLimitBehaviur { stopSimWhenReachLimit , toroid , test }
	public enum typeVectorField { slopeDistanceRadius , gravitiy , minVal , maxVal , interpolation ,test  } 
	public enum typeRadius { square , circle}
	
	private static String idPattern;

	protected static symplifyNetwork simNet = new symplifyNetwork() ;
	public enum RdmType { holes , solitions , movingSpots , pulsatingSolitions , mazes , U_SkateWorld , f055_k062 , chaos , spotsAndLoops , worms , waves }
	

	
// GET METHODS --------------------------------------------------------------------------------------------------------------------------------------	
	// get spatial distance from 2 nodes 
	public static double getDistGeom ( Node n1 , Node n2 ) {	
		
		double [] 	coordN1 = GraphPosLengthUtils.nodePosition(n1) , 
					coordN2 = GraphPosLengthUtils.nodePosition(n2); 
		
		return  Math.pow(Math.pow( coordN1[0] - coordN2[0] , 2 ) + Math.pow( coordN1[1] - coordN2[1] , 2 ), 0.5 )  ;
	}
	
	public static double getDistGeom ( double [] coordN1 , double [] coordN2 ) {			
		return  Math.pow(Math.pow( coordN1[0] - coordN2[0] , 2 ) + Math.pow( coordN1[1] - coordN2[1] , 2 ), 0.5 )  ;
	}
	

	
	// initialize world with more than 1 circle and corresponding Rdm pulse 
	public static void initCircle ( double valA , double valB , int numNodes , int centreX ,int centreY , int radiusRd , int radiusNet ) {	
	//	lRd.setValueOfCellAround(valA, valB, centreX, centreY, radiusRd);		
	
	//	lSeed.initSeedCircle(numNodes, radiusNet, centreX,centreY );
	}
	
	// set RD start values to use in similtion ( gsAlgo )
		protected static double[] getRdType ( RdmType pattern ) {
			double f,k ;
			switch ( pattern ) {
				case holes: 				{ f = 0.039 ; k = 0.058 ; } 
											break ;
				case solitions :			{ f = 0.030 ; k = 0.062 ; } 
											break ; 
				case mazes : 				{ f = 0.029 ; k = 0.057 ; } 
											break ;
				case movingSpots :			{ f = 0.014 ; k = 0.054 ; } 
											break ;
				case pulsatingSolitions :	{ f = 0.025 ; k = 0.060 ; } 
											break ;
				case U_SkateWorld :			{ f = 0.062 ; k = 0.061 ; } 
											break ;
				case f055_k062 :			{ f = 0.055 ; k = 0.062 ; } 
											break ;
				case chaos :				{ f = 0.026 ; k = 0.051 ; } 
											break ;
				case spotsAndLoops :		{ f = 0.018 ; k = 0.051 ; } 
											break ;
				case worms :				{ f = 0.078 ; k = 0.061 ; } 
											break ;
				case waves :				{ f = 0.014 ; k = 0.045 ; } 
											break ;		
				default :
					f= 0 ; k = 0 ;
			}	
			idPattern = pattern.toString();	
			return new double [] {f,k};
		
		}
		

		/**
		 * get list of parameters for corresponding thread
		 * @param params
		 * @param numTreadTot
		 * @param th
		 * @return
		 */
		public static ArrayList<double[]> getParamsForThread (  ArrayList<double[]> params ,int numTreadTot , int th) {
			ArrayList<double[]> list = new ArrayList<double[]> ();
			int size = params.size(),
					pos = size / numTreadTot * th , 
					lim = 0 ;
			if ( th == numTreadTot - 1 ) 
				lim = size ;
			else 
				lim = size / numTreadTot * (th+1) ;
			while ( pos < lim ) 
				list.add(params.get(pos++));
			
				return list ;
		}
		

		/**
		 * get list of parameters
		 * @param vals0
		 * @param vals1
		 * @return
		 */
		public static ArrayList<double[]>  getListParams ( boolean doShuttle , double [] vals0 , double [] vals1 ) {		
			ArrayList<double[]> list = new ArrayList<double[]> () ;
			for ( double val0 : vals0 )
				for ( double val1 : vals1 ) 
					list.add(new double [] {val0,val1} );	
			
			if ( doShuttle ) 
				 Collections.shuffle(list);
			
				return list;
		}
		
		/**
		 * get list of values from min to max with increm
		 * @param min
		 * @param max
		 * @param increm
		 * @return
		 */
		public static double[] getArrayVals (double min , double max , double increm  ) {
			int num = (int) (( max - min ) / increm) + 1 ;	
			double [] arr = new double[num];
			int pos = 0 ;
			while ( pos < num ) {
				arr[pos] =  (double) Math.round( (min + increm * pos) *1000 )/1000 ;
				pos++;
			}
			return arr ;
		}
		
}
