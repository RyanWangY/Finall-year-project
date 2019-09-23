package yuan.project.dao.algorithm.apriori;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import yuan.project.entity.Outbound_Entity;
import yuan.project.utils.Dbmanage;

public class Apriori {
	private final static int SUPPORT = 6; // ֧�ֶ���ֵ minimum support
	private final static double CONFIDENCE = 0.7; // ���Ŷ���ֵ minimum confidence
 
	private final static String ITEM_SPLIT = ","; // ��֮��ķָ���  Separator between items
	private final static String CON = "-->"; // ��֮��ķָ���  Separator between items
 
	
	// ��ʼ�����׼�¼����apriori�㷨�У�Ӧ��֤��е����������
 
	//Initialize the transaction record. 
	//In the apriori algorithm, should ensure that the items in the item set are ordered.
	
	public Map<String, Integer> getFC(ArrayList<String> dataList) {
		// ���е�Ƶ����
		//Store all frequent sets
		Map<String, Integer> frequentCollectionMap = new HashMap<String, Integer>();
 
		frequentCollectionMap.putAll(getItem1FC(dataList));
 
		Map<String, Integer> itemkFcMap = new HashMap<String, Integer>();
		itemkFcMap.putAll(getItem1FC(dataList));
		while (itemkFcMap != null && itemkFcMap.size() != 0) {
			Map<String, Integer> candidateCollection = getCandidateCollection(itemkFcMap);
			Set<String> ccKeySet = candidateCollection.keySet();
			// �Ժ�ѡ��������ۼӼ���
			//Cumulative count of candidate set items
			
			for (String trans : dataList) {
				for (String candidate : ccKeySet) {
					boolean flag = true;
					// �����жϽ������Ƿ���ָú�ѡ�������֣�������1
					//Used to determine whether the candidate appears in the transaction. 
					//If it appears, the count is incremented by 1.
					
					String[] candidateItems = candidate.split(ITEM_SPLIT);
					for (String candidateItem : candidateItems) {
						if (trans.indexOf(candidateItem + ITEM_SPLIT) == -1) {
							flag = false;
							break;
						}
					}
					if (flag) {
						Integer count = candidateCollection.get(candidate);
						candidateCollection.put(candidate, count + 1);
					}
				}
			}
			// �Ӻ�ѡ�����ҵ�����֧�ֶȵ�Ƶ������
			//Get frequent set items that meet the support level from the candidate set
			itemkFcMap.clear();
			for (String candidate : ccKeySet) {
				Integer count = candidateCollection.get(candidate);
				if (count >= SUPPORT) {
					itemkFcMap.put(candidate, count);
				}
			}
			// �ϲ�����Ƶ����
			//Merge all frequent sets
			
			frequentCollectionMap.putAll(itemkFcMap);
		}
		return frequentCollectionMap;
	}
 
	private Map<String, Integer> getCandidateCollection(
			Map<String, Integer> itemkFcMap) {
		Map<String, Integer> candidateCollection = new HashMap<String, Integer>();
		
		Set<String> itemkSet1 = itemkFcMap.keySet();
		Set<String> itemkSet2 = itemkFcMap.keySet();
 
		for (String itemk1 : itemkSet1) {
			for (String itemk2 : itemkSet2) {
				// ��������
				String[] tmp1 = itemk1.split(ITEM_SPLIT);
				String[] tmp2 = itemk2.split(ITEM_SPLIT);
 
				String c = "";
				if (tmp1.length == 1) {
					//itemkFcMap��ŵ��Ǻ�ѡ1�����ʱ
					if (tmp1[0].compareTo(tmp2[0]) < 0) {
						c = tmp1[0] + ITEM_SPLIT + tmp2[0] + ITEM_SPLIT;
					}
				} else {
					boolean flag = true;//�Ƿ���Խ�������
					for (int i = 0; i < tmp1.length - 1; i++) {
						if (!tmp1[i].equals(tmp2[i])) {
							flag = false;
							break;
						}
					}
					if (flag && (tmp1[tmp1.length - 1].compareTo(tmp2[tmp2.length - 1]) < 0)) {
						c = itemk1 + tmp2[tmp2.length - 1] + ITEM_SPLIT;
					}
				}
				
				// ���м�֦
				//Pruning
				boolean hasInfrequentSubSet = false;
				// �Ƿ��з�Ƶ�������Ĭ����
				//Is there a non-frequent set of children, default none
				if (!c.equals("")) {
					String[] tmpC = c.split(ITEM_SPLIT);
					//���Ե�������ignoreIndex
					for (int ignoreIndex = 0; ignoreIndex < tmpC.length; ignoreIndex++) {
						String subC = "";
						for (int j = 0; j < tmpC.length; j++) {
							if (ignoreIndex != j) {
								subC +=  tmpC[j] + ITEM_SPLIT;
							}
						}
						if (itemkFcMap.get(subC) == null) {
							hasInfrequentSubSet = true;
							break;
						}
					}
				} else {
					hasInfrequentSubSet = true;
				}
 
				if (!hasInfrequentSubSet) {
					//�����������ĺ�ѡ���ӵ�candidateCollection ������
					//Add a candidate set that satisfies the condition to 
					//the 'candidateCollection' collection
					candidateCollection.put(c, 0);
				}
			}
		}
		return candidateCollection;
	}
 
