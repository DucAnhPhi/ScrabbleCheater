 package scrabble.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import scrabble.util.Permutation;
import scrabble.util.PermutationUtilities;

public class SimpleWordList implements WordList {
	Collection<String> sowpods;
	static WordList wl;
	
	public SimpleWordList(){
		
		 sowpods = new ArrayList<>();
		
	}
	
	public static void main(String[] args) throws IOException {
	
		wl = new SimpleWordList().initFromFile("wordlists/sowpods.txt");
		String str = "Input";
		
		Set<String> h = wl.permutations(str);
		h.addAll(wl.words(str));
		System.out.println(h);
		
	}
	
	
	
	
	@Override
	public Set<String> permutations(String tileRackPart) {

		
		Set<String> viableWords = new HashSet<>();
		Permutation tlp = new Permutation(tileRackPart);
			
		for(String s : sowpods){
			Permutation p = new Permutation(s);
			if(p.hashCode() == tlp.hashCode())
				viableWords.add(s);
		}
		return viableWords;
	}
	
	

	@Override
	public Set<String> words(String tileRack) {
		
		Set<String> viableSubsets = new HashSet<>();
		Set<String> availableSubsets = PermutationUtilities.getSubSets(tileRack);
		for(String s: availableSubsets){
			for(String w : sowpods){
				Permutation p = new Permutation(w);
				if(s.hashCode() == p.hashCode())
					viableSubsets.add(w);
			}
		}
		
		return viableSubsets;
	}

	@Override
	public boolean add(String word) {
		return sowpods.add(word);
	}

	@Override
	public boolean addAll(Collection<String> words) {
		sowpods =  words;
		return false;
		
	}

	@Override
	public int size() {
		return sowpods.size();
	}

	@Override
	public WordList initFromFile(String fileName) throws IOException {
		WordList wList = new SimpleWordList();
		
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
	
		String current = bufferedReader.readLine();
		while(current != null){
			
			wList.add(current);
			current = bufferedReader.readLine();
		}
		
		return wList;
	}

}
