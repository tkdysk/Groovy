/**
* 指定したディレクトリ以下のmp3ファイルをID3タグから取得した"アーティスト名 - 曲名"にリネームする。
* 
* Groovy Version: 1.8.5 JVM: 1.7.0_02 Vendor: Oracle Corporation OS: Windows 7
* groovy -cp jid3lib-0.5.4.jar -c UTF-8  ReNameMp3.groovy "Dir Path"
* 
* Java ID3 Tag Library
* http://javamusictag.sourceforge.net/
*/

if( !args ) System.exit(1)
File rootDir = new File(args[0])
List<File> mp3Files = getPatternMatchFiles(rootDir, ~/.*\.[mM][pP][3]/)
mp3Files.each {
    String defFileName = it.getName()
    String defPath = it.getAbsolutePath()
    //ライブラリのバグ対応（ファイル名をアスキーのみに一時変更）
    File temp = new File( it.getParent()+File.separator+"_temp_" )
    it.renameTo temp
    String newFileName = getTitleAndArtistFileName(temp)
    String newPath = temp.getParent()+File.separator+newFileName
    
    if( newFileName!=null && defPath!=newPath && !new File(newPath).exists() ){
         println "rename to  $newPath \n          ($defFileName)"
         if( !temp.renameTo(newPath) ){
             println " => remain $defPath"
             temp.renameTo defPath
         }
    } else {
         println "remain     $defPath"
         temp.renameTo defPath
    }
}
println "fin."


/**  */
def getTitleAndArtistFileName(File mp3) {
    def mp3File = new org.farng.mp3.MP3File(mp3)
    if( mp3File.hasID3v1Tag() ){
        def getCorrectValue = {String strValue ->
            String id3v1CharCode = "iso-8859-1"
            String osCharCode = System.getProperty("file.encoding")
            return new String(strValue.getBytes(id3v1CharCode),osCharCode)
        }
        def id3v1 = mp3File.getID3v1Tag()
        String title = getCorrectValue(id3v1.getTitle())
        String artist = getCorrectValue(id3v1.getArtist())
        if( notEmpty(artist, title) 
            && title.getBytes().size()<30 && artist.getBytes().size()<30 )
            return artist+" - "+title+".mp3"
    }
    if( mp3File.hasID3v2Tag() ) {
        def id3v2 = mp3File.getID3v2Tag()
        String title = id3v2.getSongTitle()
        String artist = id3v2.getLeadArtist()
        if( notEmpty(artist, title) ) return artist+" - "+title+".mp3"
    }
    return null
}

/**  */
def notEmpty(String... strs) {
    def result = true
    strs.each { if( !it ) result=false }
    return result
}

/**  */
def getPatternMatchFiles(File rootDir, java.util.regex.Pattern filter) {
    def files = new ArrayList<File>()
    rootDir.traverse(
      type         : groovy.io.FileType.FILES,
      nameFilter   : filter
    ) {
        files << it 
    }
    return files
}