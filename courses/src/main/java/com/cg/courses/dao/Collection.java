package com.cg.courses.dao;

import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import com.cg.courses.pojo.Topics;

@Repository
public class Collection {

	private static TreeSet<Topics> topics = new TreeSet<Topics>();
	
	static 
	{
	  topics.add(new Topics(1, "C"));
	  topics.add(new Topics(2, "C++"));
	  topics.add(new Topics(3, "java"));
	  topics.add(new Topics(4, "C#"));
	  topics.add(new Topics(5, "Spring"));
	  topics.add(new Topics(6, "SpringBoot"));
	
	}
	
	public  TreeSet<Topics> getAllTopics() {
		return topics;
	}

	public void addTopic(Topics topic) {
		topics.add(topic);
	}

	public void updateTopic(int id, Topics topic) {
	
		for(Topics topicIterated : topics)
		{
			if(topicIterated.getId()==id)
			{
				topics.remove(topicIterated);
				break;
			}
		}
		
		topics.add(topic);
	}

	public void deleteTopic(int id, Topics topic) {
		
		for(Topics topicIterated : topics)
		{
			if(topicIterated.getId()==id)
			{
				topics.remove(topicIterated);
				break;
			}
		}
		
	}
}
