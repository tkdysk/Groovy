package jsonic.sample;

import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

import net.arnx.jsonic.JSON;

/**
 * GoogleのURL短縮サービス「goo.gl」を利用するクラスです。
 * 
 * @author m_namiki
 * 
 */
public class Googl {
	public static void main(String[] args) {
		System.out.println(Googl.shortener("http://d.hatena.ne.jp/m-namiki/20110530"));
	}

	private static final String API_URL = "https://www.googleapis.com/urlshortener/v1/url";

	/**
	 * 指定されたURLの短縮URLを返却します。
	 * 
	 * @param target
	 *            対象URL
	 * @return 短縮URL
	 */
	public static String shortener(String target) {
		try {
			URL url = new URL(API_URL);
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setRequestProperty("Content-Type", "application/json");

			PrintStream ps = new PrintStream(urlConn.getOutputStream());
			ps.print("{\"longUrl\": \"" + target + "\"}");
			ps.close();

			GooglJSON json = JSON.decode(urlConn.getInputStream(),
					GooglJSON.class);
			return json.getId();
		} catch (Exception cause) {
			throw new RuntimeException(cause);
		}
	}
}
