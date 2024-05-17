//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

import java.util.Scanner;
/*The game is specialy designed for numberOfRows=6,numberOfColumns=7,and k=4 (connect 4). 
  For a deferent combination programm is not working properly.
  We have set a big depth . So the algo be more efficient.*/

public class Game {
    public static void main(String[] args) {
		int computerMove=0,rows=6,colls=7,k=4,playerMove=0,depth=8;	//declaration of rows,columns,k
        Scanner scan = new Scanner(System.in);	//new Scanner Object
	

		MiniMaxAlgo myMiniMaxAlgo = new MiniMaxAlgo(depth);//MiniMaxAlgo object with depth 8
		Board myBoard=new Board(rows,colls,k);	//new Board object with numberOfRows=6,numberOfColumns=7

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
