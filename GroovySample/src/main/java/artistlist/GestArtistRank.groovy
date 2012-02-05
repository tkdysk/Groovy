package artistlist
String filePath = getClass().getClassLoader().getResource("artistlist/artists.txt").getFile()

Map rankMap = new HashMap()

new File(filePath).eachLine("ms932") {
	rankMap.put(it, 
		(rankMap.containsKey(it)?rankMap.get(it):0) +1)
}

rankMap.sort{it.value}.reverseEach {key, value ->
	println "$key : ${value}‹È"
}