	//�õ�Ƶ��1�
	private Map<String, Integer> getItem1FC(ArrayList<String> dataList) {
		Map<String, Integer> sItem1FcMap = new HashMap<String, Integer>();
		Map<String, Integer> rItem1FcMap = new HashMap<String, Integer>();// Ƶ��1�
 
		for (String trans : dataList) {
			String[] items = trans.split(ITEM_SPLIT);
			for (String item : items) {
				Integer count = sItem1FcMap.get(item + ITEM_SPLIT);
				if (count == null) {
					sItem1FcMap.put(item + ITEM_SPLIT, 1);
				} else {
					sItem1FcMap.put(item + ITEM_SPLIT, count + 1);
				}
			}
		}
 
		Set<String> keySet = sItem1FcMap.keySet();
		for (String key : keySet) {
			Integer count = sItem1FcMap.get(key);
			if (count >= SUPPORT) {
				rItem1FcMap.put(key, count);
			}
		}
		return rItem1FcMap;
	}
 
	//����Ƶ������ϵõ���������
	//Get association rules based on frequent itemsets
	
	public Map<String, Double> getRelationRules(
			Map<String, Integer> frequentCollectionMap) {
		Map<String, Double> relationRules = new HashMap<String, Double>();
		Set<String> keySet = frequentCollectionMap.keySet();
		for (String key : keySet) {
			double countAll = frequentCollectionMap.get(key);
			String[] keyItems = key.split(ITEM_SPLIT);
			if (keyItems.length > 1) {
				List<String> source = new ArrayList<String>();
				Collections.addAll(source, keyItems);
				List<Set<String>> result = new ArrayList<Set<String>>();
				
				// ���source�����зǿ��Ӽ�
				//Get all non-empty subsets of source
				
				buildSubSet(source, result);
				
				for (Set<String> itemList : result) {
					// ֻ�������Ӽ�
					//Only process true subsets
					
					if (itemList.size() < source.size()) {
						List<String> otherList = new ArrayList<String>();
						//��¼һ���Ӽ��Ĳ�
						for (String sourceItem : source) {
							if (!itemList.contains(sourceItem)) {
								otherList.add(sourceItem);
							}
						}
						String reasonStr = "";// �����ǰ��
						String resultStr = "";// ����Ľ��
						
						for (String item : itemList) {
							reasonStr += item + ITEM_SPLIT;
						}
						for (String item : otherList) {
							resultStr = resultStr + item + ITEM_SPLIT;
						}
						double countReason = frequentCollectionMap
								.get(reasonStr);
						// �������Ŷ�
						//Calculate confidence
						double itemConfidence = countAll / countReason;
						
						if (itemConfidence >= CONFIDENCE) {
							String rule = reasonStr + CON + resultStr;
							relationRules.put(rule, itemConfidence);
						}
					}
				}
			}
		}
		return relationRules;
	}
 
	private void buildSubSet(List<String> sourceSet, List<Set<String>> result) {
		int n = sourceSet.size();
		//n��Ԫ����2^n-1���ǿ��Ӽ�
		int num = (int) Math.pow(2, n);
		for (int i = 1; i < num; i++) {
			String binary = Integer.toBinaryString(i);
			int size = binary.length();
			for (int k = 0; k < n-size; k++) {//�������Ʊ�ʾ�ַ����Ҷ��룬��߲�0
				binary = "0"+binary;
			}
			Set<String> set = new TreeSet<String>();
			for (int index = 0; index < sourceSet.size(); index++) {
				if(binary.charAt(index) == '1'){
					set.add(sourceSet.get(index));
				}
			}
			result.add(set);
		}
	}
 
	}
