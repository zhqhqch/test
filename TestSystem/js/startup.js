importPackage(com.hqch.simple.resource);
importPackage(com.hqch.simple.resource.sql);
importPackage(com.hqch.simple.rpc.server);

//注册缓存
var caches = new Resource();
caches.host = "127.0.0.1";
caches.port = 11111;
$.registerCache("cache", caches);

//数据库连接
var dataSource = new C3P0ConnectionResource();
dataSource.setUser('root');
dataSource.setPassword('');
dataSource.setJdbcUrl('jdbc:mysql://localhost:3306/product?characterEncoding=UTF-8');
dataSource.setDriverClass('com.mysql.jdbc.Driver');
dataSource.setMaxPoolSize(50);
dataSource.setMinPoolSize(25);
$.registerResource("dataSource",dataSource)

//配置本server
var server = new RPCServer();
server.port = 10003;
$.initServer(server);