package com.hz.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastMain {

	public static void main(String[] args) {
		Config cfg = new Config();
		Map<String, MapConfig> map=new HashMap<String, MapConfig>(); 
		
		NetworkConfig network = cfg.getNetworkConfig();
		JoinConfig join = network.getJoin();
		join.getMulticastConfig().setEnabled(false);
		join.getTcpIpConfig().addMember("10.100.8.55").setEnabled(true);
		network.getInterfaces().setEnabled(true).addInterface("10.100.8.*");
		
		MapConfig mapCfg = new MapConfig();
		mapCfg.setName("userProductMapCfg");
		mapCfg.setBackupCount(2);
		mapCfg.getMaxSizeConfig().setSize(100000);
		mapCfg.setTimeToLiveSeconds(300);
		
		map.put("userProductMapCfg", mapCfg);
		cfg.setMapConfigs(map);
		
		HazelcastInstance hz = Hazelcast.newHazelcastInstance(cfg);
		Map<Integer, List<Integer>> userProductMap = hz.getMap("userProductMap");
		List<Integer> list1= new ArrayList<Integer>() {{
		    add(1);
		    add(2);
		    add(3);
		}};
		List<Integer> list2= new ArrayList<Integer>() {{
		    add(4);
		    add(5);
		    add(6);
		}};
		List<Integer> list3= new ArrayList<Integer>() {{
		    add(7);
		    add(8);
		    add(9);
		}};
		userProductMap.put(1,list1 );
		userProductMap.put(2, list2);
		userProductMap.put(3, list3);
		
		Map<String, Integer> userIDMap = hz.getMap("idUserMap");
		userIDMap.put("test", 1);
		userIDMap.put("aslan", 2);
		userIDMap.put("kevser", 3);
		
		Map<Integer,String> idProductMap = hz.getMap("idProductMap");
		idProductMap.put(1, "pizza");
		idProductMap.put(2, "borek");
		idProductMap.put(3,"adana");
		idProductMap.put(4, "urfa");
		idProductMap.put(5, "baklava");
		idProductMap.put(6, "pilav");
		idProductMap.put(7, "bamya");
		idProductMap.put(8, "fasulye");
		idProductMap.put(9, "cola");
	}

}
