package com.recommender.hz.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.recommender.hz.util.Utils;

import junit.framework.TestCase;

public class UtilsTest extends TestCase{
	
	public void testUtil(){
		String str1= "List(10000:5:1.4183377591052964, 10000:4:0.8828437521758593, 10000:3:0.7040203805665246, 10000:2:1.0291824224118336, 10000:1:1.1284446782654705)";
		String str2 = "List(1000:5:5.0927824251253035, 1000:4:3.814693015800801, 1000:3:2.4140916976439377, 1000:2:3.2067633843139323, 1000:1:4.287157097421455)";
		String str3 = "List(100:5:0.20498746023038433, 100:4:0.1913270448575777, 100:3:0.10452627531073541, 100:2:0.1870000459332104, 100:1:0.5369597129052311)";
		String str4 = "List(10:5:2.4405385596447906, 10:4:1.7535529878157972, 10:3:0.9934104712859443, 10:2:1.9570107951847242, 10:1:1.3053217863442035)";
		String str5 = "List(1:5:4.703947251397262, 1:4:4.2504718161199495, 1:3:2.175649619540934, 1:2:3.7624084828753324, 1:1:4.282830297191432)";
		
		List<String> list= new ArrayList<>();
		list.add(str5);
		list.add(str4);
		list.add(str3);
		list.add(str2);
		list.add(str1);
		
		Map<Integer, ArrayList<String>> map = Utils.productIdScoreParser(list);
		
		for(Integer key:map.keySet()){
			for(String str : map.get(key)){
				System.out.println( key +":" + str);
			}
		}
		
	}
}
