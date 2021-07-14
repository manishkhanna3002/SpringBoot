package com.rahulshettyacademy.SpringBootRestService.Controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
@Autowired
Greeting greeting;
//Autowired annotation automatically creates object for your class so you don't need
//to allocate memory like Greeting greeting = new Greeting and then greeting.methodname

AtomicLong counter = new AtomicLong(); //Inbuilt class in Java
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name")String name)
	//Entity or Java bean or plain old Java objects or Resource representational class
	{
		greeting.setId(counter.incrementAndGet());
		greeting.setContent("Hey I am learning Spring Boot from"+name);
		return greeting;//Springboot uses Jackson utility to return the output in JSON format
	/*Google "Jackson library java from java object to json"
		output will be as follows:
		"id"=0,
		content="Hey I am learning Spring Boot from Shetty"
		}*/
	}
	
}

/* The @Controller is a common annotation that is used to mark a class as Spring MVC Controller while @RestController is a special controller used in RESTFul web services and the equivalent of @Controller + @ResponseBody.*/
