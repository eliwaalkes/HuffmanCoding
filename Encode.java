//ELI WAALKES

import java.util.*;
import java.io.*;

public class Encode {
  
  public static void main(String[] args) throws IOException{
      //Uses frequency file (text file of char, double pairs) to create map of Characters and Doubles
      //Encodes text file using Huffman Code map of frequency file
      //Prints output (encoded text file) to console
    
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
    while(scan.hasNextLine()){ //scan, encode and print text
      String str = scan.nextLine() + "\n";
      System.out.println(huff.encode(str));
    }
  }  
}