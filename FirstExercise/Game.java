//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Game class used for implementing the game.
 */
public class Game{
	public static void main(String[] args){
		ArrayList<Node> returnList;	//list with the path
		int xStart=0,yStart=0,xFinish,yFinish,xFinish1,yFinish1;	//initialize the start values(points)	
		Scanner scan = new Scanner(System.in);	//initialize the start values(points)	
		int size,positionListStart=0,positionListExit1=0,positionListExit2=0;
		double posibility;	//posibility of forbiden cells




		//Take the size of board from user
		System.out.println("Give me the size N of the vector (5,6,7,...): ");
		size=scan.nextInt();

		if(size<5){	//check is the size is lower than 5
			System.out.println("Please give me larger vector.More than 5. Exiting");
			System.exit(0);
		}
		
		
		
		
		//Take the positibility of a forbiden shell from input
		System.out.println("Give me the possibility of forbiden (ex 0.1 ,0.2 , 0.3 ... ,1.0) : ");
		posibility=scan.nextDouble();
		if(posibility==1.0){	//chekc for wrong input(all cells set to be forbiden)
			System.out.println("You cant put all the cells in forbiden state.Exiting");
			System.exit(0);
		}
		
		
		
		System.out.println();
		Board myBoard=new Board(size, posibility);	//create the board object
		myBoard.createTable();	//create the cells of the board
		myBoard.printTable();	//print the board
		System.out.println();
		
		
		
		//Getting inputs from user
		System.out.println("Give me the x of the starting point : ");
		xStart=scan.nextInt();	//input x for starting Point
		System.out.println("Give me the y of the starting point : ");
		yStart=scan.nextInt();	//input y for starting Point

		if(xStart>size-1 || xStart<0 || yStart>size-1 || yStart<0 || myBoard.checkForBidden(xStart,yStart)){		//check for valid point
			System.out.println("Give me valid position ");
			System.exit(0);
		}
		
		positionListStart=xStart*size+yStart;	//count the position inside the list
		System.out.println();
		
		
		
		//Getting inputs from user
		System.out.println("Give me the x of the first exit : ");
		xFinish=scan.nextInt();	//input x of the first exit
		System.out.println("Give me the y of the first exit : ");
		yFinish=scan.nextInt();	//input y of the first exit

		if(xFinish>size-1 || xFinish<0 || yFinish>size-1 || yFinish<0 || myBoard.checkForBidden(xFinish,yFinish) || (xStart==xFinish && yStart==yFinish)){	//check if the point is invalid
			System.out.println("Give me valid position ");
			System.exit(0);
		}

		positionListExit1=xFinish*size+yFinish;	//count the position inside the list
		System.out.println();
		
		
		
		//Getting inputs from user
		System.out.println("Give me the x of the second exit : ");
		xFinish1=scan.nextInt();	//input x of the second exit
		System.out.println("Give me the y of the second exit : ");
		yFinish1=scan.nextInt();	//input y of the second exit
		scan.close();	//close scanner

		if(xFinish1>size-1 || xFinish1<0 || yFinish1>size-1 || yFinish1<0 || myBoard.checkForBidden(xFinish1,yFinish1) || (xStart==xFinish1 && yStart==yFinish1)){	//check if the point is invalid
			System.out.println("Give me valid position ");
			System.exit(0);
		}

		positionListExit2=xFinish1*size+yFinish1;	//count the position inside the list
		System.out.println();
		
		
		
		
		
		
		//setting the goals of pathFinding algo
		myBoard.setxGoal(xFinish,yFinish,xFinish1,yFinish1);	//set goals for the aStar
		

		Board secondBoard=myBoard.deepcopy();	//creating a deepcopy of the first board
		
	    
		
		
		Robot firstRobot=new Robot(myBoard.listCells.get(positionListStart),myBoard.listCells.get(positionListExit1),myBoard.listCells.get(positionListExit2),myBoard);	//Create a robot object
		System.out.println();System.out.println();


		
		long startTimeUCS=System.nanoTime();
		firstRobot.UCS();	//UCS ALGO 
		long stopTimeUCS=System.nanoTime();
		System.out.println("The UCS algorithm was running : " + Math.pow((stopTimeUCS-startTimeUCS),0.000000001));	//Time of the UCS ALGO
		

		//Print the results
		if(firstRobot.getFlag()==0){
			returnList=firstRobot.print(myBoard.listCells.get(positionListExit1));
			if(returnList!=null){
				
				System.out.println("The path cost for the UCS Algo is : " +returnList.get(returnList.size()-1).pathCost);
				System.out.println(returnList);
				System.out.println("The number of exetension for UCS : " + firstRobot.counterUCS);
			}
		}
		
		if(firstRobot.getFlag()==1){
			returnList=firstRobot.print(myBoard.listCells.get(positionListExit2));
			if(returnList!=null){

				System.out.println("The path cost for the UCS Algo is : " +returnList.get(returnList.size()-1).pathCost);
				System.out.println(returnList);
				System.out.println("The number of exetension for UCS : " + firstRobot.counterUCS);
			}
		} 
	

		



		Robot secondRobot=new Robot(secondBoard.listCells.get(positionListStart),secondBoard.listCells.get(positionListExit1),secondBoard.listCells.get(positionListExit2),secondBoard);	//Create a new robot object
		System.out.println();System.out.println();

		long startTimeASTAR=System.nanoTime();
		secondRobot.aStar();	//ASTAR ALGO
		long stopTimeASTAR=System.nanoTime();
		System.out.println("The AStar algorithm was running : " + Math.pow((stopTimeASTAR-startTimeASTAR),0.000000001));	//Time of the ASTAR ALGO
		
		//Print the results
		if(secondRobot.getFlag()==0){
			returnList=secondRobot.print(secondBoard.listCells.get(positionListExit1));
			if(returnList!=null){
				
				System.out.println("The path cost for the AStar Algo is : " +returnList.get(returnList.size()-1).numberAForFucntion);
				System.out.println(returnList);
				System.out.println("The number of exetension for Astar : " + secondRobot.counteraStar);
			}
		}
		
		if(secondRobot.getFlag()==1){
			returnList=secondRobot.print(secondBoard.listCells.get(positionListExit2));
			if(returnList!=null){

				System.out.println("The path cost for the AStar Algo is : " +returnList.get(returnList.size()-1).numberAForFucntion);
				System.out.println(returnList);
				System.out.println("The number of exetension for Astar : " + secondRobot.counteraStar);
			}
		} 
	}
	
}
