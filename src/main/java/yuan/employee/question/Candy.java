package yuan.employee.question;

import java.util.Scanner;

public class Candy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		
		int data[] = new int[number];
		
		for (int i = 0; i < data.length; i++) {
			data[i]=scanner.nextInt();
		}
		int numbers[]= new int [number];
		for (int i = 0; i < data.length; i++) {
			int result=0;
			//判断两束之间是否存在公约数
			//若存在，则判断是否大于1，如大于1，则加桥梁;result++;
			//将桥梁数存储到新数组numbers中
			//最后取数组numbers最大的数字+1即为可获得的最多糖果数。
			numbers[i]=result;
		}
		System.out.println();
	}

}
