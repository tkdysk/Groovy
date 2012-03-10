package bot.curator.app.collect.impl

import java.util.List;

import bot.curator.app.collect.AbstarctCollector;
import bot.curator.common.bean.Item;

import com.sun.syndication.feed.synd.SyndEntry
import com.sun.syndication.feed.synd.SyndFeed
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;

class RSSCollector extends AbstarctCollector {

	@Override
	public List<Item> getItems() {
		String url = "http://rss.rssad.jp/rss/mycom/enterprise"

		FeedFetcher fetcher = new HttpURLFeedFetcher()
		SyndFeed feed = fetcher.retrieveFeed(new URL(url))

		System.out.format("フィードタイトル:[%s] 著者:[%s]\n",
				feed.getTitle(),
				feed.getUri())
		for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
			System.out.format("\t更新時刻:[%s] URL:[%s] 記事タイトル:[%s]\n",
					entry.getPublishedDate(),
					entry.getLink(),
					entry.getTitle())
		}

		return null;
	}
}
