//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

import java.util.ArrayList;

public class MiniMaxAlgo {
    int depth;	//depth that minimax will stop .We use it in order to make more eficient
    int move=0;		//the move that algo is decide to do 






	//constructor
    public MiniMaxAlgo(int depth){
        this.depth=depth;	//inisialized the depth
    }




  /** 
 	* This method implements the miniMaxAlphaBeta(fail soft) pruning.
 	* @param board is a "state " of a board
 	* @param depth is the depth limit
 	* @param alpha,the best alternative for the maximising player (X)
 	* @param beta,the  best alternative for the minimising player (O)
 	* @param maximizingPlayer. True if is the maximizingPlayer turn or false is the minimizingPlayer turn
	* @return the Value of the board
 	*/
    public int miniMaxAlphaBeta(Board board,int depth,int alpha,int beta,boolean maximizingPlayer){
		int tempScore=board.evaluate();	//the value of the state 
		
		if(Math.abs(tempScore)==1000 || depth==0 ||!board.isMoveLeft())return tempScore;	//return this value if depth=0 or state is a terminal state

		ArrayList<Integer> listPossibleStates = new ArrayList<Integer>();	//list with all possibile states or legal Moves
		listPossibleStates=board.getLegalMoves();

		if(maximizingPlayer){	//if maximizing Player then
			int value= Integer.MIN_VALUE;	//value= −∞

			for(int index=0; index<listPossibleStates.size();index++){	//for each of the legal moves (each state of the board)
				value=Math.max(value,miniMaxAlphaBeta(board.generateBoard('x',listPossibleStates.get(index)),depth-1,alpha,beta,false));	//value=max(value and miniMaxAlphaBeta)
				alpha=Math.max(alpha,value);	//alpha=max(alpha,value)

				if(alpha>= beta)return value;	//if alpha ≥ beta we prune
			}	
			return value;	//return value
		}

		if(!maximizingPlayer){	//if manimising Player then
			int value=Integer.MAX_VALUE;	//value=+∞

			for(int index =0; index<listPossibleStates.size();index++){	//for each of the legal moves (each state of the board)
				value=Math.min(value,miniMaxAlphaBeta(board.generateBoard('o',listPossibleStates.get(index)),depth-1,alpha,beta,true));	//value=min(value and miniMaxAlphaBeta)

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
     * @param depth Is the depth limit
     * @param maximizingPlayer True if is the maximizingPlayer of false is the minimizingPlayer turn
     * @return Value of the board 
     */
	public int miniMax(Board board, int depth,boolean maximizingPlayer){
		int tempScore=board.evaluate();	//evaluate the score
		
		if(tempScore==1000 || tempScore==-1000 || depth==0 ||!board.isMoveLeft())return tempScore;	// if depth==0 or node is a terminal node then return the score

		ArrayList<Integer> listPossibleStates = new ArrayList<Integer>();	//list with all possibile states or legal Moves	
		listPossibleStates=board.getLegalMoves();

		if(maximizingPlayer){	//if maximizingPlayer then
			int value= Integer.MIN_VALUE;	// value = −∞

			for(int index =0; index<listPossibleStates.size();index++)	//for each of the legal moves (each state of the board)
				value= Math.max(value,miniMax(board.generateBoard('x',listPossibleStates.get(index)),depth-1,false));	//value = max(value, miniMax(board, depth − 1, FALSE))
			
			return value;	//return value
		}

		if(!maximizingPlayer){	//if minimizingPlayer then
			int value=Integer.MAX_VALUE;	//value = +∞

			for(int index =0; index<listPossibleStates.size();index++)	//for each of the legal moves (each state of the board)
				value= Math.min(value,miniMax(board.generateBoard('o',listPossibleStates.get(index)),depth-1,true));	//value = min(value, minimax(board, depth − 1, TRUE))
			
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
			
			int moveValue=miniMaxAlphaBeta(myBoard,this.depth,Integer.MIN_VALUE,Integer.MAX_VALUE,false);	//call the minimaxAlphaBeta algo

			//int moveValue=miniMax(myBoard, depth,false); //You can call the miniMax without pruning. If you want . Important to reduce the depth
			
			//System.out.println(moveValue);

			myBoard.unplaceMove('.', tempMove);	//unplace the move that computer make
		
			if(moveValue>bestValue){	//keep the bigest score.
				this.move=tempMove;
				bestValue=moveValue;
			}
		}
        return this.move;
    }
}