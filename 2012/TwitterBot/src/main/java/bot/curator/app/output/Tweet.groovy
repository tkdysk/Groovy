package bot.curator.app.output

import bot.curator.common.bean.Item
import bot.curator.common.bean.Result
import bot.curator.common.util.urlshortener.impl.GoogleUrlShortener

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
