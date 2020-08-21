package org.simpleframework.utils;

import java.io.File;
import java.io.FileFilter;
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
                String replacePath = absolutePath.replaceAll("\\\\", "\\.");
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

    public static void main(String[] args) {
        extractPackageClass("org.augustus.entity");
    }
}
