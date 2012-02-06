package parser

class PlayList {
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
	/** Playlist Items */
	Map<Integer, Track> playlistItems = new LinkedHashMap<Integer, Track>()
}
