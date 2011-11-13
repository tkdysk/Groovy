/**
* Groovy Version: 1.8.2 JVM: 1.7.0
* //set JAVA_OPTS=-Dhttp.proxyHost=proxy.xxxxx.co.jp -Dhttp.proxyPort=8080
* groovy -c UTF-8 weather_forecast.groovy
*/

rssFeed = new groovy.util.XmlParser().parse("http://weather.yahooapis.com/forecastrss?p=JAXX0099&u=c")
item = rssFeed.channel.item[0]
title = item.title[0].value()[0]
condition = item."yweather:condition"

println "${title} : ${condition.@text[0]}, ${condition.@temp[0]}[℃]"