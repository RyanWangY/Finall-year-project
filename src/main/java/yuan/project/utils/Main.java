package yuan.project.utils;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("请输入大楼数量");
		int number = in.nextInt();	
		int data[] = new int[number];
		int count =0;
		String result = "";
		System.out.println("请输入大楼高度");
		int index=0;
		for(int i=0;i<number;i++){
			data[i]=in.nextInt();
		}
		Main b = new Main();
		for (int i = 0; i < data.length; i++) {
			index =i;
			count =b.coutbuilding(data,index);
			result += count;
			//System.out.println(count);
		}
		

		System.out.println(result);
	}

	private int coutbuilding(int[] data, int index) {
		int count=1;
		if (index==data.length) {
			 count=2;
			 int max =0;
			for (int i = data.length-1; i>=1;i--) {
				for(int j=1;j<index;j++) {
					max=data[i-j];
					if(max<data[i-(j+1)]) {
						max=data[i-(j+1)];
						count ++;
					}
				}
				
				
			}
		}
		if (index==0) {
			count=2;
			int max =0;
			for (int i = 0; i<data.length-3;i++) {					
					max=data[1+i];
					if(max<data[2+i]) {
						max=data[2+i];
						count ++;
					}
				}

			}
		
		if(0<=index&&index<=data.length-1) {
			count =3;
			int max =0;
			for (int i = index-1; i>0; i--) {
				max=data[i];
				if(data[i]<data[i-1]) {
					max=data[i-1];
					count ++;
				}				
			}
			for(int j=index;j<data.length-3;j++) {
				max=data[j+1];
				if(max<data[j+2]) {
					max=data[j+2];
					count ++;
				}
			}
		}
		
		return count;
	}
}
	/*
	 * private String deCode(String str) {
	 * 
	 * if(str.indexOf("[")==-1) {
	 * 
	 * 
	 * }else { deCode(str); }
	 * 
	 * return null; }
	 */
	
