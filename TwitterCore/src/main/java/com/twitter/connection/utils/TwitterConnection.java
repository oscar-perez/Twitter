package com.twitter.connection.utils;


import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterConnection {
	
	private static final String consumerKey = "YmRlvsbUit16MtyBwp6iEroIo";
	private static final String consumerSecret = "6WmfbmjFONxKVEi7zOtzUaMRtXWKoDA8zpIo4UPNSItplTIZYR";
	
	/**
	 * Devuelve una instancia unica de Twitter.
	 * @return
	 */
	public static Twitter getTwitterInstance(){
	    // The factory instance is re-useable and thread safe.
	    TwitterFactory factory = new TwitterFactory();
	    AccessToken accessToken = loadAccessToken(0); // Si tenemos varios usuarios(para evitar las limitaciones)
	    Twitter twitter = factory.getInstance();
	    twitter.setOAuthConsumer(consumerKey, consumerSecret);
	    twitter.setOAuthAccessToken(accessToken);
	    return twitter;
	 
	  }
	
	/**
	 * Nos devuelve las credenciales para el usuario indicado
	 * La idea es: como tenemos limitaciones en las consultas
	 * cuando hayamos agotado el número de consultas, nos conectaremos a otro usuario.
	 * @param useId
	 * @return
	 */
	  private static AccessToken loadAccessToken(int useId){
	    String token = "";// load from a persistent store
	    String tokenSecret = ""; // load from a persistent store
	    switch(useId){
	    case 0 :
	    	token = "927464393981530112-hxwgmo965oLNO7arlLIzZswOqbAknsE";
	    	tokenSecret = "shvEO0WJF5KbZGX35r4MkZtZvgstBjfKWluJx0HCPGkxM";
	    	break;
	    default:
	    	
	    }
	    
	    return new AccessToken(token, tokenSecret);
	  }

}
