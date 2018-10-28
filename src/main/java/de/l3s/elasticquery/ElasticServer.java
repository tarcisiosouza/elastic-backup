package de.l3s.elasticquery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticServer {
	
	private  TransportClient client;
	private  Settings settings;
	private  String index;
	private  String type;
	private  String cluster;
	private  String hostname;
	private  String user;
	private  String password;
	private  int port;
	private  int port2;
	private  Properties config;
	
	public ElasticServer(String index) {
		
		this.index = index;
	}

	public void loadProperties () throws IOException
	{
		
		String propFileName = "configLK.properties";
		
		InputStream inputStream = ElasticServer.class.getClassLoader().getResourceAsStream(propFileName);
		config = new Properties ();
		
		if (inputStream != null) {
			config.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
		//index = config.getProperty("index");
		type = config.getProperty("type");
		cluster = config.getProperty("cluster");
		hostname = config.getProperty("hostname");
		port = Integer.parseInt(config.getProperty("port"));
		user = config.getProperty("user");
		password = config.getProperty("password");
		port2 = port + 1;
		Settings settings = Settings.settingsBuilder()
		.put("client.transport.sniff", true)
		.put("shield.user", "souza:pri2006")
		.put("sniffOnConnectionFault",true)
		
		.put("cluster.name", cluster).build();
		/*settings = ImmutableSettings.settingsBuilder()
			.put("shield.user", "souza:pri2006")
		    .put("cluster.name", cluster).build();*/
		client = TransportClient.builder().settings(settings).build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostname), port))
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostname), port));
	
	/*	 client = new PreBuiltTransportClient(Settings.EMPTY)
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostname), port))
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostname), port));
	*/
	
	}
	

	public TransportClient getClient() {
		return client;
	}

	public void setClient(TransportClient client) {
		this.client = client;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void closeConection ()
	{
		client.close();
		
	}
}
