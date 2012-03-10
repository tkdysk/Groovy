package twitter4j.sample

//Groovy習作「イケメンと呟いた人に「ん、呼んだ？」と即座に非公式RTを返した気分になる」 - IT素人がIT機器を弄ったり妄想したりする日記
//http://d.hatena.ne.jp/the48/20120309/1331276920

//@Grab('org.twitter4j:twitter4j:2.2.5')
//@Grab('org.twitter4j:twitter4j-stream:2.2.5')
import twitter4j.*


if (false) {

	def twitter = new TwitterFactory().instance
	def query = new twitter4j.Query("イケメン")

	twitter.search(query).getTweets().each {
		def text = "ん、呼んだ？ RT @${it.fromUser}: ${it.getText()}"
		if (text.length() <140) {
			println text // 表示
			//twitter.updateStatus(text)
		}
	}

} else {

	def stream = new TwitterStreamFactory().instance
	def listener = [
				onStatus: { println "ん、呼んだ？ RT @$it.user.screenName: $it.text"  },
				onException: { ex -> ex.printStackTrace() },
			] as UserStreamAdapter
	stream.addListener(listener)

	stream.filter(new FilterQuery(0,null,'イケメン'))

}