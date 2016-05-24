//ELI WAALKES

import java.util.*;
import java.io.*;

class HuffmanCode {
  //Creates Huffman Tree of frequency file with char/binary expression of char in codeMap
  //and char/frequency in freqMap
  //Minimizes bits/char for encoding
  //Encodes text files
  //Decodes text files

    TreeNode root;
    Map<Character,String> codeMap;
    Map<Character,Double> freqMap;

    class TreeNode{
       double freq;
       char letter;
       TreeNode left,right;

       TreeNode(double freq,TreeNode left,TreeNode right){
          this.freq = freq;
          this.left = left;
          this.right = right;
       }
       
       TreeNode(char letter, double freq){
          this.freq = freq;
          this.letter = letter;
          this.left = this.right = null;
       }
       
       public boolean isLeaf(){
          return left==null && right==null;
       }
       
    }
    //create HuffmanCode object
    HuffmanCode(Map<Character,Double> freqMap){
       this.freqMap = freqMap;
       PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>(new NodeComparator());
       
       //create TreeNode with all Character, Double pairs from freqMap
       //add to PriorityQueue with highest priority being lowest frequency
       for(Map.Entry<Character,Double> entry : freqMap.entrySet()){ 
         TreeNode temp = new TreeNode(entry.getKey(),entry.getValue());
         queue.add(temp);
       }
       
       //remove top two TreeNodes (two current lowest frequencies)
       //create new TreeNode with summation of two frequencies as freq and original two TreeNodes as left/right children
       //re-add to Queue, continue until only one TreeNode
       while(queue.size() > 1){
         TreeNode t1 = queue.remove();
         TreeNode t2 = queue.remove();
         TreeNode node = new TreeNode(t1.freq + t2.freq, t1, t2);
         queue.add(node);
       }
       root = queue.remove();
       String s = ""; 
       Map<Character,String> tree = new TreeMap<Character,String>();
       codeMap = traversal(root,s,tree); //create CodeMap with character and binary expression for character
    }
    
    //recursively create map
    public Map<Character,String> traversal(TreeNode node, String s, Map<Character,String> tree){
      if(!node.isLeaf()){
        traversal(node.left,s+"0",tree);
        traversal(node.right,s+"1",tree);
      }else{
        tree.put(node.letter,s);
      }
      return tree;
    }
    
    //using codeMap compresses string to binary expression with min bits/char
    public String encode(String plain){
       StringBuilder s = new StringBuilder();
       for(int i=0;i<plain.length();i++){
         char ch = plain.charAt(i);
         String val = codeMap.get(ch);
         s.append(val);
       }
       return s.toString();
    }
    
    //using codeMap decodes compressed file
    public String decode(String code){ 
      StringBuilder result = new StringBuilder(); 
      int index = 0; 
      while(index<code.length()){ 
        TreeNode current = root; 
        while(!current.isLeaf()){ 
          int c = Character.getNumericValue(code.charAt(index)); 
          if(c==0){ 
            current = current.left; 
          } 
          else{ 
            current = current.right; 
          } 
          index++; 
        } 
        result.append(current.letter); 
      } 
      return result.toString(); 
    }
    
    //calculates bits/char of compressed binary expression of characters
    public double bitsperchar(){
       double s = 0.0;
       for(Map.Entry<Character,String> entry : this.codeMap.entrySet()){
         Character c = entry.getKey();
         double temp = this.codeMap.get(c).length() * this.freqMap.get(c);
       }
       return s;
    }
    
    //prints char, ascii value, and maximized binary expression value
    public static void main(String[] args){
      try{
        if(args.length < 1){
          Scanner input = new Scanner(System.in);
          String str = input.nextLine();
          Scanner scan = new Scanner(str);
          Map<Character,Double> freqMap = new TreeMap<Character,Double>();
          while(scan.hasNextLine()){
            int i = Integer.parseInt(scan.next());
            char ch = (char)i;
            double frq = Double.parseDouble(scan.next());
            freqMap.put(ch,frq);
          }
          input.close();
          scan.close();
          
          HuffmanCode huff = new HuffmanCode(freqMap);
          for(Map.Entry<Character,String> entry : huff.codeMap.entrySet()){
            System.out.format(entry.getKey() + " " + (int)entry.getKey() + " " + entry.getValue());
          }        
        }else{
          Scanner scan = new Scanner(new File(args[0]));
          Map<Character,Double> freqMap = new TreeMap<Character,Double>();
          while(scan.hasNext()){
            int i = Integer.parseInt(scan.next());
            char ch = (char)i;
            double frq = Double.parseDouble(scan.next());
            freqMap.put(ch,frq);
          }
          HuffmanCode huff = new HuffmanCode(freqMap);
          for(Map.Entry<Character,String> entry : huff.codeMap.entrySet()){
            System.out.format(entry.getKey() + " " + (int)entry.getKey() + " " + entry.getValue());
          }
          System.out.println();
        }
      }catch(IOException e){
        System.out.print(e);
      }
    }
    
}