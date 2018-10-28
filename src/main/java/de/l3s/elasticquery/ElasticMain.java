package de.l3s.elasticquery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class ElasticMain {

	public static boolean ASC = true;
	
    public static boolean DESC = false;
    private static String keywords;
    private static String field;
    private static int limit;
    private static long count;
    private static Map<Url, Double> result;
    private static Map<String, String> resultGeneric;
    private static Map<String, RelationSnapshot> resultRelation;
    private static String propFileName;
    private static UrlElasticQuery query;
    private static Map<LivingKnowledgeSnapshot, Double> resultLK;
    private static String indexName;
    private static int rand;
    private RelationSnapshot relation;
    private boolean randomSearch;
    private boolean matchAll;
    private static  Map<String, RelationSnapshot> relationMap;
    public boolean isMatchAll() {
		return matchAll;
	}

	public void setMatchAll(boolean matchAll) {
		this.matchAll = matchAll;
	}

	public boolean isRandomSearch() {
		return randomSearch;
	}

	public void setRandomSearch(boolean randomSearch) {
		this.randomSearch = randomSearch;
	}

	public static int getRand() {
		return rand;
	}

	public void setRand(int rand) {
		ElasticMain.rand = rand;
	}

	public ElasticMain (String q, int lim, String f, String index) throws IOException
    {
    
		randomSearch = false;
		matchAll=false;
		relationMap = new HashMap<String, RelationSnapshot>();
    	ElasticMain.indexName = index;
    	result = new HashMap<Url, Double>();
    	keywords = q;
    	limit = lim;
    	field = f;
    	count = 0;
    	query = new UrlElasticQuery (indexName);
    	
    }
    
	public void setIndexName (String name)
	{
		ElasticMain.indexName = name;
		query.setIndexName(name);
	}
    public ElasticMain (String index, int rand) throws IOException
    {
    	ElasticMain.indexName = index;

    	this.rand = rand;
    	query = new UrlElasticQuery (indexName);
    	relation = new RelationSnapshot ();
    }
  
    public ElasticMain () throws IOException
    {
    	
    }
  
    public void closeConnection ()
    {
    	query.closeConnection();
    }
    
	public static String getKeywords() {
		return keywords;
	}
	public void setKeywords(String key) {
		keywords = key;
	}
	
	public static String getField() {
		return field;
	}
	public void setField(String field) {
		ElasticMain.field = field;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int lim) {
		limit = lim;
	}
	
	public static Map<Url, Double> getResult() {
		return result;
	}
	
	public static Map<LivingKnowledgeSnapshot, Double> getResultLK() {
		return resultLK;
	}

	public static Map<?, Double> getResults ()
	{
		switch (indexName)
		{
		
		case "souza_livingknowledge":
		{
			return  resultLK;
		}
		
		case "souza_german_cdx_urls":
		{
			return result;
			
		}
		
		
		}
		
		return null;
	}
	public HashMap <String, Integer> getDomains (){
		return query.getDomains();
	}
	
	public ArrayList<String> getArticleText () {
		return query.getArticleText();
	}
	public ArrayList<String> getTotalDocuments (){
		return query.getArrayTotalDoc();
	}
	public void run () throws IOException
	{
		
		switch (indexName)
		{
		
		case "souza_livingknowledge_2":
		{
			resultLK = new HashMap<LivingKnowledgeSnapshot, Double>();
			resultLK = query.getRankedDocumentsLivingKnowledge(keywords, limit, field);
			break;
		}
		
		case "souza_livingknowledge":
		{
			resultLK = new HashMap<LivingKnowledgeSnapshot, Double>();
			resultLK = query.getRankedDocumentsLivingKnowledge(keywords, limit, field);
			break;
		}
		
		case "souza_german_cdx_urls":
		{
			result = query.getRankedDocuments(keywords, limit, field);
			break;
		}
		
		case "souza_eventkg":
		{
			if (randomSearch)
			{
				relation = query.getRandomDocument(rand);
				break;
			}
			else
			{
			  if (!matchAll)
			  {
				resultGeneric = new HashMap<String,String>();
				resultGeneric = query.getGenericDocuments(keywords, limit, field);
				break;
			  }
			}
			
			if (matchAll)
			{
				resultGeneric = query.matchAllGenericDocuments();
				break;
			}
		}
		
		case "souza_eventkg_timestamp":
		{
			resultGeneric = new HashMap<String,String>();
			resultGeneric = query.getGenericDocuments(keywords, limit, field);
			break;
		}
		
		case "souza_eventkg_relations":
		{
			if (randomSearch)
				relation = query.getRandomDocument(rand);
			else
			{
				relationMap = query.getRelationDocuments(keywords, limit, field);

			}
			break;
		}
		
		case "souza_eventkg_relations_all":
		{
			if (randomSearch)
				relation = query.getRandomDocument(rand);
			else
			{
				relationMap = query.getRelationDocuments(keywords, limit, field);

			}
			break;
		}
		
		}
		
			
	}
	

	public static Map<String, RelationSnapshot> getRelationMap() {
		return relationMap;
	}

	public static void setRelationMap(Map<String, RelationSnapshot> relationMap) {
		ElasticMain.relationMap = relationMap;
	}

	public RelationSnapshot getRelation() {
		return relation;
	}

	public void setRelation(RelationSnapshot relation) {
		this.relation = relation;
	}

	public HashMap<String, Article> getAllDocuments () throws IOException
	{
		return query.getDocuments(limit);
	}
	
	public Map<String, String>  getGenericDocuments () throws IOException
	{
		return resultGeneric;
	}
	
	public static void main (String args[])
	{
		
	}
	}
