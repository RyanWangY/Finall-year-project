package yuan.employee.question;

import java.util.Scanner;

public class DouYouMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		//���վ����С������=����
		int numbers=scanner.nextInt();
		
		int[][] data=new int[numbers][numbers];
		
		//������װ������
		for (int i = 0; i < numbers;i++) {
			for (int j = 0; j < numbers; j++) {
				data[i][j]=scanner.nextInt();				
			}					
		}
		scanner.close();
		//��ʼ������ƿ����
		int DouYouP=0;
		//�ж��Ƿ�Ϊ����ƿ
		//��һ���������,����һ���û�
		if(numbers==1) {
			DouYouP=1;
			System.out.println(DouYouP);
		}
		else {
			for (int i = 0; i < data.length-2; i++) {
				//����i���û����i+1���û������������Σ�����ƿ����+1
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
