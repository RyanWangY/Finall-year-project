package yuan.employee.question;

//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
import java.util.Scanner;
//import java.util.Vector;

public class Main {
//̰���㷨
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		int data[] = new int [number];
		boolean Flag[]= new boolean[number];
		//Vector<Integer> vector = new Vector<Integer>();
		//ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < data.length; i++) {
			//vector.add(in.nextInt());
			//list.add(in.nextInt());
			data[i]=in.nextInt();
			Flag[i]=false;
		}
		//�Լ���Ԫ�ؽ�������
		//Collections.sort(list);
		
		Arrays.sort(data);
		
		/*
		 * for (int i = 0; i < data.length; i++) { data[i]=(int)list.get(i); }
		 */
		int i =0;
		int count=0;//����������
		if(number==1) {
			count =2;
		}
		else {
			for (i = 0; i<number-2;) {//i<number-2 Ϊ�˱����±�Խ��
				//�ж�ǰ�������Ƿ��������� b-a<=10
				if((data[i+1]-data[i])<=10) {
					//�ж϶��������Ƿ��������� c-b<=10
					if((data[i+2]-data[i+1])<=10) {
						
						Flag[i]=true;
						Flag[i+1]=true;
						Flag[i+2]=true;
						i+=3;
						continue;
						//ǰ������������������ʼ�жϵ��ĵ���
					}
					else {
						//������c-b<=10 ��Ҫ���һ��ʹ�����   b<d<c
						Flag[i]=true;
						Flag[i+1]=true;
						count++;
						i+=2;
						continue;
					}
				}else {//ǰ�����ⲻ��������b-a<=10
					if((data[i+1]-data[i])<=20) {
						//ǰ����������̫������һ����ʹ�����
						Flag[i]=true;
						Flag[i+1]=true;
						count++;
						i+=2;
						continue;
					}else {
						//ǰ����������������������ʹ���������������Ŀ�ѶȱȽ���ɢ����ҪΪ������������Ŀ�������һ�ο��ԣ�
						count+=2;
						Flag[i]=true;
						Flag[i+1]=true;
						i+=2;
						continue;
					}
					
				}
			
			}
			//�ж���ɣ���Ҫ���Ѷ���������������ж�
			if(Flag[number-2]==false&&Flag[number-1]==false) {
				if(data[number-1]-data[number-2]<=20) {
					//���������ѶȽӽ�������һ���⣻����Ϊ��ɢ�������ĵ���
					count++;
				}
				else {
					count+=4;
				}
			}else {
				if(Flag[number-2]==true&&Flag[number-1]==true) {
					
				}else if(Flag[number-2]==true&&Flag[number-1]==false){
					//��ֻʣ���ѵ���û�н��з���ʱ
					count+=2;
				}
			}
		}
		System.out.println(count);
	}

}
