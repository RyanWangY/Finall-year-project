package yuan.project.utils;

import java.util.List;
import java.util.Scanner;
public class ATest {
	 
	//最短寻道时间优先
	 
		private int shortIndex=0;    
		private int visit[];    //访问序列
	 
		public int[] sstf(int data[],int begin){
			int nearest=99999;  //最近的值，变量
			visit=new int[data.length];
			for(int j=0;j<data.length;j++){
				for(int i=0;i<data.length;i++){
					if(data[i]!=-1){
						if(Math.abs(nearest-begin)>Math.abs(data[i]-begin)){
							nearest=data[i];
							shortIndex=i;
						}
					}
				}
				visit[j]=nearest;
				data[shortIndex]=-1;
				begin=nearest;
				nearest=99999;
			}
			return visit;
		}
	    public void print(int visit[],int begin){
	    	for(int i=0;i<visit.length;i++){
	    		System.out.print(visit[i]+"     ");
	    	}
	    	int length=0;
	    	for(int i=0;i<visit.length-1;i++){
	    		length+=Math.abs(visit[i]-visit[i+1]);
	    	}
	    	length+=Math.abs(begin-visit[0]);
	    	System.out.println("平均寻道长度为"+length/visit.length);
	    }
		public static void main (String[] args) {
			Scanner scanner=new Scanner(System.in);
			System.out.println("请输入请求个数");
			int num=scanner.nextInt();
			System.out.println("请输入请求序列，空格隔开");
			int data[]=new int[num];
			for(int i=0;i<num;i++){
				data[i]=scanner.nextInt();
			}
			ATest sstf=new ATest();
	        System.out.println("请输入起始头位置");
	        int begin=scanner.nextInt();
	        sstf.print(sstf.sstf(data,begin),begin);
		}
	//98 183 37 122 14 124 65 67
	}


