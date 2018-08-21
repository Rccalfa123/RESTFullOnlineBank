package com.cg.courses.service;

import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.courses.dao.Collection;
import com.cg.courses.pojo.Topics;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private Collection collection;
	
	public  TreeSet<Topics> getAllToipcs() {
	
		return collection.getAllTopics();
		
	}

	public void addTopic(Topics topic) {
	
	  collection.addTopic(topic);
		
	}

	public void updateTopic(int id, Topics topic) {
		
		collection.updateTopic(id,topic);
		
	}

	public void deleteTopic(int id, Topics topic) {
		
		collection.deleteTopic(id,topic);
		
	}

	

}
