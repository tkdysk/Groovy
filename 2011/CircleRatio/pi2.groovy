println "http://www.eonet.ne.jp/~kaz5919/kazwebsite/data/pi.txt"
            .toURL()
            .getText()
            .replaceAll(" |\n","")
            .substring(0, args ? args[0].toInteger() : 4 )
