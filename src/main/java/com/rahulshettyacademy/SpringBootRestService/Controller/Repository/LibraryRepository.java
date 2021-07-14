package com.rahulshettyacademy.SpringBootRestService.Controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulshettyacademy.SpringBootRestService.Controller.Library;

public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom
{

}
