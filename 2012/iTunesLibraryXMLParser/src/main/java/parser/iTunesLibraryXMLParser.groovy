
package parser

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;

import org.xml.sax.SAXException;
import parser.utils.PropertyListUtils;

public class iTunesLibraryXMLParser {
  private static final String MAJOR_VERSION = "Major Version"
  private static final String MINOR_VERSION = "Minor Version"
  private static final String DATA = "Date"
  private static final String APPLICATION_VERSION = "Application Version"
  private static final String FEATURES = "Features"
  private static final String MUSIC_FOLDER = "Music Folder"
  private static final String LIBRARY_PERSISTENT_ID = "Library Persistent ID"
  private static final String TRACKS = "Tracks"
  private static final String PLAYLISTS = "Playlists"

  private Map content;

  iTunesLibraryXMLParser(String libraryXmlPath) throws IOException, SAXException{
	content = PropertyListUtils.load( new File(libraryXmlPath) );
	replaceTracks(content)
	replacePlaylists(content)
  }

  private def getIntegerValue = { Map map, String key -> (Integer) (map.get(key)) }
  private def getDateValue = { Map map, String key -> (Date) map.get(key) }
  private def getStringValue = { Map map, String key -> (String) map.get(key) }
  private def getBooleanValue = { Map map, String key -> (Boolean) map.get(key) }

  Integer getMajorVersion() {
	return getIntegerValue(content, MAJOR_VERSION)
  }
  Integer getMinorVersion() {
	return getIntegerValue(content, MINOR_VERSION)
  }
  Date getDate() {
	return getDateValue(content, DATA)
  }
  String getApplicationVersion() {
	return getStringValue(content, APPLICATION_VERSION)
  }
  Integer getFeatures() {
	return getIntegerValue(content, FEATURES)
  }
  String getMusicFolder() {
	return getStringValue(content, MUSIC_FOLDER)
  }
  String getLibraryPersistentID() {
	return getStringValue(content, LIBRARY_PERSISTENT_ID)
  }

  Map<Integer, Track> getTracks() {
	return (Map<Integer, Track>)content.get(TRACKS)
  }
  Map<String, PlayList> getPlayLists() {
	return (Map<String, PlayList>)content.get(PLAYLISTS)
  }
  PlayList getPlayList(String name) {
	return (PlayList)getPlayLists().get(name)
  }

  private def replaceTracks(Map content) {
	Map<String, Map> defTracks = (Map<String, Map>)content.get(TRACKS)
	Map<Integer, Track> newTracks = new HashMap<Integer, Track>()

	for (Map track : defTracks.values()) {
	  def trackObj = new Track(track)
	  newTracks.put(trackObj.getTrackID(), trackObj)
	}
	content.put(TRACKS, newTracks)
  }

  private def replacePlaylists(Map content) {
	List<Map> defPlaylists = (ArrayList<Map>)content.get(PLAYLISTS)
	Map<String, PlayList> newPlayLists = new LinkedHashMap<String, PlayList>()
	Map<Integer, Track> tracks = (Map<Integer, Track>)content.get(TRACKS)

	for(Map playlist : defPlaylists) {
	  def playlistObj = new PlayList(playlist, tracks)
	  newPlayLists.put(playlistObj.getName(), playlistObj)
	}
	content.put(PLAYLISTS, newPlayLists)
  }
}