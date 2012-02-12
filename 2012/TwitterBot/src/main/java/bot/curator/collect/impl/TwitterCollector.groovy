package bot.curator.collect.impl

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import bot.curator.collect.Collector;
import bot.curator.common.Item;
import bot.curator.common.Service;

class TwitterCollector extends Collector {


	List<Item> getItems(){
		String queryString = "hatena"

		Twitter twitter = new TwitterFactory().getInstance()
		List<Tweet> tweets
		try {
			Query query = new Query()
			query.setQuery(queryString)
			query.setLang("ja")
			query.setResultType(Query.MIXED)
			query.setPage(1)
			query.rpp(1)

			QueryResult result = twitter.search(query)
			tweets = result.getTweets()
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		return tweets2Items(tweets)
	}

	private List<Item> tweets2Items(List<Tweet> tweets){
		List<Item> items = []
		tweets.each { items << tweet2Item(it) }
		return items
	}


	private Item tweet2Item(Tweet tweet){
		if(!tweet) return null

		def item = new Item()
		item.setService(Service.Twitter)
		item.setFqdn("twitter.com")
		String tweetId = String.valueOf(tweet.getId())
		item.setId(tweetId)
		item.setUrl("http://twitter.com/" + tweet.getFromUser() + "/status/" + tweetId)
		item.setBody(tweet.getText())
		item.setTitle("")
		List<String> links = []
		tweet.getURLEntities().each {
			links << it.getExpandedURL().toString()
		}
		item.setLinks(links)

		String data = ""
		tweet?.getText()?.replaceAll("[、。]", " ")
				.replaceAll(/@[0-9a-zA-Z_]*/, " ")
				.replaceAll(/[ ]+/, " ")
				.split(" ").each {
					if(isSignificantConditions(it)){
						data += it+" "
					}
				}
		item.setData(addLinkInfo2Data(data, links))

		return item
	}
}
