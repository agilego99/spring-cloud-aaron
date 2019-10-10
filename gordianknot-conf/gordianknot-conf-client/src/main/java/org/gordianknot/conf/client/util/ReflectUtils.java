package org.gordianknot.conf.client.util;
import java.lang.reflect.Method;
import org.springframework.util.StringUtils;
public abstract class ReflectUtils {
    
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
}
