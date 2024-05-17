//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

import java.util.Comparator;
/**
 * The ComparatorNodeAStar class used for overriding comparator Method of the Astar Algo.
 */
public class ComparatorNodeAStar implements Comparator<Node>{
   /**
    * This method is used to compare two nodes by the sumOfHeuristicAndNumberA fanction result. 
    * @param one The first node
    * @param two The second node
    * @return int the result of the comparison
    */
    @Override
    public int compare(Node one, Node two){

        if(one.sumOfHeuristicAndNumberA > two.sumOfHeuristicAndNumberA)return 1; 

		if(one.sumOfHeuristicAndNumberA < two.sumOfHeuristicAndNumberA)return -1;

		return 0;
    }
}
