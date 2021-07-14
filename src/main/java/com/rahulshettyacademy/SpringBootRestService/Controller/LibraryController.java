package com.rahulshettyacademy.SpringBootRestService.Controller;

import java.util.List;

import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rahulshettyacademy.SpringBootRestService.Controller.Repository.LibraryRepository;
import com.rahulshettyacademy.SpringBootRestService.service.LibraryService;


@RestController
public class LibraryController {
	
	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryservice;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LibraryController.class);
	

	@PostMapping("/addBook")	
	public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library) 
	{
		String id = libraryservice.buildId(library.getIsbn(),library.getAisle());
		AddResponse ad = new AddResponse();
		if(!libraryservice.CheckBookAlreadyExists(id))
		{
		//String id = library.getIsbn()+library.getAisle();
		logger.info("Book do not exist so creating one");
		library.setId(id);
		repository.save(library);
		HttpHeaders headers = new HttpHeaders();
		headers.add("unique", id);
		ad.setMsg("Success Book is Added");
		ad.setId(id);
		return new ResponseEntity<AddResponse>(ad, headers, HttpStatus.CREATED);
		//ResponseEntity returns the output in JSON format. You could have used return ad also.
		}
		else
		{
			logger.info("Book exists so skipping creation");
			ad.setMsg("Book already exists");
			ad.setId(id);
			return new ResponseEntity<AddResponse>(ad, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/getBooks/{id}")
	public Library getBooksById(@PathVariable(value="id")String id)
	//The path variable replaces id value that we pass through POSTMAN in above 2 statements
	{
		try
		{
		Library lib = repository.findById(id).get();
		return lib;
		//This lib object will return all the values like aisle, isbn etc.
		}
		catch (Exception e)
		{
			//return new ResponseEntity<AddResponse>(ad, HttpStatus.NOT_FOUND);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getBooks/author")
	public List<Library> getBookByAuthorName(@RequestParam(value="authorname")String authorname)
	{
		return repository.findAllByAuthor("authorname");
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Library> updateBook(@PathVariable(value="id")String id, @RequestBody Library library)
	{
		Library existingBook = repository.findById(id).get();
		existingBook.setAisle(library.getAisle());
		existingBook.setAuthor(library.getAuthor());
		existingBook.setBook_name(library.getBook_name());
		repository.save(existingBook);
		return new ResponseEntity<Library>(existingBook, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBookById(@RequestBody Library library)
	{
		Library libdelete = repository.findById(library.getId()).get();
		repository.delete(libdelete);
		logger.info("Book is deleted");//other methods are logger.error and logger.debug
		return new ResponseEntity<>("Book is deleted", HttpStatus.CREATED);		
	}
}
