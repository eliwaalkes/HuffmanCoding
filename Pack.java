//ELI WAALKES

import java.util.*;

class Pack {

    public static void pack(StringBuilder b, ArrayList<Byte> arr){
      arr.clear();
      int n = b.length()%8;
      arr.add((byte) n);
      int p = 1;
      
      for(int i=0; i<b.length(); i+=8){
        byte m=0;
        
        for(int k=0; k<8; k++){
          m <<= 1;
          if(i+k<b.length()){
            char c = b.charAt(i+k);
            if(c=='1')
              m |= 1;
            else if(c!='0')
              throw new RuntimeException("pack: Invalid input character "+c);
          }
        }
        arr.add(m);
      }
    }

    public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);
      StringBuilder sb = new StringBuilder();
      ArrayList<Byte> array = new ArrayList<Byte>();
      
      while(scanner.hasNextLine()){
        sb.append(scanner.nextLine());
      }
      
      pack(sb,array);
      
      for(byte c : array){
        System.out.write(c);
      }
      System.out.flush();
    }
}
