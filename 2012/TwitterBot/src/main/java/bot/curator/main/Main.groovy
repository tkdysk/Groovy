package bot.curator.main

import bot.curator.analysis.Analyzer;
import bot.curator.analysis.impl.KuromojiAnalyzer
import bot.curator.collect.Collector
import bot.curator.collect.impl.TwitterCollector;
import bot.curator.common.Item;
import bot.curator.common.Result
import bot.curator.tweet.Tweet

class Main {

	static main(args) {
		Collector collector = new TwitterCollector()
		List<Item> items = collector.getItems()
		items.each {
			//println "---------" //+it.toString()
			Result result = new KuromojiAnalyzer(it).analyze()
			new Tweet().tweet(result)
		}
	}

}
