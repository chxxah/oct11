package com.peazh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.peazh.entity.Test;

public interface TestRepository extends MongoRepository<Test, Long>{//MongoRepository 이거 자체가 값을 자동으로 만들어줌
	

}
