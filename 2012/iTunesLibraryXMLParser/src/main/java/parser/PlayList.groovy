package parser

class PlayList {
	PlayList() {
	}
	PlayList(Map playlist, Map<Integer, Track> tracks) {
		def getIntegerValue = { String key -> (Integer) (playlist?.get(key)) }
		def getDateValue = { String key -> (Date) playlist?.get(key) }
		def getStringValue = { String key -> (String) playlist?.get(key) }
		def getBooleanValue = { String key -> (Boolean) playlist?.get(key) }

		setName(getStringValue("Name"))
		setMaster(getBooleanValue("Master"))
		setPlaylistID(getIntegerValue("Playlist ID"))
		setPlaylistPersistentID(getStringValue("Playlist Persistent ID"))
		setDistinguishedKind(getIntegerValue("Distinguished Kind"))
		setMusic(getBooleanValue("Music"))
		setMovies(getBooleanValue("Movies"))
		setAudiobooks(getBooleanValue("Audiobooks"))
		setBooks(getBooleanValue("Books"))
		setPurchasedMusic(getBooleanValue("Purchased Music"))
		setVisible(getBooleanValue("Visible"))
		setAllItems(getBooleanValue("All Items"))
		List<Map> playlistItems = (ArrayList<Map>)playlist?.get("Playlist Items")
		for(Map playlistItem : playlistItems) {
			Integer trackID = (Integer) (playlistItem?.get("Track ID"))
			getPlaylistItems().put(trackID, tracks?.get(trackID))
		}
	}

	/** Name */
	String name
	/** Master */
	Boolean master
	/** Playlist ID */
	Integer playlistID
	/** Playlist Persistent ID */
	String playlistPersistentID
	/** Distinguished Kind */
	Integer distinguishedKind
	/** Music */
	Boolean music
	/** Movies */
	Boolean movies
	/** Audiobooks */
	Boolean audiobooks
	/** Books */
	Boolean books
	/** Purchased Music */
	Boolean purchasedMusic
	/** Visible */
	Boolean visible
	/** All Items */
	Boolean allItems
	/** Playlist Items <Track ID, Track Object> */
	Map<Integer, Track> playlistItems = new LinkedHashMap<Integer, Track>()
}
