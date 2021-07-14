package com.rahulshettyacademy.SpringBootRestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rahulshettyacademy.SpringBootRestService.Controller.AddResponse;
import com.rahulshettyacademy.SpringBootRestService.Controller.Library;
import com.rahulshettyacademy.SpringBootRestService.Controller.LibraryController;
import com.rahulshettyacademy.SpringBootRestService.Controller.Repository.LibraryRepository;
import com.rahulshettyacademy.SpringBootRestService.service.LibraryService;

@SpringBootTest
class SpringBootRestServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	LibraryController con;
	@MockBean
	LibraryRepository repository;
	@MockBean
	LibraryService libraryservice;
	
	@Test //This annotation comes from Junit and is used to run unit test cases
	public void checkBuildIdLogic()
	{
		LibraryService lib = new LibraryService();
		String id = lib.buildId("ZMAN", 24);
		assertEquals(id,"OLDZMAN24");
		String id1 = lib.buildId("MAN", 24);
		assertEquals(id,"MAN24");
	}
	
	@Test
	public void addBookTest() 
	{
		Library lib = buildLibrary();
		when(libraryservice.buildId(lib.getIsbn(),lib.getAisle())).thenReturn(lib.getId());
		when(libraryservice.CheckBookAlreadyExists(lib.getId())).thenReturn(true);
		ResponseEntity response = con.addBookImplementation(buildLibrary());
		System.out.println("response.getStatusCode()");
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
		AddResponse ad = (AddResponse) response.getBody();
		ad.getId();
		assertEquals(lib.getId(),ad.getId());//General assert statement has expected first then actual
		assertEquals("Book already exist", ad.getMsg());
	}
	
	public Library buildLibrary()
	{
		Library lib = new Library();
		lib.setAisle(322);
		lib.setBook_name("Spring");
		lib.setIsbn("sfe");
		lib.setAuthor("Rahul Shetty");
		lib.setId("sfe322");
		return lib;
	}

}
