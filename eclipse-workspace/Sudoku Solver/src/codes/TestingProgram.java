package codes;

import java.io.FileNotFoundException;

public class TestingProgram {

	public static void main(String[] args) throws FileNotFoundException{
		LinkedGrid lg = new LinkedGrid(9);
		lg.readFile();
		System.out.println();
		lg.boxID();
		lg.eliminateSolved();
		  
		lg.findSolution();
		//lg.logic1();
		lg.display();
	}

}
