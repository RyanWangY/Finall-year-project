package yuan.employee.question;

import java.util.Scanner;

public class DouYouMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		//接收矩阵大小，列数=行数
		int numbers=scanner.nextInt();
		
		int[][] data=new int[numbers][numbers];
		
		//将矩阵装入数组
		for (int i = 0; i < numbers;i++) {
			for (int j = 0; j < numbers; j++) {
				data[i][j]=scanner.nextInt();				
			}					
		}
		scanner.close();
		//初始化豆油瓶数量
		int DouYouP=0;
		//判断是否为豆油瓶
		//第一种特殊情况,仅有一个用户
		if(numbers==1) {
			DouYouP=1;
			System.out.println(DouYouP);
		}
		else {
			for (int i = 0; i < data.length-2; i++) {
				//若第i个用户与第i+1个用户互动超过三次，则豆油瓶数量+1
				for (int j = 1; i+j<data.length-1; j++) {
					if(i+j<data.length-1) {
						if(data[i][i+j]>=3) {
							if(data[i+j][i+j+1]>=3) {
								DouYouP++;
							}else {
								DouYouP+=2;
							}					
					}
				}
				}			
		}
		
		System.out.println(DouYouP);
	}

}
}
