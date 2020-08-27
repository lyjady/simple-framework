package org.simpleframework.utils;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LinYongJin
 * @date 2020/8/21 14:04
 */
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";
    public static final String CLASS_SUFFIX = ".class";

    /**
     * 获取包下类的集合
     *
     * @param packageName 指定的包名
     * @return 类的集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        ClassLoader classLoader = getClassLoader();
        // 加载资源
        URL url = classLoader.getResource(packageName.replaceAll("\\.", "/"));
        if (url == null) {
            return null;
        }
        Set<Class<?>> classSet = null;
        // 过滤不同文件资源
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            File file = new File(url.getPath());
            classSet = new HashSet<>();
            addClassToSet(classSet, file, packageName);
        }
        return classSet;
    }

    /**
     * 将指定目录下的class文件生成class对象添加到集合中
     *
     * @param classSet    添加的集合
     * @param file        指定的文件对象
     * @param packageName 指定的包
     */
    private static void addClassToSet(Set<Class<?>> classSet, File file, String packageName) {
        // 如果是文件那么就结束递归
        if (!file.isDirectory()) {
            return;
        }
        // 过滤掉非文件夹, 非class文件的file对象
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return true;
                } else {
                    // 获取到文件绝对路径
                    String absolutePath = pathname.getAbsolutePath();
                    if (absolutePath.endsWith(CLASS_SUFFIX)) {
                        try {
                            classSet.add(generateClass(absolutePath));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e.getMessage());
                        }
                    }
                }
                return false;
            }

            private Class<?> generateClass(String absolutePath) throws ClassNotFoundException {
                String replacePath = absolutePath.replaceAll(File.separator, "\\.");
                int indexOf = replacePath.indexOf(packageName);
                String targetClassPackageName = replacePath.substring(indexOf, replacePath.length() - CLASS_SUFFIX.length());
                return Class.forName(targetClassPackageName);
            }
        });
        if (files != null) {
            for (File f : files) {
                addClassToSet(classSet, f, packageName);
            }
        }
    }

    /**
     * 获取到当前线程的上下文类加载器
     *
     * @return 上下文类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 实例化类
     *
     * @param target 要被实例化的类
     * @return
     */
    public static Object newInstance(Class<?> target) {
        return newInstance(target, true);
    }

    /**
     * 实例化类
     *
     * @param target     要被实例化的类
     * @param accessible 访问权限
     * @return
     */
    public static Object newInstance(Class<?> target, boolean accessible) {
        try {
            Constructor<?> constructor = target.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 为属性注入Bean
     *
     * @param target 被注入的对象
     * @param field  被注入的属性
     * @param bean   注入的对象
     */
    public static void fieldInject(Object target, Field field, Object bean) {
        fieldInject(target, field, bean, true);
    }

    /**
     * 为属性注入Bean
     *
     * @param target     被注入的对象
     * @param field      被注入的属性
     * @param bean       注入的对象
     * @param accessible 访问权限
     */
    public static void fieldInject(Object target, Field field, Object bean, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(target, bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        extractPackageClass("org.augustus.entity");
    }
}
