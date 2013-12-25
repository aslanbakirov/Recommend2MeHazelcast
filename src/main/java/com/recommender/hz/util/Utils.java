package com.recommender.hz.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

	public static Map<Integer, ArrayList<String>> productIdScoreParser(
			List<String> list) {

		Map<Integer, ArrayList<String>> result = new HashMap<>();
		List<String> tempList = new ArrayList<>();

		int userId = 1;
		String strTemp = "";

		for (String str : list) {
			String[] split = str.split(",");
			for (String s : split) {
				if (s.startsWith("List")) {
					s = s.substring(5);
					userId = new Integer(s.split(":")[0]);
					strTemp = strTemp.concat(s.split(":")[1] + ":"+ s.split(":")[2]);
					tempList.add(strTemp);
					strTemp = "";
				} else if (s.endsWith(")")) {
					strTemp = strTemp.concat(s.split(":")[1]+ ":"+ s.split(":")[2].substring(0,s.split(":")[2].length() - 1));
					tempList.add(strTemp);
					strTemp = "";
				} else {
					strTemp = strTemp.concat(s.split(":")[1] + ":"
							+ s.split(":")[2]);
					tempList.add(strTemp);
					strTemp = "";
				}
			}

			String[] array = listToArray(tempList);
			tempList.clear();
			insertionSortOfMap(array);
			List<String> list2 = Arrays.asList(array);
			Collections.reverse(list2);
			result.put(userId, getSublist(list2, 3));
		}
		return result;
	}

	public static void insertionSortOfMap(String toSort[]) {
		for (int i = 0; i < toSort.length; i++) {
			String temp = toSort[i];
			Double value = new Double(toSort[i].split(":")[1]);
			int j = i - 1;
			while (j >= 0 && new Double(toSort[j].split(":")[1]) > value) {
				toSort[j + 1] = toSort[j];
				j = j - 1;
			}
			toSort[j + 1] = temp;

		}
	}

	public static String[] listToArray(List<String> list) {
		String[] arr = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}

		return arr;
	}
	
	public static ArrayList<String> getSublist(List<String> list, int offset){
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0;i<offset; i++){
			result.add(list.get(i));
		}
		return result;
	}

}
