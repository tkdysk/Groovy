/**
* Groovy Version: 1.8.3 JVM: 1.7.0_01
* groovy -c UTF-8 MakeThumbnail.groovy
* 
* thumbnailator - Thumbnailator - a thumbnail generation library for Java - Google Project Hosting
* http://code.google.com/p/thumbnailator/
* "Thumbnailator-0.3.10.jar" > ~/.groovy/lib or <GROOVY_HOME>/lib 
*/

import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.resizers.configurations.Antialiasing

/** Config */
File rootDir = new File(".")
File pictureDir = new File("./pictures")
File thumbnailDir = new File("./thumbnail")

/** initialize */
if(!pictureDir.exists()) pictureDir.mkdir()
if(thumbnailDir.exists()) thumbnailDir.deleteDir()
thumbnailDir.mkdir()

/** Get Jpeg pictures Path */
List pictures = findPictures(pictureDir, ~/.*\.[jJ][pP][gG]/)

/** Create Thumbnails */
List thumbnails = createThumbnails(pictureDir, thumbnailDir, pictures, 800)

/** Completed! Message */
println "Completed!"


/** function - findPictures  */
def findPictures(File rootDir, java.util.regex.Pattern filter) {
  List pictures = new ArrayList()
  rootDir.traverse(
    type         : groovy.io.FileType.FILES,
    nameFilter   : filter
  ) { 
      pictures.add( it )
  }
  return pictures
}

/** function - createThumbnails */
def createThumbnails(File pictureDir, File thumbnailDir, List pictures, int width) {
  List thumbnails = new ArrayList()
  for(File picture : pictures){
    String relativePath = picture.canonicalPath - pictureDir.canonicalPath
    File thumbnail = new File(thumbnailDir.canonicalPath + relativePath)
    File parentDir = new File(thumbnail.parent)
    if (!parentDir.exists()) parentDir.mkdirs()
    
    Thumbnails.of(picture)
      .width(width)
      .keepAspectRatio(true)
      .antialiasing(Antialiasing.ON)
      .outputQuality(1.0d)
      .allowOverwrite(true)
      .toFile(thumbnail)
    
    thumbnails.add(thumbnail)
  }
  return thumbnails
}


