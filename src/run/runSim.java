package run;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.graphstream.graph.Graph;

import dataAnalysis.analyzeNetwork;
import dataAnalysis.handleReComputeSim;
import dataAnalysis.indicatorSet.indicator;
import layerViz.vizLayerCell;
import layers.*;
import run.framework.RdmType;
import run.framework.handleLimitBehaviur;
import run.framework.typeComp;
import run.framework.typeDiffusion;
import run.framework.typeInit;
import run.framework.typeRadius;
import run.framework.typeVectorField;

public class runSim implements parameters {
	
	public static void main ( String[] args ) throws FileNotFoundException, UnsupportedEncodingException {
		
		double [] fk = framework.getRdType(RdmType.holes) ;
		double f = fk[0], 
				k = fk[1];	//	System.out.println(f + " " + k);
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(3);
		String nameFile = "f-"+ nf.format(f)+"_k-"+ nf.format(k)+"_";
			
		System.out.println("start " + nameFile + " " + new Date().toString() );
		
		// bucket set
		bucketSet bks = new bucketSet(1, 1, sizeGridX, sizeGridY ); 
		bks.initializeBukets(); 

		// layer Rd
		layerCell lRd = new layerCell(1, 1, sizeGridX, sizeGridY ,2,5) ;
		lRd.initializeCostVal(new double[] {1,0});
		lRd.setValueOfCellAround(new double[] {1, 1}, sizeGridX/3,sizeGridY/2 ,3 );

		lRd.setReachBord( true , deltaCech);
		lRd.setGsParameters(f , k , 0.2, 0.1, typeDiffusion.mooreCost);
		
		// layer max local
		layerMaxLoc lMl = new layerMaxLoc(true,true, typeInit.test, typeComp.wholeGrid ,1) ;
		lMl.setLayers(lRd, bks);
		lMl.initializeLayer();
		
		// layer bumps
		layerCell lBumps = new layerCell(1, 1, sizeGridX, sizeGridY ,3,3) ;
		lBumps.initCells();		
		lBumps.setGridInValsLayer(lBumps.getBumbsFromPosition ( 1 , 1 , 10) , 0);		
		
		// vector field Rd
		vectorField vfRd = new vectorField(lRd, 1, 1 , sizeGridX, sizeGridY, typeVectorField.slopeDistanceRadius) ;
		vfRd.setSlopeParameters( 1 , r, alfa, true, typeRadius.circle);
				
		// layer Seed
		vectorField[] vfs = new vectorField[] { vfRd } ; 
		layerSeed lSeed = new layerSeed( vfs, 
				new double[] { 1 //, 0.0		
				} ) ;																																				
																						
		// layer net
		layerNet lNet = new layerNet("net") ;
		lNet.setLayers( bks, lSeed, lRd, lMl);
		lSeed.setLayers(lNet, bks, lRd);
		lSeed.initSeedCircle(numNodes, radiusNet, sizeGridX/2, sizeGridY/2);	
		framework.initCircle(perturVal0,perturVal1,numNodes , sizeGridX/2 ,sizeGridY/2, 2 , radiusNet );				

		lNet.setLengthEdges("length" , true );
		
		Graph netGr = lNet.getGraph();
	
		netGr.display(false) ;
		vizLayerCell vizlRd = new vizLayerCell(lRd, 1);
		
		int t = 0 ; 	//		System.out.print("steps : " );
		// lNet.seedHasReachLimit = false ;	
				
		while ( t <= stepMax // 	&& ! lSeed.getListSeeds().isEmpty() 
				&& lNet.seedHasReachLimit == false 
				&& lRd.getHasReachBord() == false
				) {	//	if ( t / (double) stepToPrint - (int)(t / (double) stepToPrint ) < 0.0001) System.out.println( nameFile +" " + "step: " + t);

			// update layers
			lRd.updateLayer();
			lMl.updateLayer();
			lNet.updateLayers( 0 ,true,1);	
			vizlRd.step();
			t++ ;
		}
		System.out.println("finish " + nameFile + " " + new Date().toString()  + " step " + t + " seed " + lSeed.getListSeeds().size() + " node " + lNet.getGraph().getNodeCount()+ "\n");		
	}
}
