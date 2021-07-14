package com.rahulshettyacademy.SpringBootRestService.Controller;

import org.springframework.stereotype.Component;

@Component/*This annotation will indicate that this code is bean and it will inject
into the controller which is greetingcontroller in this case*/
public class Greeting {

	private long id;//If made final long id then this id can't be changed outside of this class
	private String content;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
