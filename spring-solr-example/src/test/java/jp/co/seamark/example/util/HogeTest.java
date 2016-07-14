package jp.co.seamark.example.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.params.SolrParams;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.annotation.Id;

import jp.co.seamark.example.util.EmbeddedSolrServer.ClientCache;

public class HogeTest {

	
	public static @ClassRule EmbeddedSolrServer server = EmbeddedSolrServer.configure(new ClassPathResource("example"), ClientCache.DISABLED);

	@Test
	public void test() throws IOException, SolrServerException {
		UpdateResponse ret = server.getSolrClient().addBean(new User("1234","hogename"));
		
		server.getSolrClient().commit();
		System.out.println( server.getSolrClient().query(new SolrQuery("*:*")) );
	}
	
	static class User {
		@Id
		@Field("id")
		private String id;
		@Field("uname")
		private String name;
		
		public User(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	static class HogeData {
		private String id = "hoge_id";
	}

}
