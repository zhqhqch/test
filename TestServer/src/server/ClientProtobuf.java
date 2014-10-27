package server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import com.hqch.simple.netty.io.RequestInfoProtoBuf;
import com.hqch.simple.util.StringUtil;

public class ClientProtobuf {

	public static void main(String[] args) {
		ClientBootstrap bootstrap = new ClientBootstrap(  
                new NioClientSocketChannelFactory(  
                        Executors.newCachedThreadPool(),  
                        Executors.newCachedThreadPool()));  
  
        // Configure the event pipeline factory.  
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new ProtobufVarint32FrameDecoder());
//				pipeline.addLast("encoder", new ProtobufEncoder());
				
//				pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());  
//				pipeline.addLast("protobufDecoder", new ProtobufDecoder());
				pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());  
				pipeline.addLast("protobufEncoder", new ProtobufEncoder()); 
				return pipeline;
			}
		});  
  
        // Make a new connection.  
        ChannelFuture connectFuture =  
            bootstrap.connect(new InetSocketAddress("localhost", 10002));  
  
        // Wait until the connection is made successfully.  
        Channel channel = connectFuture.awaitUninterruptibly().getChannel();
        
        RequestInfoProtoBuf.Request.Builder request = RequestInfoProtoBuf.Request.newBuilder();
        request.setId(StringUtil.generateID());
//        request.setSn("UserService.login");
        request.setSn("UserService.test");
        
        RequestInfoProtoBuf.RequestParam.Builder param1 = RequestInfoProtoBuf.RequestParam.newBuilder();
        param1.setKey("userName");
        param1.setValue("呵呵");
        request.addData(param1);
        
        RequestInfoProtoBuf.RequestParam.Builder param2 = RequestInfoProtoBuf.RequestParam.newBuilder();
        param2.setKey("sex");
        param2.setValue("true");
        request.addData(param2);
        
        RequestInfoProtoBuf.RequestParam.Builder param3 = RequestInfoProtoBuf.RequestParam.newBuilder();
        param3.setKey("name");
        param3.setValue("tru2222e");
        request.addData(param3);
        
        channel.write(request.build());
	}
}
