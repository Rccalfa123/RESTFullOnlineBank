package com.cg.courses.controller;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.courses.pojo.Topics;
import com.cg.courses.service.Service;

@RestController
public class TopicController {

	@Autowired
	private Service service;
	
	@RequestMapping(value="/topics",method=RequestMethod.GET, produces={"application/pdf","application/json","application/xml"},consumes = {"application/pdf","application/json","application/xml"} )
	public TreeSet<Topics> getAllTopics()
	{
		return service.getAllToipcs();
	}
	
	
	@RequestMapping(value="/topics", method=RequestMethod.POST)
	public String addTopic(@RequestBody Topics topic)
	{
		service.addTopic(topic);
		
		return "Added SuccessFully";
	}
	
	@RequestMapping(value="/topics/{id}", method=RequestMethod.PUT)
	public String updateTopic(@RequestBody Topics topic, @PathVariable int id)
	{
		service.updateTopic(id,topic);
		
		return "updated SuccessFully";
	}
	
	@RequestMapping(value="/topics/{id}", method=RequestMethod.DELETE)
	public String deleteTopic(@RequestBody Topics topic, @PathVariable int id)
	{
		service.deleteTopic(id,topic);
		
		return "deleted SuccessFully";
	}
}
