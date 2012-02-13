package bot.curator.common.util

import bot.curator.common.util.CustomHTMLParser;


List<String> contents = new CustomHTMLParser ( "http://www.lawson.co.jp/index.html" ).getContents()
contents.each {
	println it 
}
