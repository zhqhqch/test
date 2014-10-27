importPackage(com.hqch.simple.resource);
importPackage(com.hqch.simple.server);

//日志级别 默认 DUBUG
//$.setLogLevel("INFO");

//注册缓存
var cacheds = new Resource();
cacheds.host = "127.0.0.1";
cacheds.port = 11111;
$.registerCache("cached", cacheds);

//配置本server
var server = new GameServer();
server.port = 10002;
server.protocol = "json";//protobuf json
server.synchroData = true;
server.cachedName = "cached";
$.initServer(server);

//配置rmi提供调用的server
var remoteList = [ {
	name : "system",
	host : "127.0.0.1",
	port : 10003
}, {
	name : "data",
	host : "127.0.0.1",
	port : 10004
} ];

for(var i = 0; i<remoteList.length; i++){
	server = remoteList[i];
	var remote = new Resource();
	remote.setName(server.name);
	remote.setHost(server.host);
	remote.setPort(server.port);
	$.addRemoteServer(remote);
}