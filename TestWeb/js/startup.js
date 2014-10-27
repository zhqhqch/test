importPackage(com.hqch.simple.web);
importPackage(com.hqch.simple.resource);

//配置本server
var server = new WebServer();
server.port = 10011;
$.initServer(server);

//配置rmi提供调用的server
var remoteList = [ {
	name : "system",
	host : "127.0.0.1",
	port : 10003
}];

for(var i = 0; i<remoteList.length; i++){
	server = remoteList[i];
	var remote = new Resource();
	remote.setName(server.name);
	remote.setHost(server.host);
	remote.setPort(server.port);
	$.addRemoteServer(remote);
}