package scrabble.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PermutationUtilities {
	static Set<String> hash;
	/**
	 * creates a set with all subsets of the input string
	 * 
	 * @param str
	 * @return
	 */
	public static Set<String> getSubSets(String str) {
		hash = new HashSet<>();
		
		if(str.length() >= 2){
			Permutation p = new Permutation(str);
			generateSubsets(p.getNormalized());
		}

		return hash;
	}
	
	public static void generateSubsets(String word){
			
			hash.add(word);
			char[] cword = word.toCharArray();
			int indexToSkip = 0; 
			while(indexToSkip < cword.length){
				String s = "";
					for(int i = 0; i < cword.length; i++){
						if(i != indexToSkip)
							s += cword[i];		
					    }
					if(s.length() >= 2){
						hash.add(s);
						generateSubsets(s);
					}
			indexToSkip++;
			}
	}

	public static String createPermutation(int length) {
		
		String randomPermutation="";
		
		for(int i=0; i<length; i++){
		Random randomC= new Random();
		int charValue= randomC.nextInt(25)+97;
		char randomChar= (char) charValue;
		randomPermutation+=randomChar;
		}
		return randomPermutation;
	}

	public static String createPermutation(String p) {
		char[] word = p.toCharArray();
		char[] notUsed = p.toCharArray();
		String permutation="";
		
		while(permutation.length() !=word.length){
		Random random = new Random();
		int randomIndex=random.nextInt(word.length);
		
		if(notUsed[randomIndex]!=0){
			permutation+=word[randomIndex];
			notUsed[randomIndex]=0;}
		}
		return permutation;
	}
}
