package codes;

public class Node {

	private Node up,down,left,right;
	private int number;
	private int boxID;
	private boolean[] possible = {true,true,true,true,true,true,true,true,true,true};
	 
	public Node() {
		up = null;
		down = null;
		left = null;
		right = null;
		number = 0;
	}
	 
	/*public Node(int data) {
	super();
	this.number = data;
	}*/
	
	public int numberPossible()
	{
		int count = 0;
		for(int x = 1; x < 10; x++)
			if(possible[x] == true)
				count++;
		return count;
	}
	 
	public int firstPossibleNumber()
	{
		for(int x = 1; x < 10; x++)
			if(possible[x]) 
				return x;
		return -1;
	}
	
	public int secondPossibleNumber()
	{
		for(int x = firstPossibleNumber() + 1; x < 10; x++)
			if(possible[x] == true) 
				return x;
		return -1;
	}

	 
	public void eliminatePossible(int x)
	{
		possible[x] = false;
	}
	 
	public Node getUp() {
		return up;
	}
	public void setUp(Node up) {
		this.up = up;
	}
	public Node getDown() {
		return down;
	}
	public void setDown(Node down) {
		this.down = down;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean[] getPossible() {
		return possible;
	}
	public void setPossible(boolean[] possible) {
		this.possible = possible;
	}
	//@return the boxID
	public int getBoxID() {
		return boxID;
	}
	//@param boxID the boxID to set
	public void setBoxID(int boxID) {
		this.boxID = boxID;
	}
}
