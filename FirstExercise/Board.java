//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

import java.util.Random;  
import java.util.ArrayList;


/**
 * The Board class used for implement the board for the game(Important to mention that the obstacles is represented with -1 values)
 */
public class Board {
  ArrayList<Node> listCells;  //we create the cells
  ArrayList<Node> forbidden;  //we create the list with forbidden cells
  private int size;   //the size of the board
  public int xGoal,yGoal,xGoal1,yGoal1; //the ending nodes of the board. We use those fields for the calculation of the heuristic function
  private double posibility;  //the possibility of cell being forbidden
  Random rand;    //variable for the random Generator






  //Constructor
  public Board(int size,double posibility){
    this.listCells=new ArrayList<Node>(); //initialize the list with cells
    this.forbidden= new ArrayList<Node>();  //initialize the list with forbidden cells
    this.size= size;  //initialize the size of the NxN
    this.posibility=posibility; //initialize the possibility of a cell be forbidden
    rand=new Random();  //initialize the random object
  }





 /**  
  * This method is used to create a deep copy of the board. 
  * @return Board object. 
  */
  public Board deepcopy(){
    Board returnB=new Board(this.size, this.posibility);    //create a new Board object with the same parameters
    int xpos=0,ypos=0;
        
    for(int i=0;i<this.size;i++){
      for(int j=0;j<this.size;j++){
        Node temp=new Node(i, j,getValue(i, j));    //copy the cells,value of cells
        returnB.listCells.add(temp);  //adding the new cell to the list (list with cells ) from the new object board
        temp.countH(this.xGoal, this.yGoal,this.xGoal1,this.yGoal1);    //calculate the heuristic value for every cell
      }
    }

    for(int i=0;i<this.forbidden.size();i++)returnB.forbidden.add(this.forbidden.get(i));   //copy forbidden cells

    for(int i=0;i<returnB.listCells.size();i++){
      returnB.listCells.get(i).neighbors=returnB.createEdges(xpos, ypos);   //create edges
      ypos++;
      if((i+1)%size==0)xpos++;
      if(ypos==size)ypos=0;
    }
    return returnB; //return Board object
  }





 /**
  * This method is used to createCells. 
  */
  private void createCells(){
    for(int i=0;i<this.size;i++){
      for(int j=0;j<this.size;j++)this.listCells.add(new Node(i, j,randomGenarator()));   //create Cell(i,j) with random value
    }
    forbiddenCells();    //create Forbidden cells
  }
  
  




 /**
  * This method is used for creating forbidenCells. 
  */
  private void forbiddenCells(){
    int temp;
    int numberOfElementsForbiden=(int)Math.round(this.size*this.size*this.posibility);
        
    for(int i=0;i<numberOfElementsForbiden;i++){
      temp=rand.nextInt(this.size*this.size);

      if(listCells.get(temp).value!=-1){
        this.listCells.get(temp).value=-1;  //put the value of the forbiden cell to -1
        this.forbidden.add(this.listCells.get(temp)); //put this cell to the forbiden list
      } 
    }
  }





 /**  
  * This method is used for creating Table(board).
  */
  public void createTable(){
    createCells();  //Create the cells for board
    int xpos=0,ypos=0;

    for(int i=0;i<this.listCells.size();i++){   //For every shell create the edges that is atached to the cell
      this.listCells.get(i).neighbors=createEdges(xpos, ypos);  //create adjacencies-neighbors for every cells

      ypos++;
      if((i+1)%size==0)xpos++;
      if(ypos==size)ypos=0;
    }
  }
  
  




 /** 
  * This method is used for creating Edges of a node(or cell). 
  * @param xCurrent This is the first parameter(int x coordinate)
  * @param yCurrent  This is the second parameter(int y coordinate) 
  * @return EDGE[]. This returns neigbors of the node(or the point). 
  */
  private Edge[] createEdges(int xCurrent,int yCurrent){
    Edge[] temp1=new Edge[1];
    if(getValue(xCurrent, yCurrent)==-1)return temp1; //if the node(x,y) is not valid

    ArrayList<Integer>neihbors=removeBiddenNeigbors(getNeighbors(xCurrent, yCurrent)); //list with valid neighbors
    Edge[] temp=new Edge[neihbors.size()/2];
    
    for(int i=0;i<temp.length;i++){  //for every neighbor
      temp[i]=new Edge(getNode(neihbors.get(2*i),neihbors.get((2*i)+1)), countValue(xCurrent, yCurrent,neihbors.get(2*i),neihbors.get((2*i)+1))); //create a new Edge object
    }
    return temp;
  }
  
  



 /** 
  * This method is used to find the neigbors of one node. 
  * @param x  This is the first parameter(int x coordinate)
  * @param y  This is the second parameter(int y coordinate) 
  * @return ArrayList<Integer> with all possible neighbors.
  */
  private ArrayList<Integer> getNeighbors(int x,int y){
    ArrayList<Integer> neighbor=new ArrayList<Integer>();
    ArrayList<Integer> finale  =new ArrayList<Integer>();

    neighbor.add(x-1);
    neighbor.add(y-1);
      
    neighbor.add(x-1);
    neighbor.add(y);
      
    neighbor.add(x-1);
    neighbor.add(y+1);
        
    neighbor.add(x);
    neighbor.add(y-1);
    
    neighbor.add(x);
    neighbor.add(y+1);
    
    neighbor.add(x+1);
    neighbor.add(y-1);
        
    neighbor.add(x+1);
    neighbor.add(y);
    
    neighbor.add(x+1);
    neighbor.add(y+1);
    
    for(int i=0;i<neighbor.size();i++){
      if(neighbor.get(i)<=size-1 && neighbor.get(i)>=0){
        
        i++;
        if(neighbor.get(i)<=size-1 && neighbor.get(i)>=0){
          finale.add(neighbor.get(i-1));
          finale.add(neighbor.get(i));
        }
      }
      else i++;
    }
    return finale;
  }
  
  



