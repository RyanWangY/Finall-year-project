package yuan.project.utils;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main (String[] args) {	
		/*
		 * //��������ʱ�� System.out.println("found Prime"); long
		 * startTime=System.currentTimeMillis();
		 * 
		 * List<Integer> isPrimenumber = new ArrayList<Integer>();//��������洢���� boolean[]
		 * checkPrimenumber = new boolean[600];//�������ԣ�Ҫ��100��������������Ҫ600����Ȼ����1-600�� for
		 * (int i = 2; i < checkPrimenumber.length; i++) {// ��2��ʼ for (int j = i + 1; j
		 * < checkPrimenumber.length; j++) {//j=i+1 ��Ϊi��j���ܳ�������i+1ѭ�����С� if (j % i == 0)
		 * { checkPrimenumber[j] = true;// �����Ա�������������ֵΪtrue } } } for (int i = 2; i <
		 * checkPrimenumber.length; i++) {//0��1������������2��ʼ if (checkPrimenumber[i] ==
		 * false) { isPrimenumber.add(i); //System.out.println(i); } }
		 * System.out.println(isPrimenumber.get(99));//��ȡ��100������
		 * 
		 * //��������ʱ�� long endTime=System.currentTimeMillis();
		 * System.out.println("����ʱ��:"+(endTime-startTime)+"ms");
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
