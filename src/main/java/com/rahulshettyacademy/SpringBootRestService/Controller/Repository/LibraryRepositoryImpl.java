package com.rahulshettyacademy.SpringBootRestService.Controller.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rahulshettyacademy.SpringBootRestService.Controller.Library;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom
{
	@Autowired
	LibraryRepository repository;
	
	@Override
	public List<Library> findAllByAuthor(String authorName)
	{
		List<Library>bookswithAuthor = new ArrayList<Library>();
		List<Library>books = repository.findAll();
		for (Library item : books)
		{
			System.out.println(item.getBook_name());
			if (item.getAuthor().equalsIgnoreCase("authorName"))
			{
				bookswithAuthor.add(item);
			}
		}
		return bookswithAuthor;
		
	}
}
