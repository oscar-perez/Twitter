package com.twitter.testing;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;

import com.twitter.basics.utils.TwitterBasics;
import com.twitter.connection.utils.TwitterConnection;
import com.twitter.log.GestorDeTraza;

public class Main {

	public static void main(String[] args) {
		Twitter twitter = TwitterConnection.getTwitterInstance();
		TwitterBasics basics = new TwitterBasics();
		GestorDeTraza.info("main", "main", "Mensaje Info");
		List<Status> tweets = basics.search(twitter, "#CristianoRonaldo", 200, null);
		basics.printQueryResults(tweets);

	}

}
