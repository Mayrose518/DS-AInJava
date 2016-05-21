// Topic 12: Anagrams (6.2) Create all permutations of a word

package dev.recursion;

public class AnagramsApp {
	
	public void perm(String str){
		if(str == null)
			return;
		char[] strs = str.toCharArray();
		int length = str.length() - 1;
		doPerm(strs, 0, length);
	}
	
	private void doPerm(char[] strs, int start, int end){
		if(start == end){
			for(int i=0; i<=end; i++){
				System.out.print(strs[i]);
			}
			System.out.println();
		}
		
		for(int i=start; i<=end; i++){
			swap(strs, start, i);
			doPerm(strs, start+1, end);
			swap(strs, start, i);
		}
	}
	
	private void swap(char[] strs, int i, int j){
		char temp = strs[i];
		strs[i] = strs[j];
		strs[j] = temp;
	}

	public static void main(String[] args) {
		AnagramsApp aa = new AnagramsApp();
		aa.perm("cats");
	}
}
