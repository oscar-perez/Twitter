package com.twitter.testing;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;

import com.twitter.basics.utils.TwitterBasics;
import com.twitter.connection.utils.TwitterConnection;
import com.twitter.log.GestorDeTraza;

public class Main {

	public static void main(String[] args) {
		Twitter twitter = TwitterConnection.getTwitterInstance();
		TwitterBasics basics = new TwitterBasics();
		
		//List<Status> tweets = basics.search(twitter, "#CristianoRonaldo", 200, null);
		Trends trends = basics.getTrendingTopics(twitter, 23424950);
		
		for (Trend t : trends.getTrends()){
			GestorDeTraza.debug("", "", t.getName());
		}
		//basics.printQueryResults(tweets);

	}

}
