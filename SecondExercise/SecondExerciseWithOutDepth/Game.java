//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761
import java.util.Scanner;

	/*  
    	The game is not using a depth limit. So to be more efficient we reduce the dimensions of the board.
    	The minimum dimensions of the board are numberOfRows=4 and numberOfColumns=5,
    	with these dimensions we can play the game Connect-4(4 in a row in diagonal, vertical, and horizontal).
    	Be patient because the algo has no depth limit .!! 
    	It may take some time at the beginning of the program(less than 30 seconds for alpha bets pruning).
    */


public class Game {
    public static void main(String[] args) {
		System.out.println("Minimax-Algo with out depth limit.It take less than 30 seconds for the computer to make the move in the first round !! Please wait just for the first round.");
		int computerMove=0,rows=4,colls=5,k=4,playerMove=0;	//declaration of rows,columns,k.
		/*If you want to increase the numbers you must consider and follow two principles:
			1)rows<columns
			2)|rows-columns|=1
			So you have the following options (rows=5 colls=6,rows=6 colls=7).
			The number k must be the same to be a Connect-4 Game!
			WARNING:TIME COMPLEXITY
		*/
        Scanner scan = new Scanner(System.in);	//new Scanner Object
	

		MiniMaxAlgo myMiniMaxAlgo = new MiniMaxAlgo();//MiniMaxAlgo object 
		Board myBoard=new Board(rows,colls,k);	//new Board object with numberOfRows=4,numberOfColumns=5

        myBoard.start();myBoard.printTable();	//initialize the board to empty cells and printTables	
		
		System.out.println("Do you want to play first (yes or no): ");
		if(scan.next().equals("yes")){
			System.out.println("Give me a valid move : ");
			myBoard=myBoard.generateBoard('o',scan.nextInt());
		}
    
		//Game
		while(true){
			if(!myBoard.isMoveLeft()){	//check if any move left in the board
				System.out.println("\nNo more moves. Tie");	
				System.exit(0);	
			}

            computerMove = myMiniMaxAlgo.getMove(myBoard);	//get the best possible move
				
			System.out.println("\nComputer move  : " + computerMove);	
			
			myBoard=myBoard.generateBoard('x', computerMove);
			myBoard.printTable();	
			
			if(myBoard.checkForWinning()==1000){	//check for winner
				System.out.println("Computer wins");
				break;	
			}	

			if(!myBoard.isMoveLeft()){	//check if any move left in the board	
				System.out.println("\nNo more moves . Tie");	
				System.exit(0);	
			}

			System.out.println("Give me a valid move : ");	
			playerMove=scan.nextInt();
			
			if(myBoard.board[0][playerMove]!='.'){	//check if the move is valid
				System.out.println("The column that you have choose if full. Exiting");
				System.exit(0);	
			}

			myBoard=myBoard.generateBoard('o',playerMove);myBoard.printTable();	
	
			if(myBoard.checkForWinning()==-1000){	//check for winner	
				System.out.println("Player wins");	
				break;
			}
        }
		scan.close();	
    }
}
