package thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class GreeterServiceProvider {
    public static void main(String[] args) {
        try {
            System.out.println("服务开启...");
            TProcessor tProcessor = new GreeterService.Processor<GreeterService.Iface>(new GreeterServiceImpl());
            TServerSocket serverTransport = new TServerSocket(33333);
            //TServer.Args tArgs = new TServer.Args(serverTransport);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
            tArgs.processor(tProcessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            //单线程阻塞IO
            //TServer server = new TSimpleServer(tArgs);

            TServer server = new TThreadPoolServer(tArgs);
            //服务暴露
            server.serve();
        }catch (TTransportException e){
            e.printStackTrace();
        }
    }
}
