package org.gordianknot.jdbc.util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
@SuppressWarnings({"unchecked"})
public abstract class ReflectUtils {
 	/**
 	* 調用字段名對象的setter方法，也有可能只是以set*的普通方法
 	* @param fieldName 字段名，也可能只是一個普通的名字
 	* @param type setter方法類型
 	* @param instance 調用實例
 	* @param args setter方法參數
 	* @return setter方法調用後的返回值，通常為null
 	*/
 	public static Object callSetMethod(String fieldName, Class<?> type, Object instance, Object... args){
 		Object result = null;
 		String mn = "set"+StringUtils.capitalize(fieldName);
 		try{
 			Method method = getMethod(instance.getClass(), mn, type);
 			result = method.invoke(instance, args);
 		}catch(IllegalArgumentException e){
 			throw new RuntimeException("參數不正確[MethodName:"+mn+",Type:"+type.getCanonicalName()+",ArgumentType:"+args[0].getClass().getCanonicalName()+"]", e);
 		}catch(Exception e){
 			throw new RuntimeException(e.getMessage(), e);
 		}
 		return result;
 	}
 	
 	/**
 	* 根據字段名調用對象的getter方法，如果字段類型為boolean，則方法名可能為is開頭，也有可能只是以setFieleName的普通方法
 	* @param fieldName
 	* @param instance
 	* @return getter方法調用後的返回值
 	*/
 	public static Object callGetMethod(String fieldName, Object instance){
 		Object result = null;
 		try{
 			String mn = "get"+StringUtils.capitalize(fieldName);
 			Method method = null;
 			try{
 				method = getMethod(instance.getClass(), mn);
 			}catch(NoSuchMethodException nsme){
 				mn = "is"+StringUtils.capitalize(fieldName);
 				method = getMethod(instance.getClass(), mn);
 			}
 			result = method.invoke(instance, new Object[]{});
 		}catch(Exception e){
 			throw new RuntimeException(e.getMessage(), e);
 		}
 		return result;
 	}
 	/**
 	*
 	* @param fieleName
 	* @param instance
 	* @param args 方法調用參數
 	* @return
 	*/
 	public static Object callGetMethod(String fieleName, Object instance, Object[] args){
 		Object result = null;
 		try{
 			String mn = "get"+StringUtils.capitalize(fieleName);
 			Method method = null;
 			try{
 				method = getMethod(instance.getClass(), mn);
 			}catch(NoSuchMethodException nsme){
 				mn = "is"+StringUtils.capitalize(fieleName);
 				method = getMethod(instance.getClass(), mn);
 			}
 			result = method.invoke(instance, args);
 		}catch(Exception e){
 			throw new RuntimeException(e.getMessage(), e);
 		}
 		return result;
 	}
 	/**
 	* 得到給寫的類或其父類中聲明的字段
 	* @param entityClass 類
 	* @param fieldname 字段名
 	* @param caseSensitive 是否大小寫敏感
 	* @return
 	* @throws NoSuchFieldException
 	*/
 	public static Field getField(Class<?> entityClass, String fieldname, boolean ignoreCase) throws NoSuchFieldException {
 		if(ignoreCase){
 			try{
 				Field f = entityClass.getDeclaredField(fieldname);
 				if(f != null){
 					return f;
 				}
 			}catch(NoSuchFieldException e){
 				if(entityClass.getSuperclass() != null && entityClass.getSuperclass() != Object.class){
 					return getField(entityClass.getSuperclass(), fieldname, ignoreCase);
 				}else{
 					throw e;
 				}
 			}
 		}else{
 			Field[] fs = BeanUtils.extractFieldsFromPOJO(entityClass);
 			for(int i=0; i<fs.length; i++){
 				Field f = fs[i];
 				if(fieldname.toLowerCase().equals(f.getName().toLowerCase() )){
 					return f;
 				}
 			}
 		}
 		return null;
 	}
 	
 	public static Field getField(Class<?> entityClass, String[] fieldNames, Class<? extends Annotation> annotationClass) throws NoSuchFieldException {
 		for (String fieldname : fieldNames) {
 			try{
 				Field f = entityClass.getDeclaredField(fieldname);
 				if(f != null && f.isAnnotationPresent(annotationClass)){
 					return f;
 				}
 			}catch(NoSuchFieldException e){
 				if(entityClass.getSuperclass() != null && entityClass.getSuperclass() != Object.class){
 					return getField(entityClass.getSuperclass(), fieldname, true);
 				}else{
 					return null;
 				}
 			}
 		}
 		return null;
 	}
 	
 	public static Field getField(Class<?> entityClass, String fieldname, Class<? extends Annotation> annotationClass) throws NoSuchFieldException {
 		try{
 			Field f = entityClass.getDeclaredField(fieldname);
 			if(f != null && f.isAnnotationPresent(annotationClass)){
 				return f;
 			}
 		}catch(NoSuchFieldException e){
 			if(entityClass.getSuperclass() != null && entityClass.getSuperclass() != Object.class){
 				return getField(entityClass.getSuperclass(), fieldname, true);
 			}else{
 				return null;
 			}
 		}
 		return null;
 	}
 	
