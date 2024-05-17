//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761
import java.util.ArrayList;

public class MiniMaxAlgo {
    int move=0;		//the move that algo is decide to do 




  /** 
 	* This method implements the miniMaxAlphaBeta(fail soft) pruning.
 	* @param board is a "state " of a board
 	* @param alpha, the best alternative for the maximising player (X)
 	* @param beta, the  best alternative for the minimising player (O)
 	* @param maximizingPlayer. True if is the maximizingPlayer turn or false is the minimizingPlayer turn
	* @return the Value of the board
 	*/
    public int miniMaxAlphaBeta(Board board,int alpha,int beta,boolean maximizingPlayer){
		int tempScore=board.checkForWinning();	//the value of the state 
		
		if(Math.abs(tempScore)==1000 ||!board.isMoveLeft())return tempScore;	//return this value if state is a terminal state

		ArrayList<Integer> listPossibleStates = new ArrayList<Integer>();	//list with all possibile states or legal Moves
		listPossibleStates=board.getLegalMoves();

		if(maximizingPlayer){	//if maximizing Player then
			int value= Integer.MIN_VALUE;	//value= −∞

			for(int index=0; index<listPossibleStates.size();index++){	//for each of the legal moves (each state of the board)
				value=Math.max(value,miniMaxAlphaBeta(board.generateBoard('x',listPossibleStates.get(index)),alpha,beta,false));	//value=max(value and miniMaxAlphaBeta)
				alpha=Math.max(alpha,value);	//alpha=max(alpha,value)

				if(beta<=alpha)return value;	//if beta ≤ αlpha we prune
			}	
			return value;	//return value
		}

		if(!maximizingPlayer){	//if manimising Player then
			int value=Integer.MAX_VALUE;	//value=+∞

			for(int index =0; index<listPossibleStates.size();index++){	//for each of the legal moves (each state of the board)
				value=Math.min(value,miniMaxAlphaBeta(board.generateBoard('o',listPossibleStates.get(index)),alpha,beta,true));	//value=min(value and miniMaxAlphaBeta)

				beta=Math.min(beta,value);	//beta=min(beta,value)

				if(beta<=alpha)return value;	//if beta ≤ αlpha we prune
			}
			return value;	//return value
		}
		return 0;
	}




  /**
     * This method implements the miniMax Algo.
     * @param board Board to play on and evaluate
     * @param maximizingPlayer True if is the maximizingPlayer of false is the minimizingPlayer turn
     * @return Value of the board 
     */
	public int miniMax(Board board,boolean maximizingPlayer){
		int tempScore=board.checkForWinning();	//evaluate the score
		
		if(Math.abs(tempScore)==1000 ||!board.isMoveLeft())return tempScore;	// if is a terminal node then return the score

		ArrayList<Integer> listPossibleStates = new ArrayList<Integer>();	//list with all possibile states or legal Moves	
		listPossibleStates=board.getLegalMoves();

		if(maximizingPlayer){	//if maximizingPlayer then
			int value= Integer.MIN_VALUE;	// value = −∞

			for(int index =0; index<listPossibleStates.size();index++)	//for each of the legal moves (each state of the board)
				value= Math.max(value,miniMax(board.generateBoard('x',listPossibleStates.get(index)),false));	//value = max(value, miniMax(board,FALSE))
			
			return value;	//return value
		}

		if(!maximizingPlayer){	//if minimizingPlayer then
			int value=Integer.MAX_VALUE;	//value = +∞

			for(int index =0; index<listPossibleStates.size();index++)	//for each of the legal moves (each state of the board)
				value= Math.min(value,miniMax(board.generateBoard('o',listPossibleStates.get(index)),true));	//value = min(value, minimax(board,TRUE))
			
			return value;	//return value
		}
		return 0;
	}




	
	/**
	 * This method is used to choose the best possible move
	 * @param myBoard Board object
	 * @return The number the of the column where computer places the move
	 */
    public int getMove(Board myBoard){
		ArrayList<Integer> legalMoves=new ArrayList<Integer>();	//list with all the legal moves
		legalMoves=myBoard.getLegalMoves();
		int bestValue=Integer.MIN_VALUE;

		for(int i=0;i<legalMoves.size();i++){
			int tempMove=legalMoves.get(i);	//Get the number of the column

			myBoard.placeMove('x',tempMove);	//place 'x' or place a computer move
			
			int moveValue=miniMaxAlphaBeta(myBoard,Integer.MIN_VALUE,Integer.MAX_VALUE,false);	//call the minimaxAlphaBeta algo

			//int moveValue=miniMax(myBoard,false); // If you want you can call the miniMax without pruning. Important-calling minimax with out pruning will increase the time that computer need to respond
			
			System.out.println("Value of column : "+ tempMove +" pos is : " +moveValue);

			myBoard.unplaceMove('.', tempMove);	//unplace the move that computer make
		
			if(moveValue>bestValue){	//keep the bigest score.
				this.move=tempMove;
				bestValue=moveValue;
			}
		}
        return this.move;
    }
}