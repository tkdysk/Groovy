package bot.curator.app.main

import bot.curator.app.analysis.Analyzer;
import bot.curator.app.analysis.impl.KuromojiAnalyzer
import bot.curator.app.collect.Collector;
import bot.curator.app.collect.impl.RSSCollector
import bot.curator.app.collect.impl.TwitterCollector;
import bot.curator.common.bean.Item;
import bot.curator.common.bean.Result
import bot.curator.app.output.Tweet

class Main {

	static main(args) {
		
		Collector collector2 = new RSSCollector()
		collector2.getItems()
		
		
		
//		def keywords = ["groovy", "java", "android", "music"]
//		Collector collector = new TwitterCollector()
//		keywords.each {
//			collector.setQueryString(it)
//			List<Item> items = collector.getItems()
//			items.each {
//				//println "---------" //+it.toString()
//				Result result = new KuromojiAnalyzer(it).analyze()
//				new Tweet().tweet(result)
//			}
//		}
	}
}
