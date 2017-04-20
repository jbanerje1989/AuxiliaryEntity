package AuxiliaryEntityCode;

import java.io.File;
import java.io.FileNotFoundException;

public class DriverAuxiliaryEntity {
	public static void main(String[] args) throws FileNotFoundException{
		String fileName = "case118IIRsAtTimeStep1";
		int KVal = 10;
		int hardeningBudget = 4;
		
		System.out.println("ILP Output");
		long startTime = System.currentTimeMillis();
		ILPSolution Object = new ILPSolution(fileName, KVal, hardeningBudget);
		Object.optimize();
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("==============================================");	
		Object.printReport();
		System.out.println("Total Time: " + (double) totalTime / 1000.0 + " seconds"); 
		System.out.println("==============================================");	
		
		System.out.println("Heuristic Output");
		startTime = System.currentTimeMillis();
		HeuristicSolution Object2 = new HeuristicSolution(fileName, Object, hardeningBudget);
		Object2.compute();
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("Components Protected: " +  Object2.getTotalProtected());
		System.out.println("Total Time: " + (double) totalTime / 1000.0 + " seconds"); 
		System.out.println("==============================================");	
		
		// Delete all files created for heuristic
		File dir = new File("OutFileForHeuristics/");
		for(File file: dir.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();
	}
}
