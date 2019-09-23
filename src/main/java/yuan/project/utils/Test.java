package yuan.project.utils;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main (String[] args) {	
		/*
		 * //计算运行时间 System.out.println("found Prime"); long
		 * startTime=System.currentTimeMillis();
		 * 
		 * List<Integer> isPrimenumber = new ArrayList<Integer>();//定义数组存储质数 boolean[]
		 * checkPrimenumber = new boolean[600];//经过测试，要第100个质数，至少需要600个自然数（1-600） for
		 * (int i = 2; i < checkPrimenumber.length; i++) {// 从2开始 for (int j = i + 1; j
		 * < checkPrimenumber.length; j++) {//j=i+1 因为i与j必能除尽，从i+1循环就行。 if (j % i == 0)
		 * { checkPrimenumber[j] = true;// 将可以被整除的数，赋值为true } } } for (int i = 2; i <
		 * checkPrimenumber.length; i++) {//0和1不是质数，从2开始 if (checkPrimenumber[i] ==
		 * false) { isPrimenumber.add(i); //System.out.println(i); } }
		 * System.out.println(isPrimenumber.get(99));//获取第100个质数
		 * 
		 * //计算运行时间 long endTime=System.currentTimeMillis();
		 * System.out.println("运行时间:"+(endTime-startTime)+"ms");
		 */
		
		int i=0 ; int max =100;
		int sum =0;
		do {
			if(i!=(1/9)*10)
				sum+=i;
			System.out.println(sum);
		} while (++i<max);
		System.out.println(sum);
	
	}
}
