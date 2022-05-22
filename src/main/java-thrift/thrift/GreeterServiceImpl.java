package thrift;

import org.apache.thrift.TException;

public class GreeterServiceImpl implements GreeterService.Iface{
    @Override
    public String sayHello(String username) throws TException {
        return "Hello " + username + " ! ";
    }
}
