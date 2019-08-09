package com.mongodb;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface BeanRepository extends MongoRepository<Bean, Long>{

	public List<Bean> findAll();

	public Bean save(Bean customer);
	
	public Bean findByName(String name);
	
	public Bean findById(String id);

	public void deleteById(String id);
	
	public List<Bean> findByNameLikeOrderByNameAsc(String part);
	
	@Query("{ 'name' : { $regex: '?0.*?1.*' } }")
	public List<Bean> findBeansByRegexpFirstSecond(String first, String second);
}
