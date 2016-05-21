// Topic 11: Recursive Triangles (6.1) Evaluate triangle numbers

package dev.recursion;

public class TriangleApp {
	
	public static int triangle(int n){
		if(n == 1)
			return 1;
		else
			return n + triangle(n-1);
	}

	public static void main(String[] args) {
		System.out.println("Please input a number:");
		int n = Util.getInt();
		System.out.println(triangle(n));
	}
}