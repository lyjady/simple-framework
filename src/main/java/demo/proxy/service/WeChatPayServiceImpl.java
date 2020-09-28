package demo.proxy.service;

/**
 * @author: linyongjin
 * @create: 2020-09-28 22:49:27
 */
public class WeChatPayServiceImpl implements PayService {

    @Override
    public void pay() {
        System.out.println("使用微信支付");
    }
}
