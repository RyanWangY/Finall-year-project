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
			//�ж�����֮���Ƿ���ڹ�Լ��
			//�����ڣ����ж��Ƿ����1�������1���������;result++;
			//���������洢��������numbers��
			//���ȡ����numbers��������+1��Ϊ�ɻ�õ�����ǹ�����
			numbers[i]=result;
		}
		System.out.println();
	}

}
