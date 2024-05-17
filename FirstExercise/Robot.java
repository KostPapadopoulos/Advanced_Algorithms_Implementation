//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

import java.util.*;
/**
 * The Robot class used for implement the pathFinding algos.
 */
public class Robot {
    Node  start;	//the node that algo starts	
    Node  goal;		//the first exit-node
    Node  goal1;	//the second exit-node
    Board board;	//the board that algo will work on
	int flag,counterUCS,counteraStar;	//variables for flag and counters of extensions
	
	
	
	//Constructor
	public Robot(Node start , Node goal, Node goal1, Board board){
        this. start = start;	//declaration of the start node
        this. goal=goal;	//declaration of the goal
        this. goal1=goal1;	//declaration of the goal1
        this. board=board;	//declaration of the board
		this.flag=0;	//declaration of the flag
    }





	
   /** 
    * This method is used for implametation of the UCS algo. 
    */
	public void UCS(){
		Node current,child;	//declare a variable that we want to use for exploration
		Boolean flag = false;	//set flag to false
		Double tempCost,cost;
		Comparator<Node> comparatorUCS=new ComparatorNodeUCS();		//Create a comparator object for UCS
		PriorityQueue<Node> searchHead = new PriorityQueue<Node>(100000,comparatorUCS);	//Creating a priority Queue with the nodes which need to be explored
		HashSet<Node> visited = new HashSet<Node>();//Create a hashSet with nodes that are visited.We use HashSet because we want to use the contain method(and we dont care about the order of the elements).

		searchHead.add(this.start);	//add the starting node to the Queue
		this.start.pathCost = 0;	//Path cost
		
		while(searchHead.size()!=0 && flag==false){
			current = searchHead.poll();	//Take the lowest(by path.cost node out of the Queue)
			counterUCS++;	//increase the number of extensions
			visited.add(current);	//Add the node to the visited nodes
			 
			


        	if(this.board.checkNode(current,goal) || this.board.checkNode(current,goal1) ){		//check if the node is an ending node
				flag=true;	//set flag true because we find one of the endings
				if(this.board.checkNode(current,goal1))this.flag=1;
			}	




			for(int index=0;index<current.neighbors.length;index++){	//for every edge(every neighbor)
				child = current.neighbors[index].target;	//child of the node(neighbor)
				cost = current.neighbors[index].cost;	//cost of moving to the neighbor
				Boolean tempContains=searchHead.contains(child);	//look for the child node in the search head
			
				if(!visited.contains(child)){	//if the node is not included to visited hashset and the search head.

					if(!tempContains){
						child.parent = current;		//update the cost of the parent
						child.pathCost = current.pathCost + cost;	//update the pathCost
						searchHead.add(child);	//add the node to the Queue
					}
				}	


				if(tempContains){	//if the node is inside the Queue and the node is reached from a different path with lower cost
					tempCost=current.pathCost+cost;

					if(child.pathCost>tempCost){
						child.parent=current;	//update the parent of the node
						child.pathCost = tempCost;	//update the path cost
						searchHead.remove(child);	//remove the node from the Queue
						searchHead.add(child);		//add the node to the Queue for changing the order
					}
				}
			}
		}
	}





   /**  
    * This method is used for implametation of the aStar algo. 
    */
	public void aStar(){
		Node current,child;	//declare a variable that we want to use for exploration
		Boolean flag = false;	//set flag to false
		Double cost,temp;	//declaretion of cost of moving to the neighbor,temporary sum of the heuristic Value and current numberAforFuntion 
		Comparator<Node> comparatorAStar=new ComparatorNodeAStar();		//Create a comparator object for Astar
        PriorityQueue<Node> searchHead = new PriorityQueue<Node>(100000,comparatorAStar);	//Creating a priority Queue with the nodes which need to be explored 
		HashSet<Node> visited = new HashSet<Node>();//Create a Set with nodes that are visited . HashSet because we want to use the contains method(and we dont care about the order of elements).

		searchHead.add(this.start);	//add the starting node to the Queue
        this.start.pathCost = 0;	//path cost
        
        do{
			current= searchHead.poll();	//Take the lowest(by path.cost and heuristic result node out of the Queue) 
			counteraStar++;	//increase the number of extensions
			visited.add(current);	//Add the node to the visited nodes
			



			if(this.board.checkNode(current,goal) || this.board.checkNode(current,goal1) ){		//check if the node is an ending node
				flag=true;	//set flag true because we find one of the endings
				if(this.board.checkNode(current,goal1))this.flag=1;
			}	
             
			


            for(int index=0;index<current.neighbors.length;index++){	//for every edge(every neighbor)	
				child=current.neighbors[index].target;	//child of the node of the neighbor	
				cost=current.neighbors[index].cost;	//cost of moving to the neighbor
				Boolean tempContains=searchHead.contains(child);	//look for the child node in the search head
                temp= current.numberAForFucntion + cost + child.hvalue;	//temporary sum of the heuristic Value and current numberAforFuntion 
   
                if(visited.contains(child))if(temp >= child.sumOfHeuristicAndNumberA)continue;	//check if the child is been proccesed. And	 the child has biger sumOfHeuristicAndNumberA


            	if(!tempContains || child.sumOfHeuristicAndNumberA > temp){	//if the node is not been processed
					child.parent = current;	//update the parent of the node
                    child.numberAForFucntion=current.numberAForFucntion + cost ;	//update the path cost
                    child.sumOfHeuristicAndNumberA = temp;	//ubate the sum of two numbers 

                    if(tempContains) searchHead.remove(child);	//remove the node from the Queue
                            
                    searchHead.add(child);	//add the node to the Queue
                }
            }
        }while(searchHead.size()!=0 && flag==false);	//do the above while the queue has nodes and the exit point if not found.
    }



	
   /**
    * This method is used to get a flag. 
    * @return The value of the flag.
    */
	public int getFlag(){
		return this.flag;	//return the value of the flag
	}





   /** 
    * This method is used to print the path. 
    * @param exit .This is the first parameter(node target)
    * @return pathCellList . A list with nodes if does exist. Else return null
    */
	public ArrayList<Node> print(Node exit){
		Node targetNode=exit;
		ArrayList<Node> pathCellList = new ArrayList<Node>();	//list with nodes of the path

		do{
			pathCellList.add(targetNode);	//add to the list node of the path 
			targetNode=targetNode.parent;	//from child to parent
		}while(targetNode!=null);	//while the node is not the starting node . We go back from the target Node to the first Node(from bottom to top)

		for (int index = 0, index1 = pathCellList.size() - 1; index < index1; index++)pathCellList.add(index, pathCellList.remove(index1));	//reverse list
        

		if(pathCellList.size()>1)return pathCellList;	//if the  exit point is reachable

		if(pathCellList.size()==1)System.out.println("The finish points are not reachable");	//if the exit point is not reachable

		return null;	//return null in case that the node is not reachable
	}
	
}


