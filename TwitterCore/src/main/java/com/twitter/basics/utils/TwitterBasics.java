package com.twitter.basics.utils;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.twitter.log.GestorDeTraza;
import com.twitter.utils.Utilities;

public class TwitterBasics {
	/**
	 * Actualiza el estado en Twitter
	 * @param instance
	 * @param message
	 * @return
	 */
	public Status updateStatus(Twitter instance, String message){
		final String methodName = "updateStatus";
		Status updateStatus = null;
		try {
			updateStatus = instance.updateStatus(message);
		} catch (TwitterException e) {
			String msgError = "Error al actualizar el estado ";
			GestorDeTraza.error(this.getClass().getName(), methodName, msgError, e);
		}
		
		return updateStatus;
	}
	
	/**
	 *  Buscar Tweets asociados a la query que recibe por parametro
	 * @param instance Instancia de Twitter
	 * @param queryStr Query que se usará para la búsqueda
	 * @param wantedTweets Número de Tweets que se solicitan
	 * @param dateStr Opcional, indica la fecha desde que la que se va a realizar la búsqueda
	 * @return Lista de Status que contiene información de los tweets otenidos
	 */
	public List<Status> search(Twitter instance, String queryStr, int wantedTweets, String dateStr){
		final String methodName = "search";
		GestorDeTraza.info(this.getClass().getName(), methodName, "INICIO");
		ArrayList<Status> result = new ArrayList<Status>();
		
		Query query= new Query(queryStr);
		
		if(wantedTweets > 100){
			query.setCount(100);		
		}else{ 
			query.setCount(wantedTweets);
		}
		
		if (Utilities.isNotEmpty(dateStr)){
			query.setSince(dateStr);
		}
		
		long lowestTweetId = Long.MAX_VALUE;
		int obtainedTweets = 0;
		int searchResultCount = 11;
		try {
			while (obtainedTweets <= wantedTweets && searchResultCount > 10){
		    	QueryResult queryResult = instance.search(query);

				searchResultCount = queryResult.getTweets().size();
				
				result.addAll(queryResult.getTweets());
				 for (Status status : queryResult.getTweets()){  
					    if (status.getId() < lowestTweetId) {
					          lowestTweetId = status.getId();
					          query.setMaxId(lowestTweetId);
					    }		            
					    obtainedTweets++;
				}		       
		    }
		   
		} catch (TwitterException e) {
			String msgError = "Error al buscar tweets, tweets deseados: " + wantedTweets;
			GestorDeTraza.error(this.getClass().getName(), methodName, msgError, e);
		}
		
		GestorDeTraza.info(this.getClass().getName(), methodName, "FIN");
		return result;
	}
	
	public Trends getTrendingTopics(Twitter instance, int woeid){
		final String methodName = "getTrendingTopics";
		Trends trends = null;
		try {
			trends = instance.getPlaceTrends(woeid);
		} catch (TwitterException e) {
			String msgError = "Error al buscar los trending topics para el woeid : " + woeid;
			GestorDeTraza.error(this.getClass().getName(), methodName, msgError, e);
		}
		return trends;
	}
	
	public void printQueryResults(List<Status> result){
		final String methodName = "printQueryResults";
	
		for (Status status : result) {
			GestorDeTraza.debug(this.getClass().getName(),methodName,"@" + status.getUser().getScreenName() + ":" + status.getText().replaceAll("[\n\r]", ""));
	    }
	}
	
	

}
