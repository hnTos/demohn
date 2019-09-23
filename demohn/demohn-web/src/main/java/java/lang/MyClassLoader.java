package java.lang;

/**
 * @Author: liyb
 * @Date: 2019/7/4
 * @description
 */
public class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
