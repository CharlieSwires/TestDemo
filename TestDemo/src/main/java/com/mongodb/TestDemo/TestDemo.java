package com.mongodb.TestDemo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.Bean;
import com.mongodb.BeanRepository;

@RestController
@RequestMapping(path = "/TestDemo")
public class TestDemo {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestDemo.class);

	@Autowired
	private BeanRepository beanRepository;
	
	@Autowired
	private MongoOperations mongoOperations;

	@GetMapping(path="/", produces = "application/json")
	public List<Bean> getBeans()
	{
		List<Bean> result = beanRepository.findAll();
		return result;
	}
	@GetMapping(path="/{id}", produces = "application/json")
	public Bean getBean(@PathVariable String id)
	{
		Bean result = beanRepository.findById(id);
		return result;
	}
	@GetMapping(path="/name/{part}", produces = "application/json")
	public List<Bean> getBeansWithNameLike(@PathVariable String part)
	{
		List<Bean> result = beanRepository.findByNameLikeOrderByNameAsc(part);
		return result;
	}
	@GetMapping(path="/name/{first}/{second}", produces = "application/json")
	public List<Bean> getBeansWithNameLikeFirstSecond(@PathVariable String first,@PathVariable String second)
	{
		List<Bean> result = beanRepository.findBeansByRegexpFirstSecond(first,second);
		return result;
	}
	
	
	@PostMapping(path= "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Bean> addBean(@RequestBody Bean been)
	{

		beanRepository.save(been);

		return new ResponseEntity<Bean>(been,HttpStatus.CREATED);
	}
	@PostMapping(path= "/bulk", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<Bean>> addBeans(@RequestBody List<Bean> beens)
	{
		this.mongoOperations.insertAll(beens);
		return new ResponseEntity<List<Bean>>(beens,HttpStatus.CREATED);
	}
	@PutMapping(path= "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Bean> updateBean(@PathVariable String id,@RequestBody Bean newBean)
	{
		Bean bean = beanRepository.findById(id);
		bean.setName(newBean.getName()!=null?newBean.getName():bean.getName());
		bean.setAddress(newBean.getAddress()!=null?newBean.getAddress():bean.getAddress());
		bean.setMobile(newBean.getMobile()!=null?newBean.getMobile():bean.getMobile());
		bean.setPets(newBean.getPets()!=null?newBean.getPets():bean.getPets());
		beanRepository.save(bean);

		return new ResponseEntity<Bean>(bean,HttpStatus.OK);
	}
	
	@DeleteMapping(path= "/{id}", produces = "application/json")
	public ResponseEntity<String> removeBean(@PathVariable String id)
	{

		beanRepository.deleteById(id);

		return new ResponseEntity<String>(id,HttpStatus.OK);
	}
}