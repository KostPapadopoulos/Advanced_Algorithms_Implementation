//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

/**
 * The Node class used for creating the cells.
 */
public class Node{

	public int xPosition;	//x coordinate of the node(cell).
  public int yPosition;	//y coordinate of the node(cell).
  public int value;	//value of the cell.
  public double numberAForFucntion,sumOfHeuristicAndNumberA;  //declaretion of variables used by Astar
	public double hvalue;	//the value of heuristic function for this node(cell).

	public double pathCost;	//pathCost.
	public Edge[] neighbors;	//neighbors list.
	public Node parent; //parent node(cell).






	//Constuctor
	public Node(int x,int y,int value){
    this.xPosition=x; //initialize the x coordinate of the node(cell)
    this.yPosition=y; //initialize the y coordinate of the node(cell)
    this.value=value; //initialize the value of the node
    this.pathCost=0;  //set the pathCost to zero
    this.numberAForFucntion=0;  //cost used for AStar.Similar to pathCost but we use a different Variable in order to be more distinguishable.
    this.sumOfHeuristicAndNumberA=0;  //sum of the heuristic and numberAForFunction
	}






 /**  
  * This method is used to check for equality. 
  * @param X  This is the first parameter(other x coordinate).
  * @param Y  This is the second parameter(other y coordinate).
  * @return boolean. This returns true if it is the same. 
  */ 
	public boolean equal(int x,int y){
    return this.xPosition==x && this.yPosition==y;
  }





 /**  
  * This method is used to check for equality. 
  * @param other node . This is the first parameter (other node(other cell)).
  * @return boolean. This returns true if it is the same. 
  */
	public boolean equal(Node other){
		return this==other;
	}





 /**
  * This method is used to return a string. 
  * @return string. This returns a string with the contents of the node(point). 
  */
	public String toString(){
    return "("+this.xPosition+","+this.yPosition+")"+": "+ this.value+" ";
	}




  
 /** 
  * This method is used to count the value of the heuristic function(Using the Chebyshev distance) for this node. 
  * @param xGoal  This is the first parameter(int x coordinate).
  * @param yGoal  This is the second parameter(int y coordinate).
  */
	public void countH(int xGoal,int yGoal,int xGoal1 ,int yGoal1){
		double C=0.5; //minimum cost

		double dx= Math.abs(this.xPosition-xGoal);  //dx for the first node we choose
		double dy=Math.abs(this.yPosition-yGoal);   //dy for the second node we choose
		double hGoal=C*Math.max(dx, dy); //the result

    double dx1= Math.abs(this.xPosition-xGoal1);  //dx1 for the second node we choose
		double dy1=Math.abs(this.yPosition-yGoal1);   //dx2 for the second node we choose
		double hGoal1=C*Math.max(dx1, dy1);  //the result
	  

    if(hGoal<hGoal1)this.hvalue=hGoal;  //we keep the hGoal in case the hGoal is lower than hGoal1
    else this.hvalue=hGoal1;  //otherwise,we keep the hGoal1
	}
}