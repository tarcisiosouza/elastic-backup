package de.l3s.elasticquery;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;

public class testES5 extends TestCase {
	@SuppressWarnings("unchecked")
	public void testApp() throws IOException   {
		ElasticMain es = new ElasticMain("souza_eventkg", 32423442);
		es.setField("entityid");
		
		es.setKeywords("<entity_4380223>");
		es.setLimit(1);
		es.run();
		
		Map<LivingKnowledgeSnapshot, Double> documents = new HashMap<LivingKnowledgeSnapshot, Double>();
		documents = (Map<LivingKnowledgeSnapshot, Double>) es.getResults();
		for (Entry<LivingKnowledgeSnapshot, Double> s:documents.entrySet())
		{
			System.out.println(s.getKey().getDocId() + " " + s.getKey().getTitle());
		}
}
}
