package parser

class Track {
	Track(){
	}

	Track(Map track){
		def getIntegerValue = { String key -> (Integer) (track?.get(key)) }
		def getDateValue = { String key -> (Date) track?.get(key) }
		def getStringValue = { String key -> (String) track?.get(key) }
		def getBooleanValue = { String key -> (Boolean) track?.get(key) }

		setTrackID(getIntegerValue("Track ID"))
		setName(getStringValue("Name"))
		setArtist(getStringValue("Artist"))
		setComposer(getStringValue("Composer"))
		setAlbum(getStringValue("Album"))
		setAlbumArtist(getStringValue("Album Artist"))
		setGenre(getStringValue("Genre"))
		setKind(getStringValue("Kind"))
		setSize(getIntegerValue("Size"))
		setTotalTime(getIntegerValue("Total Time"))
		setDiscNumber(getIntegerValue("Disc Number"))
		setDiscCount(getIntegerValue("Disc Count"))
		setTrackNumber(getIntegerValue("Track Number"))
		setYear(getIntegerValue("Year"))
		setDateModified(getDateValue("Date Modified"))
		setDateAdded(getDateValue("Date Added"))
		setBitRate(getIntegerValue("Bit Rate"))
		setSampleRate(getIntegerValue("Sample Rate"))
		setComments(getStringValue("Comments"))
		setPlayCount(getIntegerValue("Play Count"))
		setPlayDate(getIntegerValue("Play Date"))
		setPlayDateUTC(getDateValue("Play Date UTC"))
		setReleaseDate(getDateValue("Release Date"))
		setSkipCount(getIntegerValue("Skip Count"))
		setSkipDate(getDateValue("Skip Date"))
		setArtworkCount(getIntegerValue("Artwork Count"))
		setSortComposer(getStringValue("Sort Composer"))
		setPersistentID(getStringValue("Persistent ID"))
		setDisabled(getBooleanValue("Disabled"))
		setTrackType(getStringValue("Track Type"))
		setiTunesU(getBooleanValue("iTunesU"))
		setUnplayed(getBooleanValue("Unplayed"))
		setHasVideo(getBooleanValue("Has Video"))
		setHD(getBooleanValue("HD"))
		setVideoWidth(getIntegerValue("Video Width"))
		setVideoHeight(getIntegerValue("Video Height"))
		setMovie(getBooleanValue("Movie"))
		setLocation(getStringValue("Location"))
		setFileFolderCount(getIntegerValue("File Folder Count"))
		setLibraryFolderCount(getIntegerValue("Library Folder Count"))
	}

	/** Track ID */
	Integer trackID
	/** Name */
	String name
	/** Artist */
	String artist
	/** Composer */
	String composer
	/** Album */
	String album
	/** Album Artist */
	String albumArtist
	/** Genre */
	String genre
	/** Kind */
	String kind
	/** Size */
	Integer size
	/** Total Time */
	Integer totalTime
	/** Disc Number */
	Integer discNumber
	/** Disc Count */
	Integer discCount
	/** Track Number */
	Integer trackNumber
	/** Year */
	Integer year
	/** Date Modified */
	Date dateModified
	/** Date Added */
	Date dateAdded
	/** Bit Rate */
	Integer bitRate
	/** Sample Rate */
	Integer sampleRate
	/** Comments */
	String comments
	/** Play Count */
	Integer playCount
	/** Play Date */
	Integer playDate
	/** Play Date UTC */
	Date playDateUTC
	/** Release Date */
	Date releaseDate
	/** Skip Count */
	Integer skipCount
	/** Skip Date */
	Date skipDate
	/** Artwork Count */
	Integer artworkCount
	/** Sort Composer */
	String sortComposer
	/** Persistent ID */
	String persistentID
	/** Disabled */
	Boolean disabled
	/** Track Type */
	String trackType
	/** iTunesU */
	Boolean iTunesU
	/** Unplayed */
	Boolean unplayed
	/** Has Video */
	Boolean hasVideo
	/** HD */
	Boolean HD
	/** Video Width */
	Integer videoWidth
	/** Video Height */
	Integer videoHeight
	/** Movie */
	Boolean movie
	/** Location */
	String location
	/** File Folder Count */
	Integer fileFolderCount
	/** Library Folder Count */
	Integer libraryFolderCount
}