 /**
  * This method is used to count the cost of moving from one edge to other. 
  * @param x  This is the first parameter(int x coordinate)
  * @param y  This is the second parameter(int y coordinate) 
  * @param xNeighbour This is the third parameter(int xNeighbour coordinate)
  * @param yNeighbour This is the fourth parameter(int  yNeighbour coordinate)
  * @return Double. This returns the cost of moving from the current node to the neighbor node. 
  */
  private double countValue(int x,int y,int xNeighbour,int yNeighbour){
    double returnV=Math.abs((getValue(x, y)- getValue(xNeighbour, yNeighbour)));

    if(checkDiagonal(x, y, xNeighbour, yNeighbour))return returnV+0.5;

    return returnV+1;
  }
  
  




 /** 
  * This method is used to get the value of the node . 
  * @param x  This is the first parameter(other x coordinate)
  * @param y  This is the second parameter(other y coordinate) 
  * @return int. This returns the value of the node if the node exists in the list with cells. 
  */
  private int getValue(int x,int y){
    for(int i=0;i<this.listCells.size();i++){
      if(this.listCells.get(i).equal(x, y))return this.listCells.get(i).value;
    }
    return 0;
  }






 /**
  * This method is used to check if an element is diagonal. 
  * @param x  This is the first parameter(other x coordinate)
  * @param y  This is the second parameter(other y coordinate)
  * @param xNeighbour This is the third parameter(other y coordinate) 
  * @param yNeighbour  This is the fourth parameter(other y coordinate) 
  * @return boolean. This returns true if the neighbor is the diagonal element. 
  */
  private boolean checkDiagonal(int x,int y,int xNeighbour,int yNeighbour){
    if(xNeighbour<x && yNeighbour<y)return true;

    if(xNeighbour>x && yNeighbour>y)return true;

    if(xNeighbour<x && yNeighbour>y)return true;

    if(xNeighbour>x && yNeighbour<y)return true;

    return false;
  }






 /**
  * This method is used to check for equality. 
  * @param node1  This is the first parameter(node node1)
  * @param node2  This is the second parameter(node node2) 
  * @return boolean. This returns true if it is the same. 
  */
  public boolean checkNode(Node node1,Node node2){
    return (node1.xPosition==node2.xPosition && node1.yPosition==node2.yPosition);
  }
    
    
    
    
 /** 
  * This method is used to get a node. 
  * @param x  This is the first parameter(other x coordinate)
  * @param y  This is the second parameter(other y coordinate) 
  * @return Node. This returns the node if it is in the list with nodes else null. 
  */
  private Node getNode(int x,int y){
    for(int i=0;i<this.listCells.size();i++)if(this.listCells.get(i).equal(x, y))return this.listCells.get(i);
    return null;
  }
  
  




 /**  
  * This method is used to generate a random number(1-5).  
  * @return int. This returns the random number.
  */
  private int randomGenarator(){
    return (rand.nextInt(5-1)+1); //return a random number with upperboand 5 and lower 1
  }
  
  




 /**  
  * This method is used to print table(or board). 
  */
  public void printTable(){
    for(int i=0;i<this.listCells.size();i++){
      System.out.print(this.listCells.get(i));
      if((i+1)%this.size==0)System.out.println();
      
    }
  }
  
  



 /**  
  * This method is used to check if an element is forbidden. 
  * @param x  This is the first parameter(other x coordinate)
  * @param y  This is the second parameter(other y coordinate) 
  * @return boolean. This returns true if the node with (x,y) coordinate is forbidden. 
  */
  public boolean checkForBidden(int x,int y){
    return getNode(x, y).value==-1;
  }
  
  




 /** 
  * This method is a getter for the list with the forbidden nodes. 
  * RETURN ArrayList<Node>. This returns the list with forbidden nodes. 
  */
  public ArrayList<Node> getList(){
    return this.forbidden;
  }
  
  



 /** 
  * This method is used to remove forbidden nodes from the list. 
  * @param ArrayList<Interger> list .This is the first parameter(list with all neighbors of a node)
  * @return ArrayList<Integer>. This returns the list without the forbidden neighbors. 
  */
  private ArrayList<Integer> removeBiddenNeigbors(ArrayList<Integer> list){
    ArrayList<Integer> returnList=new ArrayList<>();
    
    for(int i=0;i<list.size();i++){
      if(!checkForBidden(list.get(i),list.get(i+1))){
        returnList.add(list.get(i));
        returnList.add(list.get(i+1));
      }
      i++;
    }
    return returnList;
  }





  
 /**
  * This method is used to set the cordinates of EndingNodes(the nodes we want to go with AStar). 
  * @param xGoal.This is the first parameter(x coordinate of the first exit)
  * @param yGoal.This is the second parameter(y coordinate of the first exit)
  * @param xGoal1.This is the third parameter(x coordinate of the second exit)
  * @param yGoal1.This is the fourth parameter(y coordinate of the second exit)
  */
  public void setxGoal(int xGoal,int yGoal,int xGoal1,int yGoal1){
    this.xGoal=xGoal;
    this.yGoal=yGoal;
    this.xGoal1=xGoal1;
    this.yGoal1=yGoal1;
  }
}
