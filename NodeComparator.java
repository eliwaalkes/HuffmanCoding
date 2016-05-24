//ELI WAALKES

import java.util.*;

//comparator for TreeNodes
public class NodeComparator implements Comparator<HuffmanCode.TreeNode>{
  
  @Override
  public int compare(HuffmanCode.TreeNode t1, HuffmanCode.TreeNode t2){
    if(t1.freq > t2.freq){
      return 1;
    }else if(t1.freq < t2.freq){
      return -1;
    }else{
      return 0;
    }
  }
  
}