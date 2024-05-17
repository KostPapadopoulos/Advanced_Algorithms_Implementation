//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

public class Edge{
    public final double cost;	//cost of the edge
	public final Node target;	//target node

	public Edge(Node targetNode, double costVal){
		cost = costVal;	//initialize the cost of the edge
		target = targetNode;	//initialize the node that the edge is attached

	}





	
   /**
    * This method is used to return a string. 
    * @return string. This returns a string with the contains of the edge. 
    */
	public String toString(){
		return " targetNode : "+ this.target.toString()+"costVal: "+ this.cost ;
	}
}

