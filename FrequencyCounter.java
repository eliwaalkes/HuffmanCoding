//ELI WAALKES

import java.util.*;
import java.io.*;

public class FrequencyCounter {
   
   public static void main(String[] args) throws IOException {
     //Creates frequency file of text file by creating map of ( (int) char, (int) count ) pairs
     //Maintains count of total characters and unused characters
     //Outputs ((int)character, (double) frequency) pairs 
      Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
      InputStream fis; // either args[0] or System.in
      
      if(args.length<1) {
         fis = System.in;
      }
      else {
         fis = new FileInputStream(new File(args[0]));
      }
      
      int ch;
      int n = 0;
      while((ch=fis.read())>=0){
         Integer count = map.get(ch);
         if(count==null)
            map.put(ch,1);
         else
            map.put(ch,count+1);
         n++;
      }
      
      int unusedct = 0;
      for(int c=0; c<128; c++){
         if(map.get(c)==null)
            unusedct++;
      }
      
      double flen = (double)(n+1);
      for(int key=0; key<128; key++){
         Integer count = map.get(key);
         if(count==null)
            System.out.println(key+" "+1/flen/unusedct);
         else
            System.out.println(key+" "+count/flen);
      }       
   }
   
}
