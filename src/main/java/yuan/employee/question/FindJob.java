package yuan.employee.question;

//import java.util.Arrays;
//import java.util.Iterator
//import java.util.Set;
//import java.util.Map.Entry;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FindJob {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		//get job number
		int job_number = scanner.nextInt();
		//get friend number
		int friend_number = scanner.nextInt();
		int data[] = new int[friend_number];
		
		//初始化数据结构treemap用于存储数据
		//treemap 默认升序
		TreeMap<Integer, Integer> map =new TreeMap<Integer, Integer>();
		//
		for (int i = 0; i < job_number; i++) {
			map.put(scanner.nextInt(), scanner.nextInt());
		}
		//用于存储朋友能力值
		for (int i = 0; i < friend_number; i++) {
			data[i]= scanner.nextInt();
		}
		scanner.close();
		//Arrays.sort(data);
		for (int j = 0; j < data.length; j++) {
			int salary = 0;
			int tmp=0;
			  for(Map.Entry<Integer,Integer> entry:map.entrySet()) {
			  	if(data[j]>=entry.getKey()) {
			  		tmp=entry.getValue();
			  		if(salary<tmp) {
			  			salary=tmp;
			  		}

				}
			  }
			System.out.println(salary);
		}
		
	}

}
