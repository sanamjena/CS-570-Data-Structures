import java.io.*;
import java.util.*;

public class Anagrams {

	  //Fields to store data
	  public final Integer[] primes;
	  public Map<Character,Integer> letterTable = new HashMap<Character, Integer>(); 
	  public Map<Long, ArrayList<String>> anagramTable = new HashMap<Long, ArrayList<String>>();
	
	  //constructor
	  public Anagrams(){
	    primes = new Integer[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	    buildLetterTable();
	  }
	  
	  
	  //File processing method
	  public void processFile(String s) throws IOException {
			FileInputStream fstream = new FileInputStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while((strLine=br.readLine()) != null) {
				this.addWord(strLine);
			}
			br.close();
	  }
	  
	  //builds the letter table
	  private void buildLetterTable() {
		  char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			for (int i: letters ) { 
				letterTable.put(letters[i-97], primes[i-97]);
			}
	  }
	  
	/**
	 * Computes the hash code by multiplying each letter of a string using letterTable values
	 * @param s
	 * @return Returns the hash code of String s
	 */  
	 
	private Long myHashCode(String s) {
			char[] letter = s.toCharArray();
			Long result = 1L;
			for(int i=0; i<s.length();i++) {
				result = result*(letterTable.get(letter[i]));
			}
			return result;
	}
	
	/**
	 * Computes the hash code of a string and adds the string to anagramTable
	 * @param s the given String
	 */
	private void addWord(String s){
        Long hash = myHashCode(s);
        if (anagramTable.containsKey(hash)){
            ArrayList<String> temp = anagramTable.get(hash);
            temp.add(s);
            anagramTable.put(hash,temp);
        }
        else{
            ArrayList<String> array = new ArrayList<String>();
            array.add(s);
            anagramTable.put(hash, array);
        }
    }
	
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long, ArrayList<String>>> result = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		int max = 0;
		for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
			if (entry.getValue().size() > max) {
				max = entry.getValue().size();
			}
		}
		for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
			if (entry.getValue().size() == max) {
				result.add(entry);
			}
		}
		return result;
	}
	  
	  public static void main(String[] args) {
		Anagrams a = new Anagrams ();
		  final long startTime = System.nanoTime(); 
		  try {
		  a.processFile("words_alpha.txt"); 
		  } catch (IOException e1) {
			  e1.printStackTrace();
		  }
		  ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		  final long estimatedTime = System.nanoTime() - startTime;
		  final double seconds = ((double) estimatedTime/1000000000); 
		  System.out.println("Time: "+ seconds);
		  System.out.println("List of max anagrams: "+ maxEntries); 

	}

}
