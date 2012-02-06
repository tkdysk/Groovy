package sample

import parser.PlayList;
import parser.Track;
import parser.iTunesLibraryXMLParser;

def parser = new iTunesLibraryXMLParser("ライブラリ.xml")

PlayList playList = parser.getPlayList("ミュージック")
Map<Integer, Track> playlistItems = playList.getPlaylistItems()

def artistList = []
for(Track track: playlistItems.values()){
	artistList << track.getArtist()
}

def rankMap = [:]
artistList.each {
	rankMap.put(it, 
		(rankMap.containsKey(it)?rankMap.get(it):0) +1)
}
rankMap.sort{it.value}.reverseEach {key, value ->
	println "$key : ${value}曲"
}