package bot.curator.collect.tool

import bot.curator.collect.tool.CustomHTMLParser;


List<String> contents = new CustomHTMLParser ( "http://d.hatena.ne.jp/kamatarokugatu/20120211/1328958626" ).getContents()
contents.each {
	println it 
}
