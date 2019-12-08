// Big Endian

//Name: Sanam Jena

import java.util.*;
import java.lang.*;

class BinaryNumber {
    boolean overflow;
    String result = "";
    StringBuffer binary = new StringBuffer();
    BinaryNumber(int length)
    {
         for (int i = 0; i < length; i++) {
           binary.append(0);
        }
    }
   
    BinaryNumber(String str)
    {

        binary.append(str);
    	
    }
    int getLength()		// FUnction returns the length 
    {
        return binary.length();
    }
    int getDigit(int index)		//Function returns the digit at the mentioned position
    {
    	int digit = Character.getNumericValue(binary.charAt(index));
       	return digit;
    	
    }
    int toDecimal()		// Function converts to decimal
    {
        int dec_value = 0;
        int base = 1;
 
        int len = binary.length();
        for (int i = len - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1')
                dec_value += base;
            base = base * 2;
        }
        return dec_value;
    }
    void shiftR(int amount)		//Function does right shifting
    {
            while (amount != 0) {
                binary.insert(0, '0');
                amount --;
            }
    }
    void add( String str,String astr)
    {
        
        int s = 0;
    int i = str.length() - 1, j = astr.length() - 1;
    while (i >= 0 || j >= 0 || s == 1)
    {
        s += ((i >= 0)? str.charAt(i) - '0': 0);
        s += ((j >= 0)? astr.charAt(j) - '0': 0);
 
     
        result = (char)(s % 2 + '0') + result;
 
        s /= 2;
 
        i--; j--;
        overflow = true;
    }
    astr = result;
    }
    void clearOverflow()
    {
        overflow = false;
    }
    String toString (int bin)
    {
        return Integer.toString(bin);
    }
	
	
	
    public static void main(String[] args) {
    	   
    	   BinaryNumber b = new BinaryNumber(4);
    	   BinaryNumber b1 = new BinaryNumber("10100");
    	   int digit = 0;
    	   
    	   
    	   System.out.println(b.binary);
    	   System.out.println(b1.binary);
    	   b1.add("1010", "1010");
    	   try {
    	   digit = b1.getDigit(12);
    	   System.out.println(digit);
    	   } catch (Exception e) {
    		   e.printStackTrace();
    	   }
    	   b1.shiftR(9);
    	   
    	   int decimal = b1.toDecimal();
    	   int length = b1.getLength();
    	   System.out.println(length);
    	   System.out.println(decimal);
    	   System.out.println(b1.result);
    	   System.out.println(b1.binary);
    	   
    		}

}
	
