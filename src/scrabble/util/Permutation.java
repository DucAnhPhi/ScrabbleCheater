package scrabble.util;

import java.util.Arrays;


public class Permutation {
	String word;
	String normalizedWord;
	
	public static void main(String[] args){
		Permutation p = new Permutation("zrtana");
		Permutation p2 = new Permutation("artanz");
		p.hashCode();
		p2.hashCode();
	}
	
	public Permutation(String word) {
		this.word = word;
		this.normalizedWord = setNormalized();
	}

	@Override
	public int hashCode() {
		return getNormalized().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return (getNormalized().equals(((Permutation) obj).setNormalized()));

	}

	@Override
	public String toString() {
		return getWord() + " / " + getNormalized();
		
	}
	
	public String setNormalized(){
		char[] wordToNormalize = getWord().toCharArray();
		Arrays.sort(wordToNormalize);
		return String.valueOf(wordToNormalize);
		
	}
	
	public String getNormalized() {
		return this.normalizedWord;
	}

	public String getWord() {
		return this.word;
	}

	public int length() {
		return this.word.length();
	}

}
