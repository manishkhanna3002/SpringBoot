package com.rahulshettyacademy.SpringBootRestService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rahulshettyacademy.SpringBootRestService.Controller.Library;
import com.rahulshettyacademy.SpringBootRestService.Controller.Repository.LibraryRepository;



@SpringBootApplication
public class SpringBootRestServiceApplication implements CommandLineRunner{
@Autowired
LibraryRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}

	@Override
	public void run(String[] args)
	{
		Library lib = repository.findById("fdsefr343").get();
		System.out.println(lib.getAuthor());
		Library en = new Library();
		en.setAisle(123);
		en.setAuthor("Rahul");
		en.setBook_name("Devops");
		en.setIsbn("lkhs");
		en.setId("lkhs123");
		repository.save(en);
		List<Library> allrecords = repository.findAll();
		for (Library item : allrecords)//for enhanced loop, item is an object of the list Library
		{
			item.getAuthor();
		}
		//repository.delete(en);
	}
}
