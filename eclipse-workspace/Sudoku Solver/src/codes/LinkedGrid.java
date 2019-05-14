package codes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LinkedGrid {

	private Node root;

	public LinkedGrid(int dimension) throws FileNotFoundException {
		// make first node
		Node temp = new Node();
		root = temp;
		Node marker = root;

		// making first row
		for (int x = 0; x < dimension - 1; x++) {
			temp = new Node();
			marker.setRight(temp);
			temp.setLeft(marker);
			marker = temp;
		}
		marker = root;
		Node rowMarker = marker;

		for (int y = 0; y < dimension - 1; y++) {
			rowMarker = marker;
			// making the first node in the next row
			temp = new Node();
			marker.setDown(temp);
			temp.setUp(marker);
			marker = temp;

			// completing rest of the row
			for (int x = 0; x < dimension - 1; x++) {
				temp = new Node();
				temp.setLeft(marker);
				marker.setRight(temp);
				temp.setUp(marker.getUp().getRight());
				temp.getUp().setDown(temp);
				marker = temp;
			}
			rowMarker = rowMarker.getDown();
			marker = rowMarker;
		}
	}

	public void readFile() throws FileNotFoundException {
		int data = 0;
		File infile = new File("data.txt");
		Scanner input = new Scanner(infile);
		Node marker = root;
		Node rowMarker = root;

		int counter = 1;

		while (input.hasNext()) {
			while (marker != null) {
				while (marker != null) {
					data = input.nextInt();// reading from the file
					marker.setNumber(data);
					System.out.print(marker.getNumber() + " ");
					marker = marker.getRight();
					counter++;// counting how many data items I have read
				}
				System.out.println();

				marker = rowMarker;
				marker = marker.getDown();

				if (rowMarker.getDown() != null) {
					rowMarker = marker;
				}
			}

			// System.out.print(data + " ");//displaying to console

			/*
			 * if(counter%9 == 0) { System.out.println(); }
			 */
		}
		input.close();
	}

	public void boxID() {
		Node temp = root;
		Node rowMarker = root;
		int counter = 0;

		while (temp != null)// checking at beginning of new row
		{
			while (temp != null)// checking at end of row
			{
				if (counter < 27) {
					temp.setBoxID(((counter % 9) / 3) + 1);
				} else if (counter >= 27 && counter < 54) {
					temp.setBoxID(((counter % 9) / 3) + 4);
				} else {
					temp.setBoxID(((counter % 9) / 3) + 7);
				}
				// System.out.print(temp.getBoxID());
				counter++;
				temp = temp.getRight();
			}
			// System.out.println();

			temp = rowMarker;

			temp = temp.getDown();
			if (rowMarker.getDown() != null) {
				rowMarker = temp;
			}
		}
	}

	public void display() {
		Node temp = root;
		Node rowMarker = root;
		while (temp != null)// checking at beginning of new row
		{
			while (temp != null)// checking at end of row
			{
				System.out.print(temp.getNumber() + " ");
				temp = temp.getRight();
			}
			System.out.println();

			temp = rowMarker;

			temp = temp.getDown();
			if (rowMarker.getDown() != null) {
				rowMarker = temp;
			}
		}
	}

	public void eliminate(Node n, Node temp) {
		while (temp != null)// check at end of column
		{
			temp.eliminatePossible(n.getNumber());
			temp = temp.getDown();
		}
		temp = n;
		while (temp != null)// check at end of row
		{
			temp.eliminatePossible(n.getNumber());
			temp = temp.getRight();
		}
		temp = n;
		while (temp != null)// check at beginning of row
		{
			temp.eliminatePossible(n.getNumber());
			temp = temp.getLeft();
		}
		temp = n;
		while (temp != null)// check at beginning of column
		{
			temp.eliminatePossible(n.getNumber());
			temp = temp.getUp();
		}
		temp = n;
		// move Node temp to the first node that has the boxID that Node n has
		while (temp.getLeft() != null && temp.getLeft().getBoxID() == n.getBoxID()) {
			temp = temp.getLeft();
		}
		while (temp.getUp() != null && temp.getUp().getBoxID() == n.getBoxID()) {
			temp = temp.getUp();
		}

		// eliminate first row of the boxID
		temp.eliminatePossible(n.getNumber());
		temp = temp.getRight();
		temp.eliminatePossible(n.getNumber());
		temp = temp.getRight();
		temp.eliminatePossible(n.getNumber());

		// eliminate second row of the boxID
		temp = temp.getDown();
		temp.eliminatePossible(n.getNumber());
		temp = temp.getLeft();
		temp.eliminatePossible(n.getNumber());
		temp = temp.getLeft();
		temp.eliminatePossible(n.getNumber());

		// eliminate third row of the boxID
		temp = temp.getDown();
		temp.eliminatePossible(n.getNumber());
		temp = temp.getRight();
		temp.eliminatePossible(n.getNumber());
		temp = temp.getRight();
		temp.eliminatePossible(n.getNumber());
	}

	public void eliminateSolved() {
		Node n = root;
		Node temp = n;
		Node rowMarker = root;

		while (n != null)// check at beginning of new row
		{
			while (n != null)// check at end of each row
			{
				/*
				 * while(n.getNumber() == 0 && n.getRight() != null) { n = n.getRight(); temp =
				 * n; }
				 */

				if (n.getNumber() != 0) {
					eliminate(n, temp);
				}

				// move n to the next node
				n = n.getRight();
				if (n != null) {
					temp = n;
				}
			}
			n = rowMarker;
			n = n.getDown();
			if (rowMarker.getDown() != null) {
				rowMarker = n;
				temp = n;
			}
		}
	}

	public void solve(Node n, int x) {
		n.setNumber(x); // setting the number
		Node temp = n;
		eliminate(n, temp);
	}

	public void findSolution() {
		boolean keepGoing = false;

		do {
			keepGoing = false;
			logic1();
			if (logic1() == true)// if logic1 solves something, keep going with logic1
			{
				keepGoing = true;
			}
			
			logic2();
			if (logic2() == true)// if logic2 solves something, keep going
			{
				keepGoing = true;
			}

			logic3();
			if (logic3() == true)// if logic3 solves something, keep going
			{
				keepGoing = true;
			}
		} while (keepGoing == true);

		/*do {
			keepGoing = false;
			logic4();
			if (logic4() == true)// if logic4 solves something, keep going
			{
				keepGoing = true;
			}
		} while (keepGoing == true);*/
	}

	public boolean logic1() {
		boolean changeMade = false;
		Node temp = root;
		Node rowMarker = root;

		while (temp != null) {
			while (temp != null) {
				if (temp.getNumber() == 0)// if this is an unsolved node...
				{
					if (temp.numberPossible() == 1)// ...and there is only one possibility
					{
						solve(temp, temp.firstPossibleNumber()); // ..it must be that number
						changeMade = true;
					}
				}
				temp = temp.getRight();
			}
			temp = rowMarker;
			temp = temp.getDown();
			if (rowMarker.getDown() != null) {
				rowMarker = temp;
			}
		}
		return changeMade;
	}

	public boolean checkRow(Node n, Node temp, int x, int counter)// checking how many x there are in the row that the node is in
	{
		boolean changeMade = false;
		while (temp.getLeft() != null)// move Node temp to the very left of the row
		{
			temp = temp.getLeft();
		}
		while (temp != null) {
			if (temp.getNumber() == 0) {
				if (temp.getPossible()[x] == true)// if x is a possible number for Node temp
				{
					counter++;
				}
			}
			temp = temp.getRight();
		}
		if (counter == 1) {
			solve(n, x);
			changeMade = true;
		}
		return changeMade;
	}

	public boolean checkColumn(Node n, Node temp, int x, int counter)// checking how many x there are in the column that the node is in
	{
		boolean changeMade = false;
		while (temp.getUp() != null)// move Node temp to the very top of the column
		{
			temp = temp.getUp();
		}
		while (temp != null) {
			if (temp.getNumber() == 0) {
				if (temp.getPossible()[x] == true)// if x is a possible number for Node temp
				{
					counter++;
				}
			}
			temp = temp.getDown();
		}
		if (counter == 1) {
			solve(n, x);
			changeMade = true;
		}
		return changeMade;
	}

	public boolean checkBox(Node n, Node temp, int x, int counter)// checking how many x there are in the box that the node is in
	{
		boolean changeMade = false;

		// move Node temp to the top left corner of the box
		while (temp.getUp() != null && temp.getUp().getBoxID() == n.getBoxID()) {
			temp = temp.getUp();
		}
		while (temp.getLeft() != null && temp.getLeft().getBoxID() == n.getBoxID()) {
			temp = temp.getLeft();
		}

		Node boxMarker = temp;
		while (temp != null && temp.getBoxID() == n.getBoxID())// check at the bottom end of the box
		{
			while (temp != null && temp.getBoxID() == n.getBoxID())// check at the right end of the box
			{
				if (temp.getNumber() == 0) {
					if (temp.getPossible()[x] == true)// if x is a possible number for Node temp
					{
						counter++;
					}
				}
				temp = temp.getRight();
			}
			temp = boxMarker;
			temp = temp.getDown();
			if (boxMarker.getDown() != null && boxMarker.getDown().getBoxID() == n.getBoxID()) {
				boxMarker = temp;
			}
		}
		if (counter == 1) {
			solve(n, x);
			changeMade = true;
		}
		return changeMade;
	}

	public boolean logic2() {
		boolean changeMade = false;
		Node n = root;
		Node temp = n;
		Node rowMarker = root;
		int counter = 0;

		while (n != null) {
			while (n != null) {
				if (n.getNumber() == 0)// if this is an unsolved node...
				{
					for (int x = 1; x < 10; x++) {
						if (n.getPossible()[x] == true)// if x is a possible number for the node
						{
							changeMade = checkRow(n, temp, x, counter);// checking how many x there are in the row that the node is in
							temp = n;
							counter = 0;
							if (changeMade == true) {
								return changeMade;
							}
							if (n.getNumber() == 0)// if this is still an unsolved node...
							{
								changeMade = checkColumn(n, temp, x, counter);// checking how many x there are in the column that the node is in
								temp = n;
								counter = 0;
								if (changeMade == true) {
									return changeMade;
								}

								if (n.getNumber() == 0)// if this is still an unsolved node...
								{
									changeMade = checkBox(n, temp, x, counter);// checking how many x there are in the box that the node is in
									temp = n;
									counter = 0;
									if (changeMade == true) {
										return changeMade;
									}
								}
							}
						}
					}
				}
				n = n.getRight();
				if (n != null) {
					temp = n;
				}
			}
			n = rowMarker;
			n = n.getDown();
			if (rowMarker.getDown() != null) {
				rowMarker = n;
				temp = n;
			}
		}
		return changeMade;
	}

	public boolean logic3()// if two unsolved nodes have the same two possible numbers, eliminate these two numbers from the rest of the section
	{
		boolean changeMade = false;
		Node n = root;
		Node temp = n;
		Node rowMarker = root;

		while (n != null)// check at beginning of new row
		{
			while (n != null)// check at end of row
			{
				if (n.getNumber() == 0)// if this is an unsolved node...
				{
					if (n.numberPossible() == 2)// if there are two possible numbers for the unsolved node
					{
						while (temp != null)// check row
						{
							if (temp != n) {
								if (temp.getNumber() == 0 && temp.numberPossible() == 2) {
									if (temp.getPossible()[n.firstPossibleNumber()] == true && temp.getPossible()[n.secondPossibleNumber()] == true) {
										Node temp2 = temp;
										while (temp2.getLeft() != null)// move temp2 to the very left of the row
										{
											temp2 = temp2.getLeft();
										}
										// eliminate the two numbers from the rest of the row
										while (temp2 != null) {
											if (temp2.getNumber() == 0)// if this is an unsolved node
											{
												if (temp2 != temp && temp2 != n) {

													temp2.eliminatePossible(n.firstPossibleNumber());
													temp2.eliminatePossible(n.secondPossibleNumber());
												}
											}
											temp2 = temp2.getRight();
										}
									}
								}
							}
							temp = temp.getRight();
						}

						// check column
						temp = n;
						while (temp != null) {
							if (temp != n) {
								if (temp.getNumber() == 0 && temp.numberPossible() == 2) {
									if (temp.getPossible()[n.firstPossibleNumber()] == true && temp.getPossible()[n.secondPossibleNumber()] == true) {
										Node temp2 = temp;
										while (temp2.getUp() != null)// move temp2 to the very top of the column
										{
											temp2 = temp2.getUp();
										}
										// eliminate the two numbers from the rest of the column
										while (temp2 != null) {
											if (temp2.getNumber() == 0)// if this is an unsolved node
											{
												if (temp2 != temp && temp2 != n) {
													temp2.eliminatePossible(n.firstPossibleNumber());
													temp2.eliminatePossible(n.secondPossibleNumber());
												}
											}
											temp2 = temp2.getDown();
										}
									}
								}
							}
							temp = temp.getDown();
						}

						// check box
						temp = n;
						Node boxMarker = temp;
						while (temp != null && temp.getBoxID() == n.getBoxID()) {
							while (temp != null && temp.getBoxID() == n.getBoxID()) {
								if (temp != n) {
									if (temp.getNumber() == 0 && temp.numberPossible() == 2) {
										if (temp.getPossible()[n.firstPossibleNumber()] == true && temp.getPossible()[n.secondPossibleNumber()] == true) {
											Node temp2 = temp;
											while (temp2.getUp() != null && temp2.getUp().getBoxID() == n.getBoxID())// move temp2 to the top left corner of the box
											{
												temp2 = temp2.getUp();
											}
											while (temp2.getLeft() != null && temp2.getLeft().getBoxID() == n.getBoxID()) {
												temp2 = temp2.getLeft();
											}
											Node boxMarker2 = temp2;
											// eliminate the two numbers from the rest of the box
											while (temp2 != null && temp2.getBoxID() == n.getBoxID())// check at beginning of new row of the box
											{
												while (temp2 != null && temp2.getBoxID() == n.getBoxID())// check at end of row of the box
												{
													if (temp2.getNumber() == 0)// if this is an unsolved node
													{
														if (temp2 != temp && temp2 != n) {
															temp2.eliminatePossible(n.firstPossibleNumber());
															temp2.eliminatePossible(n.secondPossibleNumber());
														}
													}
													temp2 = temp2.getRight();
												}
												temp2 = boxMarker2;
												temp2 = temp2.getDown();
												if (boxMarker2.getDown() != null
														&& boxMarker2.getDown().getBoxID() == n.getBoxID()) {
													boxMarker2 = temp2;
												}
											}
										}
									}
								}
								temp = temp.getRight();
							}
							temp = boxMarker;
							temp = temp.getDown();
							if (boxMarker.getDown() != null && boxMarker.getDown().getBoxID() == n.getBoxID()) {
								boxMarker = temp;
							}
						}
					}
				}
				n = n.getRight();
				if (n != null) {
					temp = n;
				}
			}
			n = rowMarker;
			n = n.getDown();
			if (rowMarker.getDown() != null) {
				rowMarker = n;
				temp = n;
			}
		}
		return changeMade;
	}

	public void print() {
		Node temp = root;
		System.out.println(temp.getRight().getRight().getRight().getRight().getRight().getRight().getDown().getDown()
				.getDown().getDown().getPossible()[4]);
		// System.out.println(temp.getDown().getPossible()[9]);
	}

}
