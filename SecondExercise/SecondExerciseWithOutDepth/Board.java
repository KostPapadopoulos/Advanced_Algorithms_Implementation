//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

import java.util.ArrayList;
import java.lang.Integer;

/**
 * The Board class is used to implement the board of the game
 */
public class Board{
    char board[][]; //the board we play
    int cols,rows,k;   //board[rows][colls],winner in k




    //consturctor
    public Board(int rows,int cols,int k){
        this.board=new char[rows][cols];    //declaration of the board
        this.cols=cols;     //declaration of the columns
        this.rows=rows;     //declaration of the rows
        this.k=k;   //declaration of the k in row elements
    }




    /**
     * This method is used to print the board.
     */
    public void printTable(){
        System.out.println("---".repeat(cols));     //print boarders

        for(int i=0;i<this.rows;i++){  
            for(int j=0;j<this.cols;j++)System.out.print(" "+this.board[i][j]+" "); //for every row and every column print the contents of cell[i][j]
            System.out.println();
        }

        for(int i=0;i<this.cols;i++)System.out.print(" "+i+" ");

        System.out.println();
        System.out.println("---".repeat(cols)); //print boarders
    }




    /**
     * This method set all the cells to '.'
     */
    public void start(){
        for(int i=0;i<this.rows;i++)    
            for(int j=0;j<this.cols;j++)this.board[i][j]='.';   //for every cell[i][j](cell in i row and j column) print the contents
    }




    /**
     * This method clones the Board- Copies all the cells
     * @return The method returns a clone Board object
     */
    public Board cloneBoard(){
        Board temp=new Board(this.rows,this.cols,this.k);   //new Board object(clone)

        for(int i=0;i<this.rows;i++)for(int j=0;j<this.cols;j++)temp.board[i][j]=this.board[i][j];  //copy all cells

        return temp;    //return the object
    }




    /**
     * This method finds all the possible and legal moves for a state(board)
     * @return a list with the numbers of the columns where we can place a coin
     */
    public ArrayList<Integer> getLegalMoves(){
        ArrayList<Integer> returnList=new ArrayList<Integer>();     

        for(int i=0;i<this.cols;i++)if(this.board[0][i]=='.')returnList.add(i); //if the first row of every column is empty then add the column to the return list

        return returnList; 
    }




    /**
     * This method finds the first free cell of the collumn.
     * @param col The collumn we want to put the coin.
     * @return  The row where the first cell is .
     */
    public int  findTheRow(int col){
        int row=0; 

        while(row<this.rows){ 
            if(this.board[row][col]!='.')break; //break when non-empty character found
            row++;
        }
        return row; 
    }




    /**
     * This method places the char arg('x','o') to a specific col 
     * @param arg   The char of the player
     * @param col   The column we want to place the arg.
     */
    public void placeMove(char arg, int col){
        this.board[findTheRow(col)-1][col]=arg; //find the first free cell of that column and place the  move
    }




    /**
     * This method sets a cell free'.'
     * @param arg   The argument is '.'
     * @param col   The column of the cell we want to release
     */
    public void unplaceMove(char arg,int col){
        this.board[findTheRow(col)][col]=arg;   //find the first not free cell of the column and set the cell free by adding the empty character '.' to the cell
    }




    /**
     * This method checks if any move is left
     * @return  True if any move left . Or false if board is full so none move is left.
     */
    public boolean isMoveLeft(){
        int moves=0;    

        for(int i=0;i<this.cols;i++)if(this.board[0][i]=='.')moves++; 

        if(moves !=0)return true;  

        return false;   
    }




    /**
     * This method checks if exist 4 elements in Row starting from cell[xStart][yStart]. Used for collums check.
     * @param xStart The x coordinate of the starting cell
     * @param yStart The y coordinate of the starting cell
     * @return  True if 4 elements exist in row
     */
    public boolean checkInRowColl(int xStart,int yStart){
        if(this.board[xStart][yStart]=='.')return false;   

        char temp=this.board[xStart][yStart];
        int numberOfElementsInRow=0;    

        for(int i=1;i<this.k;i++) 
           if(this.board[i+xStart][yStart]==temp && this.board[i+xStart][yStart]!='.')numberOfElementsInRow++; 
         
        if(numberOfElementsInRow+1==this.k)return true;

        return false;
    }




    /**
     * This methood checks if exist 4 elements in Row from cell[xStart][yStart]. Used for Rows check
     * @param xStart The x coordinate of the starting cell
     * @param yStart The y coordinate of the starting cell
     * @return True if 4 elements exist in row
     */
    public boolean checkInRowRows(int xStart ,int yStart){  //in y
        if(this.board[xStart][yStart]=='.')return false;    

        char temp=this.board[xStart][yStart];  
        int numberOfElementsInRow=0;    

        for(int i=1;i<this.k;i++)   
            if(this.board[xStart][yStart+i]==temp && this.board[xStart][yStart+i]!='.')numberOfElementsInRow++;   
        
        if(numberOfElementsInRow+1==this.k)return true;

        return false;
    }




