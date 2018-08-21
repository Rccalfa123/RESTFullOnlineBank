package com.cg.courses.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Topics implements Comparable<Topics>{

	private int Id;
	private String name;
	
	public Topics(int id, String name) {
		super();
		Id = id;
		this.name = name;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Topics [Id=" + Id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Topics arg0) {
		return this.Id-arg0.Id;
	}
	
	
	
}
