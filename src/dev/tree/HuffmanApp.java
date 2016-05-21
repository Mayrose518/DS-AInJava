// Topic 21: Implement Huffman coding and decoding (Exercise 8.5)

package dev.tree;

import java.util.*;
import java.util.Map.Entry;

class HuffmanNode{
	public char ch;
	public int frequency;
	public HuffmanNode left;
	public HuffmanNode right;
	
	public HuffmanNode(char ch, int frequency, HuffmanNode left, HuffmanNode right){
		this.ch = ch;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}
}

class HuffmanComparator implements Comparator<HuffmanNode>{

	public int compare(HuffmanNode node1, HuffmanNode node2) {
		return node1.frequency - node2.frequency;
	}
}

public class HuffmanApp {
	Map<Character, String> charCode;
	
	public String compress(String sentence){
		if(sentence == null){
			System.err.println("Input sentence cannot be null");
			return null;
		}
			
		if(sentence.length() == 0){
			System.err.println("The string should at least have 1 character");
			return null;
		}
		
		Map<Character, Integer> frequency = getFrequency(sentence);
		HuffmanNode root = buildTree(frequency);
		charCode = generateCode(frequency.keySet(), root);
		String encodeMessage = encodeMessage(charCode, sentence);
		return encodeMessage;
	}
	
	public Map<Character, String> getCodeTable(){
		return charCode;
	}
	
	// decompress code, match each sub code string with code table as huffman code for each char is unique
	public String deCompress(Map<Character, String> codeTable, String code){
		StringBuilder builder = new StringBuilder();
		int k = 0;
		for(int i=0; i<=code.length(); i++){
			String subCode = code.substring(k, i);
			for(Entry<Character, String> entry : codeTable.entrySet()){
				if(entry.getValue().equals(subCode)){
					builder.append(entry.getKey());
					k = i;
				}
			}
		}
		return builder.toString();
	}
	
	// put each character's frequency in map
	private Map<Character, Integer> getFrequency(String sentence){
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i=0; i<sentence.length(); i++){
			char ch = sentence.charAt(i);
			if(!map.containsKey(ch)){
				map.put(ch, 1);
			}
			else{
				int freq = map.get(ch);
				map.put(ch, ++freq);
			}
		}
		return map;
	}
	
	// build huffman tree
	private HuffmanNode buildTree(Map<Character, Integer> freqMap){
		Queue<HuffmanNode> nodeQueue = createNodeQueue(freqMap);
		
		while(nodeQueue.size() > 1){
			HuffmanNode node1 = nodeQueue.remove();
			HuffmanNode node2 = nodeQueue.remove();
			
			HuffmanNode node = new HuffmanNode('\0', node1.frequency + node2.frequency, node1, node2);
			nodeQueue.add(node);
		}
		
		HuffmanNode root = nodeQueue.remove();
		return root;
	}
	
	// put each node in queue with comparator order
	private Queue<HuffmanNode> createNodeQueue(Map<Character, Integer> map){
		Queue<HuffmanNode> nodeQueue = new PriorityQueue<HuffmanNode>(11, new HuffmanComparator());
		for(Entry<Character, Integer> entry : map.entrySet()){
			nodeQueue.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
		}
		return nodeQueue;
	}
	
	// generate code for each character, and put them in map
	private Map<Character, String> generateCode(Set<Character> chars, HuffmanNode root){
		Map<Character, String> map = new HashMap<Character, String>();
		generateCodeRecur(root, map, "");
		return map;
	}
	
	// recursively generate code for each character
	private void generateCodeRecur(HuffmanNode node, Map<Character, String> map, String s){
		// if only root
		if(node.left == null && node.right == null){
			map.put(node.ch, s);
			return;
		}
		
		generateCodeRecur(node.left, map, s + "0");
		generateCodeRecur(node.right, map, s + "1");
	}
	
	// encode sentence
	private String encodeMessage(Map<Character, String> codeMap, String sentence){
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<sentence.length(); i++){
			builder.append(codeMap.get(sentence.charAt(i)));
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		HuffmanApp huffman = new HuffmanApp();
		String code = huffman.compress("I am a good student");
		System.out.println(code);
		Map<Character, String> codeTable = huffman.getCodeTable();
		String sentence = huffman.deCompress(codeTable, code);
		System.out.println(sentence);
	}
}