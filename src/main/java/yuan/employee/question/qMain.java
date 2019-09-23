package yuan.employee.question;

import java.util.Scanner;

public class qMain {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		//int number= scanner.nextInt();
		//int m=scanner.nextInt();
		//int data[]=new int[number];
		String str=scanner.nextLine();
		/*
		 * for (int i = 0; i < data.length; i++) { data[i]=scanner.nextInt(); }
		 */
		//String aa ="[3|AB]";
		System.out.println(substring(str));
		//System.out.println(slove(data,m));
	}

	public static String substring(String str) {
		String result="";		
		int start_index=str.indexOf("[");
		int end_index=str.lastIndexOf("]");
		result=str.substring(start_index+1, end_index);
		if(result.contains("[")) {
			substring(result);
		}
		return result;
	}
}
