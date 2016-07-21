package jp.co.seamark.example.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jp.co.seamark.examples.solr.Application;
import jp.co.seamark.examples.solr.data.Product;
import jp.co.seamark.examples.solr.repository.SolrProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ProductRepositoryTest {

	@Autowired
	SolrProductRepository repository;

	@Test
	public void test() {
		System.out.println(repository.findAll());
		Product product1 = new Product();
		product1.setId("key1");
		product1.setName("hogehoge");
		
		repository.save(product1);
		
		
		System.out.println(repository.findOne("key1"));
		
		
		
	}

}
