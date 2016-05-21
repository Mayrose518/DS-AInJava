// Topic 17: Implement Shell sort (7.1)

package dev.sort;

class ShellArray{
	private int[] array;
	private int nElems;
	
	public ShellArray(int max){
		array = new int[max];
		nElems = 0;
	}
	
	public void insert(int value){
		array[nElems] = value;
		nElems++;
	}
	
	public void display(){
		System.out.print("A = ");
		for(int j=0; j<nElems; j++)
			System.out.print(array[j] + " ");
		System.out.println();
	}
	
	public void shellSort(){
		int inner, outer, temp;
		int h = 1;
		
		// step 1: find intervals
		while(h < nElems / 3)
			h = h * 3 + 1;
		
		// step 2: loop top intervals to the end
		while(h > 0){
			for(outer=h; outer<nElems; outer++){
				temp = array[outer];
				inner = outer;
				// step 3: sort each interval
				while(inner >= h && array[inner-h] > temp){
					array[inner] = array[inner-h];
					inner = inner - h;
				}
				array[inner] = temp;
			}
			// step 4: change intervals
			h = (h - 1) / 3;
		}
	}
}

public class ShellSortApp {

	public static void main(String[] args) {
		int max = 50;
		ShellArray array = new ShellArray(max);
		
		for(int i=0; i<max; i++){
			int n = (int)(java.lang.Math.random() * 99);
			array.insert(n);
		}
		
		array.display();
		array.shellSort();
		array.display();
	}

}
