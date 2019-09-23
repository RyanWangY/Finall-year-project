package yuan.project.utils;

import java.util.Scanner;

public class AMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("请输入纸牌数量");
		long times = in.nextLong();
		System.out.println("请输入纸牌大小");
		long data[]=new long[(int) times];
		long count_niuniu = 0;
		long count_yangyang =0;
		long result = count_niuniu-count_yangyang;
		
		
		for(int i=0;i<times;i++){		
			data[i]=in.nextInt();		
		}
		AMain a = new AMain();	
		long dd[] = a.bubble(data);
		
		  for(int j=0;j<dd.length;j++) { 
			  System.out.println(dd[j]);		
				
		  }
			System.out.println(result);		
	}
	
	public long[] bubble (long data[]) {
		long temp;
		int size = data.length;
		for (int i= size-1 ;i>=1;i--) {
			for(int j=0;j<i;j++) {
				if(data[j]>data[j+1]) {
					temp=data[j];
					data[j]=data[j+1];
					data[j+1]=temp;
				}
			}
		}
		return data;
	}
}