 	/**
 	* 得到給寫的類或其父類中聲明的方法
 	* @param entityClass
 	* @param methodName
 	* @return
 	* @throws NoSuchMethodException
 	*/
 	public static Method getMethod(Class<?> entityClass, String methodName, Class<?>... type) throws NoSuchMethodException {
 		try{
 			Method m = entityClass.getDeclaredMethod(methodName, type);
 			if(m != null){
 				return m;
 			}
 		}catch(NoSuchMethodException ex){
 			if(entityClass.getSuperclass() != null && entityClass.getSuperclass() != Object.class){
 				return getMethod(entityClass.getSuperclass(), methodName, type);
 			}else{
 				throw ex;
 			}
 		}
 		return null;
 	}
 	public static Field[] getFields(Class<?> entityClass, boolean containsStatic){
 		List<Field> fields = new ArrayList<Field>();
 		
 		Field[] temp = entityClass.getDeclaredFields();
 		for(int i=0; i<temp.length; i++){
 			Field f = temp[i];
 			if(! containsStatic && Modifier.isStatic(f.getModifiers()) ){
 				continue;
 			}else{
 				fields.add(f);
 			}
 		}
 		if(entityClass.getSuperclass() != null && entityClass.getSuperclass() != Object.class){
 			Field[] fs = getFields(entityClass.getSuperclass(), containsStatic);
 			for(int i=0; i<fs.length; i++){
 				fields.add(fs[i]);
 			}
 		}
 		return fields.toArray(new Field[fields.size()]);
 	}
 	
 	public static Class getSuperClassGenericType(Class clazz, int index) {
 		Type genType = clazz.getGenericSuperclass();
 		if (!(genType instanceof ParameterizedType)) {
 			return Object.class;
 		}
 		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
 		if (index >= params.length || index < 0) {
 			return Object.class;
 		}
 		if (!(params[index] instanceof Class)) {
 			return Object.class;
 		}
 		return (Class) params[index];
 	}
 	/**
 	* <h3>得到指定類的所有聲明字段</h3>
 	* <p>當containsParents值為true時，返回的字段列表中將包含所有父類的聲明字段（Object除外）</p>
 	* <p>當containsStatic值為false時，不包含靜態字段</p>
 	* @param entityClass
 	* @param ignoreProperties 忽略的字段
 	* @param containsParents 是否包含父類中聲明的字段
 	* @param containsStatic 是否包含靜態字段
 	* @return
 	*/
 	public static Field[] getDeclaredFields(Class<?> entityClass, String[] ignoreProperties, boolean containsParents, boolean containsStatic){
 		List<Field> fields = new LinkedList<Field>();
 		List<String> excludeProps = Collections.EMPTY_LIST;
 		if(ignoreProperties != null){
 			excludeProps = Arrays.asList(ignoreProperties);
 		}
 		
 		Field[] temp = entityClass.getDeclaredFields();
 		for(int i=0; i<temp.length; i++){
 			Field f = temp[i];
 			if(! containsStatic && Modifier.isStatic(f.getModifiers()) ){
 				continue;
 			}else{
 				if(excludeProps.contains(f.getName())){
 					continue;
 				}
 				fields.add(f);
 			}
 		}
 		if(containsParents){
 			if(entityClass.getSuperclass() != null && entityClass.getSuperclass() != Object.class){
 				Field[] fs = getDeclaredFields(entityClass.getSuperclass(), ignoreProperties, containsParents, containsStatic);
 				for(int i=0; i<fs.length; i++){
 					fields.add(fs[i]);
 				}
 			}
 		}
 		return fields.toArray(new Field[fields.size()]);
 	}
 	/**
 	*
 	* @param clazz
 	* @param fieldName
 	* @return
 	*/
 	public static Method setter(Class clazz, String fieldName, Class<?>... type){
 		try{
 			String mn = "set"+StringUtils.capitalize(fieldName);
 			Method method = null;
 			try{
 				method = getDeclaredMethod(clazz, mn, true, type);
 			}catch(NoSuchMethodException nsme){
 				//ignore
 			}
 			return method;
 		}catch(Exception e){
 			throw new RuntimeException(e.getMessage(), e);
 		}
 	}
 	/**
 	* 得到指定類的指定字段名的getter方法
 	* @param Clazz
 	* @param fieldName
 	* @return
 	*/
 	public static Method getter(Class clazz, String fieldName){
 		try{
 			String mn = "get"+StringUtils.capitalize(fieldName);
 			Method method = null;
 			try{
 				method = getDeclaredMethod(clazz, mn, true);
 			}catch(NoSuchMethodException nsme){
 				mn = "is"+StringUtils.capitalize(fieldName);
 				method = getDeclaredMethod(clazz, mn, true);
 			}
 			return method;
 		}catch(Exception e){
 			throw new RuntimeException(e.getMessage(), e);
 		}
 	}
 	/**
 	* 得到類中聲明的方法
 	* @param entityClass
 	* @param methodName
 	* @param containsParents
 	* @param type
 	* @return
 	* @throws NoSuchMethodException
 	*/
 	public static Method getDeclaredMethod(Class<?> clazz, String methodName, boolean containsParents, Class<?>... type) throws NoSuchMethodException {
 		try{
 			return clazz.getDeclaredMethod(methodName, type);
 		}catch(NoSuchMethodException ex){
 			if(containsParents && clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class){
 				return getDeclaredMethod(clazz.getSuperclass(), methodName, containsParents, type);
 			}else{
 				throw ex;
 			}
 		}
 	}
 	
 	/**
 	* 得到類中聲明的字段
 	* @param clazz
 	* @param fieldName
 	* @param containsParents
 	* @return
 	* @throws NoSuchFieldException
 	*/
 	public static Field getDeclaredField(Class<?> clazz, String fieldName, boolean containsParents) throws NoSuchFieldException {
 		try{
 			return clazz.getDeclaredField(fieldName);
 		}catch(NoSuchFieldException ex){
 			if(containsParents && clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class){
 				return getDeclaredField(clazz.getSuperclass(), fieldName, containsParents);
 			}else{
 				throw ex;
 			}
 		}
 	}
}
