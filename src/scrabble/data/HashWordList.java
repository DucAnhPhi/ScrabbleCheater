package scrabble.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import scrabble.util.Permutation;
import scrabble.util.PermutationUtilities;

public class HashWordList implements WordList {
	
	Hashtable<Integer,Set<String>> table;
	Set<String> viableWords;
	
	public HashWordList(){
		viableWords = new HashSet<>();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		HashWordList hwl = new HashWordList();
		String test = "abcdefg";
		hwl.initFromFile("wordlists\\sowpods.txt");
		hwl.permutations(test);
		hwl.words(test);
		for(String s: hwl.viableWords)
			System.out.println(s);
	}
	
	
	@Override
	public Set<String> permutations(String tileRackPart) {
		
		Set<String> viableWords = new HashSet<>();
		Permutation tlp = new Permutation(tileRackPart);
		if(table.containsKey(tlp.hashCode())){
		viableWords = table.get(tlp.hashCode());	
		
		for(String s: viableWords){
			this.viableWords.add(s);
		}
		
		}
		return viableWords;
	}

	@Override
	public Set<String> words(String tileRack) {
		
		Set<String> subSets = PermutationUtilities.getSubSets(tileRack);
		for(String s : subSets){
			permutations(s);
		}
		
		return this.viableWords;
	}

	@Override
	public boolean add(String word) {
		 table.put(new Permutation(word).hashCode(), new HashSet<String>());
		 return table.containsKey(new Permutation(word).hashCode());
	}

	@Override
	public boolean addAll(Collection<String> words) {
		
		return false;
	}

	@Override
	public int size() {
		
		return table.size();
	}

	@Override
	public WordList initFromFile(String fileName) throws FileNotFoundException, IOException {
		table = new Hashtable<>();
		WordList wList = new SimpleWordList();
		
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
	
		String current = bufferedReader.readLine();
		while(current != null){
			Permutation p = new Permutation(current);
			
			if(!table.containsKey(p.hashCode())){
				table.put(p.hashCode(), new HashSet<String>());
				table.get(p.hashCode()).add(current);
			}
			else
				table.get(p.hashCode()).add(current);
			
			
			current = bufferedReader.readLine();
		}
		
		return wList;
		
	}

}
