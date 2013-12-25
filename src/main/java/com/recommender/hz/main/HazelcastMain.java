package com.recommender.hz.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.recommender.hz.hdfs.client.HDFSClient;
import com.recommender.hz.util.Utils;

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
		mapCfg.setName("userProductMap");
		mapCfg.setBackupCount(2);
		mapCfg.getMaxSizeConfig().setSize(100000);
		mapCfg.setTimeToLiveSeconds(300);
		mapCfg.setInMemoryFormat(InMemoryFormat.OBJECT);
		
		
		
		map.put("userProductMap", mapCfg);
		//cfg.setMapConfigs(map);
		
		
		HazelcastInstance hz = Hazelcast.newHazelcastInstance(cfg);
		Map<Integer, ArrayList<String>> userProductMap = hz.getMap("userProductMap");
		
		HDFSClient client = new HDFSClient();
		
		Map<Integer, ArrayList<String>> map2 = Utils.productIdScoreParser(client.readFromHDFS("hdfs://10.100.8.55:8020/foodData/recommendationList/part-00000"));
		
		
		for(Integer key: map2.keySet()){
			userProductMap.put(key, map2.get(key));
		}
		
		Map<String, Integer> userIDMap = hz.getMap("idUserMap");
		userIDMap.put("test", 1);
		userIDMap.put("aslan", 10);
		userIDMap.put("fat", 100);
		
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
