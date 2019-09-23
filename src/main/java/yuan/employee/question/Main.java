package yuan.employee.question;

//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
import java.util.Scanner;
//import java.util.Vector;

public class Main {
//贪婪算法
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
		//对集合元素进行排序
		//Collections.sort(list);
		
		Arrays.sort(data);
		
		/*
		 * for (int i = 0; i < data.length; i++) { data[i]=(int)list.get(i); }
		 */
		int i =0;
		int count=0;//新增题数量
		if(number==1) {
			count =2;
		}
		else {
			for (i = 0; i<number-2;) {//i<number-2 为了避免下标越界
				//判断前两道题是否满足条件 b-a<=10
				if((data[i+1]-data[i])<=10) {
					//判断二三道题是否满足条件 c-b<=10
					if((data[i+2]-data[i+1])<=10) {
						
						Flag[i]=true;
						Flag[i+1]=true;
						Flag[i+2]=true;
						i+=3;
						continue;
						//前三道题满足条件，开始判断第四道题
					}
					else {
						//不满足c-b<=10 需要添加一道使其成立   b<d<c
						Flag[i]=true;
						Flag[i+1]=true;
						count++;
						i+=2;
						continue;
					}
				}else {//前两道题不满足条件b-a<=10
					if((data[i+1]-data[i])<=20) {
						//前两道题相差并不太大，增加一道题使其成立
						Flag[i]=true;
						Flag[i+1]=true;
						count++;
						i+=2;
						continue;
					}else {
						//前两道题相差过大，增加两道题使其成立。（此项题目难度比较离散，需要为其增添两道题目才能组成一次考试）
						count+=2;
						Flag[i]=true;
						Flag[i+1]=true;
						i+=2;
						continue;
					}
					
				}
			
			}
			//判断完成，需要将难度最大的两道题进行判断
			if(Flag[number-2]==false&&Flag[number-1]==false) {
				if(data[number-1]-data[number-2]<=20) {
					//若两道题难度接近，增加一道题；若较为离散，增加四道题
					count++;
				}
				else {
					count+=4;
				}
			}else {
				if(Flag[number-2]==true&&Flag[number-1]==true) {
					
				}else if(Flag[number-2]==true&&Flag[number-1]==false){
					//当只剩最难的题没有进行分配时
					count+=2;
				}
			}
		}
		System.out.println(count);
	}

}
