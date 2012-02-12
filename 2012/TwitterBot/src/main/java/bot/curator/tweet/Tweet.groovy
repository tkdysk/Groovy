package bot.curator.tweet

import bot.curator.common.Item
import bot.curator.common.Result
import bot.curator.common.url.shortener.impl.GoogleUrlShortener

class Tweet {
	def tweet(Result result) {
		Item item = result.getItem()
		String tweet = "["+String.valueOf(item.getService())+"] "
		tweet += new GoogleUrlShortener().shortener(item.getUrl())
		
		Map countMap = [:]
		result.getTags().each {
			countMap.put(it, 
				(countMap.containsKey(it)?countMap.get(it):0) +1)
		}
		
		countMap.remove("twitter")
		countMap.remove("Twitter")
		
		countMap.sort{it.value}.reverseEach {key, value ->
			String tag = value+":"+key
			if(tweet.length()<100){
				tweet += "  "+tag
			}
		}
		
		// 書き込みさせない。
		println tweet
		tweet = ""
		return new UpdateStatus().update(tweet)
	}
}
