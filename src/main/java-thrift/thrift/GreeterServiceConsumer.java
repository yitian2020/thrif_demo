package thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class GreeterServiceConsumer {
    public static void main(String[] args) {
        System.out.println("客户端启动...");
        TTransport transport = null;
        try {
            transport = new TSocket("127.0.0.1",33333);
            //指定二进制编码格式
            TProtocol protocol = new TBinaryProtocol(transport);
            GreeterService.Client client = new GreeterService.Client(protocol);
            //建立连接
            transport.open();
            //RPC调用
            String result = client.sayHello("World");
            System.out.println(result);
            Thread.sleep(10000);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(transport != null) {
                transport.close();
            }
        }
    }
}
