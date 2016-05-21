package dev.recursion;

import java.io.*;

public class Util {
	public static String getString(){
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String str = null;
		try {
			str = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static int getInt(){
		String str = getString();
		int i = Integer.parseInt(str);
		return i;
	}
}
