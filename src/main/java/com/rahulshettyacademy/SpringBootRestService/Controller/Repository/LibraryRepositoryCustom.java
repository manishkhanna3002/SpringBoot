package com.rahulshettyacademy.SpringBootRestService.Controller.Repository;

import java.util.List;

import com.rahulshettyacademy.SpringBootRestService.Controller.Library;

public interface LibraryRepositoryCustom {
	List<Library> findAllByAuthor(String authorName);
	//Interface only defines signature, it's duty of the classes to implement methods present in the interface
}
