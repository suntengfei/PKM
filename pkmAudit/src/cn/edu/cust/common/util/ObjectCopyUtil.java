package cn.edu.cust.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectCopyUtil {
	
	public static String fieldsToEscape[] = new String[] {"serialVersionUID", "id"}; 
	
	public static boolean isEscaped(Field field) {
		for(String fieldName : fieldsToEscape) {
			if(field.getName().endsWith(fieldName)) {
				return true;
			}
		}
		return false;
	}
	
	public static void copy(Object src, Object dest) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Class<?> classType = src.getClass();
		System.out.println(classType.getName());
		
		/*Constructor<?> constructor = classType.getConstructor(new Class[]{});
		
		Object obj = constructor.newInstance(new Object[]{});
		System.out.println(obj);*/
		
		Field fields[] = classType.getDeclaredFields();
		
		for(Field field : fields) {
			System.out.println(field);
			
			if(isEscaped(field)) {
				continue;
			}
			
			String name = field.getName();
			String firstLetter = name.substring(0, 1).toUpperCase();
			String getMethodName = "get" + firstLetter + name.substring(1);
			String setMethodName = "set" + firstLetter + name.substring(1);
			
			System.out.println(getMethodName);
			System.out.println(setMethodName);
			
			Method getMethod = classType.getMethod(getMethodName, new Class[]{});
			Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});
			
			Object value = getMethod.invoke(src, new Object[]{});
			if(value == null) {
				continue;
			}
			setMethod.invoke(dest, new Object[]{value});
		}
		
	}
	
}
