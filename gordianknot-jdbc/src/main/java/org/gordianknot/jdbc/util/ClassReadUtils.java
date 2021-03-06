package org.gordianknot.jdbc.util;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
public class ClassReadUtils {
 	public static void main(String[] args) {
 		Set<Class<?>> list = getClassFromPackagePath("org.gordianknot");
 		for (Class<?> clazz : list) {
 			System.out.println(clazz.getName());
 		}
 	}
 	/**
 	* 掃描包路徑下所有的class文件
 	*
 	* @param pkg
 	* @return
 	*/
 	public static Set<Class<?>> getClassFromPackagePath(String pkg) {
 		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
 		String pkgDirName = pkg.replace('.', '/');
 		try {
 			Enumeration<URL> urls = ClassReadUtils.class.getClassLoader().getResources(pkgDirName);
 			while (urls.hasMoreElements()) {
 				URL url = urls.nextElement();
 				String protocol = url.getProtocol();
 				if ("file".equals(protocol)) {
 					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
 					findClassesByFile(pkg, filePath, classes);
 				} else if ("jar".equals(protocol)) {
 					JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
 					findClassesByJar(pkg, jar, classes);
 				}
 			}
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		return classes;
 	}
 	/**
 	* 掃描包路徑下的所有class文件
 	*
 	* @param pkgName
 	*            包名
 	* @param pkgPath
 	*            包對應的絕對地址
 	* @param classes
 	*            保存包路徑下class的集合
 	*/
 	private static void findClassesByFile(String pkgName, String pkgPath, Set<Class<?>> classes) {
 		File dir = new File(pkgPath);
 		if (!dir.exists() || !dir.isDirectory()) {
 			return;
 		}
 		// 過濾獲取目錄，or class文件
 		File[] dirfiles = dir.listFiles(new FilenameFilter() {
 			
 			public boolean accept(File dir, String name) {
 				return dir.isDirectory() || name.endsWith("class");
 			}
 		});
 		if (dirfiles == null || dirfiles.length == 0) {
 			return;
 		}
 		String className;
 		Class clz;
 		for (File f : dirfiles) {
 			if (f.isDirectory()) {
 				findClassesByFile(pkgName + "." + f.getName(), pkgPath + "/" + f.getName(), classes);
 				continue;
 			}
 			// 獲取類名，乾掉 ".class" 後綴
 			className = f.getName();
 			className = className.substring(0, className.length() - 6);
 			// 加載類
 			clz = loadClass(pkgName + "." + className);
 			if (clz != null) {
 				classes.add(clz);
 			}
 		}
 	}
 	/**
 	* 掃描包路徑下的所有class文件
 	*
 	* @param pkgName
 	*            包名
 	* @param jar
 	*            jar文件
 	* @param classes
 	*            保存包路徑下class的集合
 	*/
 	private static void findClassesByJar(String pkgName, JarFile jar, Set<Class<?>> classes) {
 		String pkgDir = pkgName.replace(".", "/");
 		Enumeration<JarEntry> entry = jar.entries();
 		JarEntry jarEntry;
 		String name, className;
 		Class<?> claze;
 		while (entry.hasMoreElements()) {
 			jarEntry = entry.nextElement();
 			name = jarEntry.getName();
 			if (name.charAt(0) == '/') {
 				name = name.substring(1);
 			}
 			if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
 				// 非指定包路徑， 非class文件
 				continue;
 			}
 			// 去掉後面的".class", 將路徑轉為package格式
 			className = name.substring(0, name.length() - 6);
 			claze = loadClass(className.replace("/", "."));
 			if (claze != null) {
 				classes.add(claze);
 			}
 		}
 	}
 	private static Class<?> loadClass(String fullClzName) {
 		try {
 			return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
 		} catch (ClassNotFoundException e) {
 		}
 		return null;
 	}
}