    /**
     * This method checks if exist 4 elemets in Row from cell[xStart][yStart]. Used for diagonals
     * @param xStart The x coordinate of the starting cell
     * @param yStart The y coordinate of the starting cell
     * @return  True if 4 elements exist in row
     */
    public boolean checkInRowDiagonal(int xStart,int yStart){
        if(this.board[xStart][yStart]=='.')return false;   

        char temp=this.board[xStart][yStart];  
        int numberOfElementsInRow=0;

        for(int i=1;i<this.k;i++) 
            if(this.board[xStart+i][yStart+i]==temp && this.board[xStart+i][yStart+i]!='.')numberOfElementsInRow++;   

        if(numberOfElementsInRow+1==this.k)return true; 
        
        return false;
    }




   /**
    * This method checks if exist 4 elements in Row from cell[xStart][yStart]. Used for diagonals
    * @param xStart The x coordinate of the starting cell
    * @param yStart The y coordinate of the starting cell
    * @return True if 4 elements exist in row
    */
    public boolean checkInRowDiagonal1(int xStart,int yStart){
        if(this.board[xStart][yStart]=='.')return false;    

        char temp=this.board[xStart][yStart];   
        int numberOfElementsInRow=0;

        for(int i=1;i<this.k;i++)
            if(this.board[xStart-i][yStart+i]==temp && this.board[xStart-i][yStart+i]!='.')numberOfElementsInRow++;

        if(numberOfElementsInRow+1==this.k)return true; 

        return false;
    }




    /**
     * This method checks for connect-4 for every collumn ,every row ,every diagonal(left to right) ,every diagonal (right to left)
     * @return 1000 if winner is computer(x) or -1000 if winner is player(o)
     */
    public int checkForWinning(){
        boolean stateForRows=false,stateForColls=false,stateForDiagonal1=false,stateForDiagonal2=false,stateForDiagonal3=false,stateForDiagonal4=false;
        int winX=1000,winO=-1000;




        //check rows
        for(int i=0;i<this.rows;i++){   //for every row of the board
            int counterForRows=0;  

            while(counterForRows+k-1<this.cols){    //all possible k in row combinations
                stateForRows=checkInRowRows(i,counterForRows);  //check if they are k in row elements 

                if(stateForRows && this.board[i][counterForRows]=='x')return winX;  
                if(stateForRows && this.board[i][counterForRows]=='o')return winO;  

                counterForRows++;                
            }
        }





        //check columns
        for(int j=0;j<this.cols;j++){   //for every coll of the board
            int counterForColls=0;

            while(counterForColls+k-1<this.rows){   //all possible k in row combinations
                stateForColls=checkInRowColl(counterForColls,j);     //check if they are k in row elements 

                if(stateForColls && this.board[counterForColls][j]=='x')return winX;     
                if(stateForColls && this.board[counterForColls][j]=='o')return winO;     

                counterForColls++;
            } 
        } 
        

        //check diagonal.
        //The diagonal check is complicated and separated in four check two from right to left and two from left to right
        int xStart=this.rows-this.k;
        int times=0;
       
        while(xStart>=0){
            int temp=xStart;
            for(int i=0;i<=times;i++){
                stateForDiagonal1=checkInRowDiagonal(temp,i);

                if(stateForDiagonal1 && this.board[temp][i]=='x')return winX;   
                if(stateForDiagonal1 && this.board[temp][i]=='o')return winO;   

                temp++;
            }

            xStart--;
            times++;
        }
        
        times=0;
        int xFinish=this.cols-this.k;
        while(xFinish>=1){
            int temp=xFinish;
            for(int i=0;i<=times;i++){
                stateForDiagonal2=checkInRowDiagonal(i,temp);

                if(stateForDiagonal2 && this.board[i][temp]=='x')return winX;   
                if(stateForDiagonal2 && this.board[i][temp]=='o')return winO;   

                temp++;
            }

            xFinish--; 
            times++;
        }

       
        times=0;
        int yStart=this.cols-this.k;

        while(yStart>=0){
            int tempy=yStart;
            int tempx=this.rows-1;

            for(int i=0;i<=times;i++){
                stateForDiagonal3=checkInRowDiagonal1(tempx,tempy);

                if(stateForDiagonal3 && this.board[tempx][tempy]=='x')return winX;  
                if(stateForDiagonal3 && this.board[tempx][tempy]=='o')return winO;  

                tempx--;
                tempy++;
            }
            
            yStart--;
            times++;
            if(yStart==0)times--;
        }

      
        times=0;
        xStart=this.k-1;

        while(xStart<this.rows-1){
            int tempx=xStart;
            int tempy=0;

            for(int i=0;i<=times;i++){
                stateForDiagonal4=checkInRowDiagonal1(tempx,tempy);

                if(stateForDiagonal4 && this.board[tempx][tempy]=='x')return winX;  
                if(stateForDiagonal4 && this.board[tempx][tempy]=='o')return winO;  

                tempx--;
                tempy++;
            }

            xStart++;
            times++;
        } 
        return 0;
    }
    
    
    
    
    /**
     * This method create a clone of current board and places to the clone object a move
     * @param arg The player(char 'x' or 'o') that plays
     * @param col The collumn that player puts the move
     * @return  A board object
     */
    public Board generateBoard(char arg,int col){
        Board temp=cloneBoard();

        temp.placeMove(arg, col);

        return temp;
    }
}