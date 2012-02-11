package parser.utils

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.xml.sax.SAXException;

/**
 * plist-rule.xmlを使ったDigesterによってライブラリXMLを読み込むクラス
 */
public class PropertyListUtils  {
  /**
   * plist-rule.xmlから作成したRulesModule
   */
  class MyRulesModule extends FromXmlRulesModule {
	URL ruleXml = getClass().getClassLoader().getResource("parser/utils/plist-rule.xml");

	@Override
	protected void loadRules() {
	  loadXMLRules(ruleXml);
	}
  }

  /**
   * ライブラリXMLファイル読み込み用のDigester
   */
  private static Digester digester =
  DigesterLoader.newLoader( new PropertyListUtils.MyRulesModule() ).newDigester();

  /**
   * 指定したurlの内容をplistとして読み込み、内部の情報を戻す。
   * 内部のタグについて、arrayはjava.util.Listに、dictはjava.util.Mapに、
   * string, integer, dateはそれぞれString, Integer, java.util.Dateに変換する。
   * @param url XML形式のplistファイルを示すURL
   * @return plistの中身を戻す
   */
  public static Map load(URL url) throws IOException, SAXException {
	return (Map)((PropertyList) digester.parse(url)).getContent();
  }
  /**
   * 指定したurlの内容をplistとして読み込み、内部の情報を戻す。
   * 内部のタグについて、arrayはjava.util.Listに、dictはjava.util.Mapに、
   * string, integer, dateはそれぞれString, Integer, java.util.Dateに変換する。
   * @param file XML形式のplistファイルを示すFile
   * @return plistの中身を戻す
   */
  public static Map load(File file) throws IOException, SAXException {
	return (Map)((PropertyList) digester.parse(file)).getContent();
  }
}

/**
 * digesterでタグを処理する際に一時的に値を保持するラッパーインタフェース
 * @author terazzo
 */
interface ValueWrapper {
  /** @return 内部に保持する値を戻す */
  Object getValue();
}

/**
 * ValueWrapperを受け取りValueWrapperの内部の値を取得するクラス
 * @author terazzo
 */
abstract class Peeler {
  /**
   * valueを追加する
   * @param value 追加する値
   */
  public abstract void add(Object value);
  /**
   * wrapperの代わりにwrapperが内部に保持する値を取り出して追加する
   * @param wrapper 追加する値を含むValueWrapper
   */
  public void add(ValueWrapper wrapper) {
	add(wrapper.getValue());
  }
}

/**
 * plistタグを読み込む為のラッパークラス
 * @author terazzo
 */
class PropertyList extends Peeler {
  /** 内部ではObjectで保持*/
  private Object content;
  /**
   * 値を設定する
   * @param value plistタグ内の値(Map/List/String/Integerなど)
   */
  public void add(Object value) {
	this.content = value;
  }
  /** @return 内部に保持する値を戻す */
  public Object getContent() {
	return this.content;
  }
}

/**
 * arrayタグを読み込む為のラッパークラス
 * @author terazzo
 */
class Array extends Peeler implements ValueWrapper {
  /** 内部ではListで保持*/
  private List list = new ArrayList();

  /**
   * valueをlistに追加する
   * @param value 追加する値
   */
  public void add(Object value) {
	this.list.add(value);
  }
  /** @return 内部に保持するList値を戻す */
  public Object getValue() {
	return this.list;
  }
}

/**
 * dateタグを読み込む為のラッパークラス
 */
class Date implements ValueWrapper {
  /** 日付フォーマット(ISO 8601。実際は"yyyy-MM-dd'T'HH:mm:ss'Z'"で固定)。 */
  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  /** 内部的に保持する日付オブジェクト*/
  private java.util.Date date = null;

  /**
   * 文字列dateStringを日付として設定する
   * @param dateString 日付を表す文字列(フォーマットは"yyyy-MM-dd'T'HH:mm:ss'Z'")
   */
  public void takeDateString(String dateString) throws ParseException {
	DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	df.setTimeZone(TimeZone.getTimeZone("UTC"));
	this.date = df.parse(dateString);
  }
  /** @return 内部に保持するDate値を戻す */
  public Object getValue() {
	return this.date;
  }
}

/**
 * dictタグを読み込む為のラッパークラス
 * @author terazzo
 */
class Dict extends Peeler implements ValueWrapper {
  /** 内部ではMapで保持*/
  private Map map = new HashMap();

  /** 最後に出現したkeyタグの値を保持 */
  private String key;

  /**
   * keyを設定する。この直後にaddした値をこのkeyを用いて内部のMapに追加する
   * @param key key文字列
   */
  public void setKey(String key) {
	this.key = key;
  }
  /**
   * valueをmapに追加する。最後に設定したkey値をキーとして使用する
   * @param value 追加する値
   */
  public void add(Object value) {
	this.map.put(this.key, value);
  }
  /** @return 内部に保持するMap値を戻す */
  public Object getValue() {
	return this.map;
  }
}

/**
* trueタグを読み込む為のラッパークラス
*/
class True implements ValueWrapper {
   /** @return 内部に保持するBoolean値を戻す */
   public Object getValue() {
	 return Boolean.TRUE;
   }
}

/**
* falseタグを読み込む為のラッパークラス
*/
class False implements ValueWrapper {
   /** @return 内部に保持するBoolean値を戻す */
   public Object getValue() {
	 return Boolean.FALSE;
   }
}