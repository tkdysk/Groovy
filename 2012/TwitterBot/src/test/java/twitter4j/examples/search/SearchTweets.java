/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twitter4j.examples.search;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.7
 */
public class SearchTweets {
    /**
     * Usage: java twitter4j.examples.search.SearchTweets [query]
     *
     * @param args
     */
    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("java twitter4j.examples.search.SearchTweets [query]");
//            System.exit(-1);
//        }
    	
    	String queryString = "groovy";
    	
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            //QueryResult result = twitter.search(new Query(args[0]));
        	Query query = new Query();
        	query.setQuery(queryString);
        	query.setLang("ja");
        	query.setResultType(Query.MIXED);
        	query.setPage(1);
        	query.rpp(1);
        	
        	QueryResult result = twitter.search(query);
            List<Tweet> tweets = result.getTweets();
            for (Tweet tweet : tweets) {
                //System.out.println("@" + tweet.getFromUser() + " - " + tweet.getText());
            	System.out.println("----------------------------");
            	System.out.println("@" + tweet.getFromUser() + " - " + tweet.getId());
            	
            	System.out.println("URL: http://twitter.com/" + tweet.getFromUser() + "/status/" + tweet.getId());
            	
            	
//            	URLEntity[] uRLEntities = tweet.getURLEntities();
//            	if(uRLEntities!=null){
//            		for(URLEntity uRLEntity : uRLEntities){
//            			System.out.println("URL : " + uRLEntity.getURL());
//            		}
//            	}
            	
            	System.out.println("      " + tweet.toString());
//            	System.out.println("1     " + tweet.getSource());
//            	System.out.println("2     " + tweet.getText());
            	
            	
            	
            }
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}
