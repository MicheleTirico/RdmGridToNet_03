package dataAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class valueSet {

	protected double Da , Db, f, k,  numStartSeed, sizeGrid , stepStore ;
	private String[] setNameInd = new String[] {
			"degreeDistribution" , "edgeCount" ,"seedCount" ,"totalEdgeLength" ,"totalEdgeLengthMST"
			} ;
	
	protected enum nameInd {	degreeDistribution , edgeCount , seedCount ,totalEdgeLength ,totalEdgeLengthMST	}
	
	protected enum whichVal {
		stepMax, nodeCount ,nodeCountNo2d ,seedCount , averageDegree , 
		totalEdgeLength , totalEdgeLengthMST , edgeCount ;
		
		public String getNameVal ( ) {
			return this.toString() ;
		}
	}
	
	private ArrayList<Double> listF = new  ArrayList<Double> () ;
	private ArrayList<Double> listK = new  ArrayList<Double> ();
	private ArrayList<Integer> listId = new  ArrayList<Integer> ();
	private ArrayList<double[]> listFK = new  ArrayList<double[]> () ;
	
	private ArrayList< value > arrayValue = new ArrayList<value>();
	private Map <Integer, value> mapIdV = new HashMap <Integer, value >() ;
	private Map <Integer, double[]> mapIdFk = new HashMap <Integer,  double[]>() ;

// SET VALUE SET ------------------------------------------------------------------------------------------------------------------------------------	
	public void setValue ( value v ) {
		arrayValue.add(v) ;
	}
	
	public void createValue ( int id , double[] ref , ArrayList<Double>vals0,ArrayList<Double> vals1, ArrayList<String> nameVals1 ) {
		value v = new value(id, ref, vals0, vals1, nameVals1) ;
		setValue(v);
		mapIdV.put(id, v);
	}
	
	public void setValue ( double[] fk ) {
		listFK.add(fk);
	}
	

// GET VALUE SET ------------------------------------------------------------------------------------------------------------------------------------	
	public value getValue ( String id ) {
		return mapIdV.get(id);
	}

}
