// ELI WAALKES

import java.util.*;
import java.io.*;

public class Decode {
  //Uses frequency file (text file of char, double pairs) to create map of Characters and Doubles
  //Decodes Huffman Encoded text file using Huffman Code map of frquency file
  //Prints output (decoded text file) to console
  
  public static void main(String[] args) throws IOException{
    
    Scanner file = new Scanner(new File(args[0]));
    Map<Character,Double> freqMap = new HashMap<Character,Double>();
    while(file.hasNext()){ //Parse  through text file creating map
      int i = Integer.parseInt(file.next());
      Character ch = (char)i;
      double frq = Double.parseDouble(file.next());
      freqMap.put(ch,frq);
    }
    HuffmanCode huff = new HuffmanCode(freqMap); //create HuffmanCode based on Map
    Scanner scan = new Scanner(System.in);
    while(scan.hasNextLine()){ //scan, decode and print text file
      String str = scan.nextLine();
      String decoded = huff.decode(str);
      System.out.println(decoded);
    }
  }
  
}