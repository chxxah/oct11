package com.peazh.entity;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "test") //mongoDB에서 사용하는 것 
@AllArgsConstructor
@NoArgsConstructor
public class Test {
	
	@Id
	private String id;
	private int age;
	private String name;
	private Map<String, String> hobby;

}
