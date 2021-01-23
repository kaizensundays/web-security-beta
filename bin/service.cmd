start "Node" java -Xmx256m ^
	-Dlog4j.configurationFile=log4j2.xml -Dlog4j.shutdownHookEnabled=false ^
	-Dproperties=service.properties ^
	-jar service.jar
