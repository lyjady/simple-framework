package demo.proxy.service;

/**
 * @author: linyongjin
 * @create: 2020-09-28 22:48:48
 */
public class AlipayServiceImpl implements PayService {

    @Override
    public void pay() {
        System.out.println("使用支付宝支付");
    }
}
