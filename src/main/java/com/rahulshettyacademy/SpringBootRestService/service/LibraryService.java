package com.rahulshettyacademy.SpringBootRestService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahulshettyacademy.SpringBootRestService.Controller.Library;
import com.rahulshettyacademy.SpringBootRestService.Controller.Repository.LibraryRepository;

//import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class LibraryService {
	
	@Autowired
	LibraryRepository repository;
	
	public String buildId (String isbn, int aisle) 
	{
		if (isbn.startsWith("Z"))
		{
		return "OLD"+isbn+aisle;
		}
		
		return isbn + aisle;
	}

	public boolean CheckBookAlreadyExists(String id)
	{
		Optional<Library> lib = repository.findById(id);
		if(lib.isPresent())
			return true;
			else
			return false;
	}
}
