// Topic 14: The Tower of Hanoi (6.4)

package dev.recursion;

public class HanoiTowerApp {
	
	public static void doTowers(int ndisk, char from, char to, char inter){
		if(ndisk == 1)
			System.out.println("Move disk " + ndisk + " from " + from + " to " + to);
		else{
			// Step1 : move disk n-1 from 'from' to 'inter'
			doTowers(ndisk-1, from, inter, to);
			// Step 2: move disk n from 'from' to 'to';
			System.out.println("Move disk " + ndisk + " from " + from + " to " + to);
			// Step 3: move disk n-1 from 'inter' to 'to'
			doTowers(ndisk-1, inter, to, from);
		}
	}

	public static void main(String[] args) {
		System.out.println("Please enter number of plates: ");
		int ndisk = Util.getInt();
		HanoiTowerApp.doTowers(ndisk, 'A', 'C', 'B');
	}
}
