package yuan.employee.question;

import java.util.Scanner;

public class T2048 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int vec = scanner.nextInt();
		
		int data[][] = new int [4][4];
		
		//将矩阵装入数组
				for (int i = 0; i < 4;i++) {
					for (int j = 0; j < 4; j++) {
						data[i][j]=scanner.nextInt();				
					}					
				}
				scanner.close();
				
			if(vec==1) {
				for (int i = 0; i <4; i++) {
						for (int j = 0; j < data.length; j++) {
							if(i+1<4) {
								if(data[i+1][j]==data[i][j]) {
									data[i][j]=data[i][j]+data[i+1][j];
									data[i+1][j]=0;
								}	
						}											
						}			
				}			
			}
			if(vec==2) {
				for (int i = 3; i >=0; i--) {
						for (int j = 0; j < data.length; j++) {
							if(i-1>=0) {
								if(data[i][j]==data[i-1][j]) {
									data[i][j]=data[i][j]+data[i-1][j];
									data[i-1][j]=0;
								}
							}						
						}					
				}
			}
			if(vec==3) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < data.length; j++) {
						if(i+1<4) {
						if(data[j][i+1]==data[j][i]) {
							data[j][i]=data[j][i]+data[j][i+1];
							data[j][i+1]=0;
						}
					}	
					}
				}				
			}
			if(vec==4) {
				for (int i = 3; i >=0; i--) {
					for (int j = 0; j < data.length; j++) {
						if(i-1>=0) {
					if(data[j][i-1]==data[j][i]) {
						data[j][i]=data[j][i]+data[j][i-1];
						data[j][i-1]=0;
					}
					}
					}
				}
			}
	
	}
}
