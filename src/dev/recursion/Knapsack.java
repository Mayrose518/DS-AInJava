// Topic 16: Implement 0-1 knapsack (Exercise 6.4)

package dev.recursion;

public class Knapsack {
	int w[] = {2,6,13,7,9};
	
	public boolean knapsack(int weight, int bags){
		if(weight == 0)
			return true;
		else if(weight < 0 || (weight > 0 && bags < 1))
			return false;
		else{
			if(knapsack((weight - w[bags-1]), bags-1)){
				System.out.println("Bag: " + (bags - 1) + " : " + w[bags - 1]);
				return true;
			}
			else{
				return knapsack(weight, bags-1);
			}
		}
					
	}

	public static void main(String[] args) {
		Knapsack sack = new Knapsack();
		sack.knapsack(19, 5);
	}
}
