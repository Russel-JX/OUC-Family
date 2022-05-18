package patten.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Service service = new ServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(service);
        Service serviceProxy = (Service) handler.getProxy();
        serviceProxy.add();
    }
    /**output:
     * -----before-----
     This is add service
     -----end-----
     */
}
