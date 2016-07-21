package jp.co.seamark.devtools.solr;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactoryBean;

public class DevelopSolrServerFactoryBean extends EmbeddedSolrServerFactoryBean{
	private static final Logger logger = LoggerFactory.getLogger(DevelopSolrServerFactoryBean.class);
	
	
	private File workingDirectory=null;
	private String solrHome;

	public DevelopSolrServerFactoryBean() {
		super();
	}

	@PostConstruct
	public void before() throws Throwable{
		this.workingDirectory = createTempDirectory();
		logger.debug("Created temp folder %s",this.workingDirectory.getPath());
		this.copySolrHomeFiles(this.solrHome, this.workingDirectory);
		super.setSolrHome(this.workingDirectory.getPath());
	}
	@Override
	public void destroy() throws Exception{
		super.destroy();
		this.workingDirectory.delete();
		super.setSolrHome(null);
	}
	
	public File createTempDirectory() throws IOException{
		return createTempDirectory(FileUtils.getTempDirectory());
	}
	public File createTempDirectory(File parentFolder) throws IOException{
		File dir = File.createTempFile(DevelopSolrServerFactoryBean.class.getSimpleName(),"",parentFolder);
		dir.delete();
		dir.mkdirs();
		return dir;
	}

	public void validate(){
	}
	public void copySolrHomeFiles(String source,File directory) throws IOException{
		File dir = new File(source);
		if( dir.exists() ){
			FileUtils.copyDirectory(dir, directory);
			logger.debug("Copied %s files from %s to temp folder.",dir.list().length ,dir.getPath());
		}
	}
	public void setSolrHome(Resource config) throws IOException{
		this.solrHome = config.getFile().getPath();
	}
	
	@Override
	public void setSolrHome(String solrHome) {
		this.solrHome = solrHome;
	}
	
	
}
