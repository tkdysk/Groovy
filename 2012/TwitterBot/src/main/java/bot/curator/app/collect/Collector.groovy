package bot.curator.app.collect

import bot.curator.common.util.CustomHTMLParser;
import bot.curator.common.bean.Item
import org.cyberneko.html.parsers.DOMParser



interface Collector {

	void setQueryString(String queryString)
	List<Item> getItems()
}


abstract class AbstarctCollector implements Collector {
	String queryString
	
	String addLinkInfo2Data(String defData, List<String> links){
		String data = defData

		links.each {
			try {
				List<String> contents = new CustomHTMLParser(it).getContents()
				contents.each {
					if(isSignificantConditions(it.trim())){
						data += removeSpecificString(it) + " "
					}
				}
				//				DOMParser parser = new DOMParser()
				//				if(it) {
				//					parser.parse(it)
				//					parser.getDocument().getDocumentElement()
				//							.getTextContent()
				//							.replaceAll(/[ ]+/, " ")
				//							.split(" ").each {
				//								if(isSignificantConditions(it.trim())
				//								){
				//									data += it + " "
				//								}
				//							}
				//				}



				//				it?.toURL()?.getText()?.replaceAll(/<.+?>/," ")
				//						.replaceAll(/[ ]+/, " ")
				//						.split(" ").each {
				//							if(isSignificantConditions(it.trim())
				//							){
				//								data += it + " "
				//							}
				//						}
			} catch (Exception e) {
				e.printStackTrace()
			}
		}

		return data
	}

	Boolean isSignificantConditions(String str){
		if(!str) return Boolean.FALSE
		return str.length()!=1 &&
		!(str.matches(/[0-9a-zA-Z]{1,2}/)) &&
		!(str.matches(/@[0-9a-zA-Z_]*/)) &&
		!(str.matches(/^(https?|ftp)(:\\/\\/[-_.!~*\\'()a-zA-Z0-9;\\/?:\\@&=+\\$,%#]+)$/))
	}

	String removeSpecificString(String str){
		return str?.trim().replaceAll(/@[^ |0-9|a-z|A-Z|_]+/, "")
		.replaceAll(/(https?|ftp)(:\\/\\/[-_.!~*\\'()a-zA-Z0-9;\\/?:\\@&=+\\$,%#]+)/, "")
	}
}
