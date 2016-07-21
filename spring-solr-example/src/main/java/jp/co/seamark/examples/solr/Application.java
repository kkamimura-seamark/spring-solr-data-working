package jp.co.seamark.examples.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import jp.co.seamark.devtools.solr.DevelopSolrServerFactoryBean;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableSolrRepositories(basePackages="jp.co.seamark.examples.solr")
public class Application {

	@Bean
	public DevelopSolrServerFactoryBean getSolrClientFactoryBean() throws IOException{
		DevelopSolrServerFactoryBean bean = new DevelopSolrServerFactoryBean();
		bean.setSolrHome(new ClassPathResource("example"));
		
		return bean;
	}
//	@Bean
//	public SolrClient getSolrClient(DevelopSolrServerFactoryBean factory){
//		return factory.getSolrClient();
//	}

	@Bean(name="solrTemplate")
	public SolrTemplate getSolrTemplate(SolrClient client){
		SolrTemplate template = new SolrTemplate(client);
		return template;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
