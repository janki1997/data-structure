package lab06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author janki
 */
public class Anagrams {

	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
	                          67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	/**
	 * Constructors
	 */
	public Anagrams() {
		letterTable = new HashMap<Character,Integer>();
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	
	private void buildLetterTable() {
		Character[] a1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int i=0;
		while(i<26)
		{
			letterTable.put(a1[i], primes[i]);
			i++;
		}
	}
	
	/**
	 * @param s The word to check
	 */
	private void addWord(String s) {
		Long t2 = myHashCode(s);
		if (anagramTable.containsKey(t2)) {
			ArrayList<String> t1 = anagramTable.get(t2);
			t1.add(s);
			anagramTable.replace(t2, t1);
		} else {
			ArrayList<String> t1 = new ArrayList<String>();
			t1.add(s);
			anagramTable.put(t2, t1);
		}
	}
	
	/**
	 *  generates unique keys 
	 */
	private Long myHashCode(String s) {
		long result = 1;
		for (int j = 0; j < s.length(); j++) {
						Integer tmp = letterTable.get(s.charAt(j));
						result = result * tmp;
						}
						return result;
	}
	
	/**
	 * 
	 * @param s The word
	 * @throws IOException The file could not be read
	 */
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); String strLine;
		while ((strLine = br.readLine()) != null) { 
			this.addWord(strLine);
		} 
		br.close();
	}
	
	/**
	 * @return Keys and anagrams
	 */
	protected ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long,ArrayList<String>>> t1 = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		int m1 = 0;
		for (Map.Entry<Long,ArrayList<String>> add: anagramTable.entrySet()) {
			if (add.getValue().size() == m1) {
				t1.add(add);
			} else {
				if (add.getValue().size() > m1) {
					t1.clear();
					t1.add(add);
					m1 = add.getValue().size();
					
				}
			}
		}
		return t1;
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
		int length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000); 
		long key = maxEntries.get(0).getKey();
		System.out.println("Time: "+ seconds);
		System.out.println("Key of max anagrams: " + key);
		System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue()); 
		System.out.println("Length of list of max anagrams: "+ length);
	}
}