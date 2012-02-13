package bot.curator.common.bean

enum Service {
	Twitter, Facebook, GooglePlus, RSS
}

class Item {
	Enum service
	String fqdn
	String id
	String url
	String body
	String title
	List<String> links
	String data

	@Override
	String toString() {
		def strigValue = "Item Object"
		strigValue += "\n  Service: "+service
		strigValue += "\n  FQDN: "+fqdn
		strigValue += "\n  ID: "+id
		strigValue += "\n  URL: "+url
		strigValue += "\n  Body: "+body
		strigValue += "\n  Title: "+title
		links.each {
			strigValue += ("\n  Links: "+it)
		}
		strigValue += "\n  Data: "+data
		return strigValue
	}
}
