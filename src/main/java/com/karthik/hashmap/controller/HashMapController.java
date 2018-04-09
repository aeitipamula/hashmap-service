package com.karthik.hashmap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.hashmap.service.CustomHashMapService;

@RestController
public class HashMapController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomHashMapService<String, String> customHashMap ;
	
	@GetMapping("/test")
	public String testing(){
		return "test";
	}
	
	@GetMapping("insert/key/{key}/value/{value}")
	public ResponseEntity<?> insert(@PathVariable String key,@PathVariable String value){
			if ( key.isEmpty() ){
				return new ResponseEntity<Object>("key Empty",HttpStatus.NOT_ACCEPTABLE);
			}else {
			   customHashMap.put(key, value);
			   return new ResponseEntity<Object>("inserted",HttpStatus.CREATED);
			}
	}
	
	@GetMapping("get/{key}")
	public ResponseEntity<?> get(@PathVariable String key){
		String foundValue = customHashMap.get(key);
		if ( null == foundValue ){
			return new ResponseEntity<Object>("Key Not Found",HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<Object>(foundValue,HttpStatus.FOUND);
		}
	}
	
	@GetMapping("remove/{key}")
	public ResponseEntity<?> remove(@PathVariable String key){
		return new ResponseEntity<Object>(customHashMap.remove(key),HttpStatus.OK);
	}
	
	@GetMapping("display")
	public  ResponseEntity<?> display(){
		return new ResponseEntity<Object>(customHashMap.display(),HttpStatus.OK);
	}
	
}
