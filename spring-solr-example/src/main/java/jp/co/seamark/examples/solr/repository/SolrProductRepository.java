package jp.co.seamark.examples.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import jp.co.seamark.examples.solr.data.Product;

public interface SolrProductRepository extends SolrCrudRepository<Product, String> {

}
