package demo.annotation;

/**
 * @author LinYongJin
 * @date 2020/8/18 14:17
 */
@PersonInfo(name = "Jack", age = 18, hobby = {"123", "234", "345"})
public class Person {

    @AddressInfo
    private String defaultAddress;

    @AddressInfo(country = "美国", province = "加尼福利亚", city = "洛杉矶")
    public void changeAddress() {
        System.out.println("change");
    }
}
