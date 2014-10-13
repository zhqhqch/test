importPackage(com.hqch.simple.resource);
importPackage(com.hqch.simple.rpc.server);

//注册缓存
var caches = new Resource();
caches.host = "127.0.0.1";
caches.port = 11111;
//$.registerCache("cache", caches);

//配置本server
var server = new RPCServer();
server.port = 10003;
$.initServer(server);