package parser

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;

import org.xml.sax.SAXException;
import parser.utils.PropertyListUtils;

public class iTunesLibraryXMLParser {
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

	Integer getMajorVersion() { return getIntegerValue(content, "Major Version") }
	Integer getMinorVersion() { return getIntegerValue(content, "Minor Version") }
	Date getDate() { return getDateValue(content, "Date") }
	String getApplicationVersion() { return getStringValue(content, "Application Version") }
	Integer getFeatures() { return getIntegerValue(content, "Features") }
	String getMusicFolder() { return getStringValue(content, "Music Folder") }
	String getLibraryPersistentID() { return getStringValue(content, "Library Persistent ID") }

	Map<Integer, Track> getTracks() { return (Map<Integer, Track>)content.get("Tracks") }
	Map<String, PlayList> getPlayLists() { return (Map<String, PlayList>)content.get("Playlists") }
	PlayList getPlayList(String key) { return (PlayList)getPlayLists().get(key) }
	
	private def replacePlaylists(Map content) {
		List<Map> defPlaylists = (ArrayList<Map>)content.get("Playlists")
		Map<String, PlayList> newPlayLists = new LinkedHashMap<String, PlayList>()
		Map<Integer, Track> tracks = (Map<Integer, Track>)content.get("Tracks")
		
		for(Map playlist : defPlaylists) {
			def playlistObj = new PlayList()
			playlistObj.setName(getStringValue(playlist, "Name"))
			playlistObj.setMaster(getBooleanValue(playlist, "Master"))
			playlistObj.setPlaylistID(getIntegerValue(playlist, "Playlist ID"))
			playlistObj.setPlaylistPersistentID(getStringValue(playlist, "Playlist Persistent ID"))
			playlistObj.setDistinguishedKind(getIntegerValue(playlist, "Distinguished Kind"))
			playlistObj.setMusic(getBooleanValue(playlist, "Music"))
			playlistObj.setMovies(getBooleanValue(playlist, "Movies"))
			playlistObj.setAudiobooks(getBooleanValue(playlist, "Audiobooks"))
			playlistObj.setBooks(getBooleanValue(playlist, "Books"))
			playlistObj.setPurchasedMusic(getBooleanValue(playlist, "Purchased Music"))
			playlistObj.setVisible(getBooleanValue(playlist, "Visible"))
			playlistObj.setAllItems(getBooleanValue(playlist, "All Items"))
			List<Map> playlistItems = (ArrayList<Map>)playlist.get("Playlist Items")
			for(Map playlistItem : playlistItems) {
				Integer trackID = getIntegerValue(playlistItem, "Track ID")
				playlistObj.getPlaylistItems().put(trackID, tracks.get(trackID))
			}
			
			newPlayLists.put(playlistObj.getName(), playlistObj)
		}
		content.put("Playlists", newPlayLists)
	}

	private def replaceTracks(Map content) {
		Map<String, Map> defTracks = (Map<String, Map>)content.get("Tracks")
		Map<Integer, Track> newTracks = new HashMap<Integer, Track>()
		
		for (Map track : defTracks.values()) {
			def trackObj = new Track()
			trackObj.setTrackID(getIntegerValue(track, "Track ID"))
			trackObj.setName(getStringValue(track, "Name"))
			trackObj.setArtist(getStringValue(track, "Artist"))
			trackObj.setComposer(getStringValue(track, "Composer"))
			trackObj.setAlbum(getStringValue(track, "Album"))
			trackObj.setAlbumArtist(getStringValue(track, "Album Artist"))
			trackObj.setGenre(getStringValue(track, "Genre"))
			trackObj.setKind(getStringValue(track, "Kind"))
			trackObj.setSize(getIntegerValue(track, "Size"))
			trackObj.setTotalTime(getIntegerValue(track, "Total Time"))
			trackObj.setDiscNumber(getIntegerValue(track, "Disc Number"))
			trackObj.setDiscCount(getIntegerValue(track, "Disc Count"))
			trackObj.setTrackNumber(getIntegerValue(track, "Track Number"))
			trackObj.setYear(getIntegerValue(track, "Year"))
			trackObj.setDateModified(getDateValue(track, "Date Modified"))
			trackObj.setDateAdded(getDateValue(track, "Date Added"))
			trackObj.setBitRate(getIntegerValue(track, "Bit Rate"))
			trackObj.setSampleRate(getIntegerValue(track, "Sample Rate"))
			trackObj.setComments(getStringValue(track, "Comments"))
			trackObj.setPlayCount(getIntegerValue(track, "Play Count"))
			trackObj.setPlayDate(getIntegerValue(track, "Play Date"))
			trackObj.setPlayDateUTC(getDateValue(track, "Play Date UTC"))
			trackObj.setReleaseDate(getDateValue(track, "Release Date"))
			trackObj.setSkipCount(getIntegerValue(track, "Skip Count"))
			trackObj.setSkipDate(getDateValue(track, "Skip Date"))
			trackObj.setArtworkCount(getIntegerValue(track, "Artwork Count"))
			trackObj.setSortComposer(getStringValue(track, "Sort Composer"))
			trackObj.setPersistentID(getStringValue(track, "Persistent ID"))
			trackObj.setDisabled(getBooleanValue(track, "Disabled"))
			trackObj.setTrackType(getStringValue(track, "Track Type"))
			trackObj.setiTunesU(getBooleanValue(track, "iTunesU"))
			trackObj.setUnplayed(getBooleanValue(track, "Unplayed"))
			trackObj.setHasVideo(getBooleanValue(track, "Has Video"))
			trackObj.setHD(getBooleanValue(track, "HD"))
			trackObj.setVideoWidth(getIntegerValue(track, "Video Width"))
			trackObj.setVideoHeight(getIntegerValue(track, "Video Height"))
			trackObj.setMovie(getBooleanValue(track, "Movie"))
			trackObj.setLocation(getStringValue(track, "Location"))
			trackObj.setFileFolderCount(getIntegerValue(track, "File Folder Count"))
			trackObj.setLibraryFolderCount(getIntegerValue(track, "Library Folder Count"))

			newTracks.put(trackObj.getTrackID(), trackObj)
		}
		content.put("Tracks", newTracks)
	}
}
