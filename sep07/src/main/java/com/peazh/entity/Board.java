package com.peazh.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "board")
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	
	@Id
	private String id;
	private String title;
	private String content;
	private LocalDateTime date;
	private String name;
	private String img;

}
