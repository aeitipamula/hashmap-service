package com.karthik.hashmap.controller;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.karthik.hashmap.HashMapServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HashMapServiceApplication.class)
public class HashMapControllerTest {
	
	@Autowired
	HashMapController hashMapController;


	
	@Test
	public void checkHashMapControllerCreated() {
		assertTrue(hashMapController!=null);
	}
	
	@Test
	public void testTesting() {
		assertTrue("test".equals(hashMapController.testing()));
		
	}
	
	@Test 
	public void testInsert(){
		ResponseEntity<?> response = hashMapController.insert("", "Test");
		assertTrue(response.getBody().equals("key Empty") && response.getStatusCode() == HttpStatus.NOT_ACCEPTABLE);
		
		response = hashMapController.insert("12", "Test");
		assertTrue(response.getBody().equals("inserted") && response.getStatusCode() == HttpStatus.CREATED);
		
	}
	
	@Test 
	public void testGet(){
		ResponseEntity<?> response = hashMapController.get("");
		assertTrue(response.getBody().equals("Key Not Found") && response.getStatusCode() == HttpStatus.NOT_FOUND);
		
		hashMapController.insert("12", "Test");
		response = hashMapController.get("12");
		assertTrue(response.getBody().equals("Test") && response.getStatusCode() == HttpStatus.FOUND);
	}
	
	@Test 
	public void testRemove(){
		ResponseEntity<?> response = hashMapController.remove("");
		assertTrue(response.getBody().equals(false) && response.getStatusCode() == HttpStatus.OK);
		
		hashMapController.insert("12", "Test");
		response = hashMapController.remove("12");
		assertTrue(response.getBody().equals(true) && response.getStatusCode() == HttpStatus.OK);
	}
	
	@Test 
	public void testDisplay(){
		ResponseEntity<?> response = hashMapController.display();
		assertTrue(response.getBody().toString().isEmpty() && response.getStatusCode() == HttpStatus.OK);
		
		hashMapController.insert("12", "Test");
		response = hashMapController.display();
		assertTrue(!response.getBody().toString().isEmpty() && response.getStatusCode() == HttpStatus.OK);
	}

}
