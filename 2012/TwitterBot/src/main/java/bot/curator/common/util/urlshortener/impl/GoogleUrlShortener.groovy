package bot.curator.common.util.urlshortener.impl

import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

import jsonic.sample.GooglJSON;
import net.arnx.jsonic.JSON;
import bot.curator.common.util.urlshortener.UrlShortener;

class GoogleUrlShortener implements UrlShortener {
	
	private static final String API_URL = "https://www.googleapis.com/urlshortener/v1/url";

	public String shortener(String longUrl) {
		if(!longUrl) return longUrl
		try {
			URL url = new URL(API_URL);
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setRequestProperty("Content-Type", "application/json");

			PrintStream ps = new PrintStream(urlConn.getOutputStream());
			ps.print("{\"longUrl\": \"" + longUrl + "\"}");
			ps.close();

			GooglJSON json = JSON.decode(urlConn.getInputStream(),
					GooglJSON.class);
			return json.getId();
		} catch (Exception e) {
			e.printStackTrace()
		}
		return longUrl
	}

}

class GoogleJSON {
	String kind
	String id
	String longUrl
}