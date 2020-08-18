package demo.reflect;

/**
 * @author LinYongJin
 * @date 2020/8/18 10:10
 */
public class ReflectTarget {

    private int privateField = 1;

    public int publicField = 2;

    int defaultField = 3;

    protected int protectedField = 4;

    ReflectTarget(int var) {
        System.out.println("default constructor, param: " + var);
    }

    public ReflectTarget(String var) {
        System.out.println("public constructor, param: " + var);
    }
    
    protected ReflectTarget(char var) {
        System.out.println("protected constructor, param: " + var);
    }
    
    private ReflectTarget(boolean var) {
        System.out.println("private constructor, param: " + var);
    }

    private String privateMethod(String var) {
        return var;
    }

    int defaultMethod(int var) {
        return var;
    }

    public boolean publicMethod(boolean var) {
        return var;
    }

    protected char protectedMethod(char var) {
        return var;
    }
}
