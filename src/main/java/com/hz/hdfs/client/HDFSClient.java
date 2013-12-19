package com.hz.hdfs.client;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSClient {
	
	Configuration conf = new Configuration();
	FileSystem fs=null;
	Path outFile=null;
	Path inFile= null;
	FSDataOutputStream out=null;
	FSDataInputStream in=null;
	
	public boolean writeToHDFS(String strToWrite, String filePath){
		
		try{
		conf.addResource(new Path("/opt/hadoop-1.0.4/conf/hdfs-site.xml"));
		conf.addResource(new Path("/opt/hadoop-1.0.4/conf/core-site.xml"));
		
		 fs = FileSystem.get(conf);
		 outFile = new Path(filePath);
		
		if (!fs.exists(outFile)){
			  System.out.println("Output file does not exist..");
			  return false;
		}
		
		 out = fs.create(outFile);
		
		out.write(strToWrite.getBytes());
		out.write("\n".getBytes());
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
		
	}
	
   public String readFromHDFS(String filePath){
		
	   StringBuilder sb=new StringBuilder();
	   
	   String line=null;
	   
		try{
		conf.addResource(new Path("/opt/hadoop-1.0.4/conf/hdfs-site.xml"));
		conf.addResource(new Path("/opt/hadoop-1.0.4/conf/core-site.xml"));
		
		 fs = FileSystem.get(conf);
		 inFile = new Path(filePath);
		
		if (!fs.exists(inFile)){
			  System.out.println("Input file does not exist..");
			  return null;
		}
		
		in = fs.open(inFile);
		
		while((line = in.readLine())!=null){
			sb.append(line);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
		
	}
	
   public String loadMapFromHDFS(String filePath){
		
	   StringBuilder sb=new StringBuilder();
	   
	   String line=null;
	   
		try{
		conf.addResource(new Path("/opt/hadoop-1.0.4/conf/hdfs-site.xml"));
		conf.addResource(new Path("/opt/hadoop-1.0.4/conf/core-site.xml"));
		
		 fs = FileSystem.get(conf);
		 inFile = new Path(filePath);
		
		if (!fs.exists(inFile)){
			  System.out.println("Input file does not exist..");
			  return null;
		}
		
		in = fs.open(inFile);
		
		while((line = in.readLine())!=null){
			
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
		
	}

}
