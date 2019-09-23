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
	private final static int SUPPORT = 6; // 支持度阈值 minimum support
	private final static double CONFIDENCE = 0.7; // 置信度阈值 minimum confidence
 
	private final static String ITEM_SPLIT = ","; // 项之间的分隔符  Separator between items
	private final static String CON = "-->"; // 项之间的分隔符  Separator between items
 
	
	// 初始化交易记录，在apriori算法中，应保证项集中的项是有序的
 
	//Initialize the transaction record. 
	//In the apriori algorithm, should ensure that the items in the item set are ordered.
	
	public Map<String, Integer> getFC(ArrayList<String> dataList) {
		// 所有的频繁集
		//Store all frequent sets
		Map<String, Integer> frequentCollectionMap = new HashMap<String, Integer>();
 
		frequentCollectionMap.putAll(getItem1FC(dataList));
 
		Map<String, Integer> itemkFcMap = new HashMap<String, Integer>();
		itemkFcMap.putAll(getItem1FC(dataList));
		while (itemkFcMap != null && itemkFcMap.size() != 0) {
			Map<String, Integer> candidateCollection = getCandidateCollection(itemkFcMap);
			Set<String> ccKeySet = candidateCollection.keySet();
			// 对候选集项进行累加计数
			//Cumulative count of candidate set items
			
			for (String trans : dataList) {
				for (String candidate : ccKeySet) {
					boolean flag = true;
					// 用来判断交易中是否出现该候选项，如果出现，计数加1
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
			// 从候选集中找到符合支持度的频繁集项
			//Get frequent set items that meet the support level from the candidate set
			itemkFcMap.clear();
			for (String candidate : ccKeySet) {
				Integer count = candidateCollection.get(candidate);
				if (count >= SUPPORT) {
					itemkFcMap.put(candidate, count);
				}
			}
			// 合并所有频繁集
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
				// 进行连接
				String[] tmp1 = itemk1.split(ITEM_SPLIT);
				String[] tmp2 = itemk2.split(ITEM_SPLIT);
 
				String c = "";
				if (tmp1.length == 1) {
					//itemkFcMap存放的是候选1项集集合时
					if (tmp1[0].compareTo(tmp2[0]) < 0) {
						c = tmp1[0] + ITEM_SPLIT + tmp2[0] + ITEM_SPLIT;
					}
				} else {
					boolean flag = true;//是否可以进行连接
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
				
				// 进行剪枝
				//Pruning
				boolean hasInfrequentSubSet = false;
				// 是否有非频繁子项集，默认无
				//Is there a non-frequent set of children, default none
				if (!c.equals("")) {
					String[] tmpC = c.split(ITEM_SPLIT);
					//忽略的索引号ignoreIndex
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
					//把满足条件的候选项集添加到candidateCollection 集合中
					//Add a candidate set that satisfies the condition to 
					//the 'candidateCollection' collection
					candidateCollection.put(c, 0);
				}
			}
		}
		return candidateCollection;
	}
 
	//得到频繁1项集
	private Map<String, Integer> getItem1FC(ArrayList<String> dataList) {
		Map<String, Integer> sItem1FcMap = new HashMap<String, Integer>();
		Map<String, Integer> rItem1FcMap = new HashMap<String, Integer>();// 频繁1项集
 
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
 
	//根据频繁项集集合得到关联规则
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
				
				// 获得source的所有非空子集
				//Get all non-empty subsets of source
				
				buildSubSet(source, result);
				
				for (Set<String> itemList : result) {
					// 只处理真子集
					//Only process true subsets
					
					if (itemList.size() < source.size()) {
						List<String> otherList = new ArrayList<String>();
						//记录一个子集的补
						for (String sourceItem : source) {
							if (!itemList.contains(sourceItem)) {
								otherList.add(sourceItem);
							}
						}
						String reasonStr = "";// 规则的前置
						String resultStr = "";// 规则的结果
						
						for (String item : itemList) {
							reasonStr += item + ITEM_SPLIT;
						}
						for (String item : otherList) {
							resultStr = resultStr + item + ITEM_SPLIT;
						}
						double countReason = frequentCollectionMap
								.get(reasonStr);
						// 计算置信度
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
		//n个元素有2^n-1个非空子集
		int num = (int) Math.pow(2, n);
		for (int i = 1; i < num; i++) {
			String binary = Integer.toBinaryString(i);
			int size = binary.length();
			for (int k = 0; k < n-size; k++) {//将二进制表示字符串右对齐，左边补0
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
