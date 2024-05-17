//CHARALAMPOS  THEODORIDIS   4674
//ILIAS        PAPATHANASIOY 4765
//KONSTANTINOS PAPADOPOYLOS  4761

import java.util.Comparator;
/**
 * The ComparatorNodeUCS class used for overriding comparator Method of the UCS Algo.
 */
public class ComparatorNodeUCS implements Comparator<Node>{
   /** 
    * This method is used to compare two nodes by pathCost. 
    * @param one  The first node.
    * @param two  The second node.
    * @return int The result of the comparison
    */
    @Override
    public int compare(Node one, Node two){

        if(one.pathCost > two.pathCost)return 1;    

        if(one.pathCost < two.pathCost)return -1;

        return 0;
    }
}
