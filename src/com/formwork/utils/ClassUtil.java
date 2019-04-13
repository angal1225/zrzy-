package com.formwork.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ClassUtil {
	
	public static Object setFieldValue(Class<?> cls, Map<String, String> valMap) {
		Object bean = null;
		try {
			bean = cls.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		Method[] methods = getAllMethods(bean);
		Field[] fields = getAllFields(bean);

		for (Field field : fields) {
			try {
				String fieldSetName = parSetName(field.getName());
				if (!checkSetMet(methods, fieldSetName)) {
					continue;
				}
				Method fieldSetMet = cls.getMethod(fieldSetName,
						field.getType());
				String value = valMap.get(field.getName());
				if (null != value && !"".equals(value)) {
					String fieldType = field.getType().getSimpleName();
					if ("String".equals(fieldType)) {
						fieldSetMet.invoke(bean, value);
					} else if ("Date".equals(fieldType)) {
						Date temp = parseDate(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Integer".equals(fieldType)
							|| "int".equals(fieldType)) {
						Integer intval = Integer.parseInt(value);
						fieldSetMet.invoke(bean, intval);
					} else if ("Long".equalsIgnoreCase(fieldType)) {
						Long temp = Long.parseLong(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Double".equalsIgnoreCase(fieldType)) {
						Double temp = Double.parseDouble(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Boolean".equalsIgnoreCase(fieldType)) {
						Boolean temp = Boolean.parseBoolean(value);
						fieldSetMet.invoke(bean, temp);
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
		
		return bean;
	}

	private static Date parseDate(String datestr) {
		if (null == datestr || "".equals(datestr)) {
			return null;
		}
		try {
			String fmtstr = null;
			if (datestr.indexOf(':') > 0) {
				fmtstr = "yyyy-MM-dd HH:mm:ss";
			} else {
				fmtstr = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
			return sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}
	}

	private static boolean checkSetMet(Method[] methods, String fieldSetMet) {
		for (Method met : methods) {
			if (fieldSetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	private static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}

	private static Field[] getAllFields(Object object) {
		Class clazz = object.getClass();
		List<Field> fieldList = new ArrayList<Field>();
		while (clazz != null) {
			fieldList.addAll(new ArrayList<Field>(Arrays.asList(clazz
					.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}

	private static Method[] getAllMethods(Object object) {
		Class clazz = object.getClass();
		List<Method> fieldList = new ArrayList<Method>();
		while (clazz != null) {
			fieldList.addAll(new ArrayList<Method>(Arrays.asList(clazz.getDeclaredMethods())));
			clazz = clazz.getSuperclass();
		}
		Method[] methods = new Method[fieldList.size()];
		fieldList.toArray(methods);
		return methods;
	}
	
	public static Method getMethod(Class<?> cls, String name, Class<?>... parameterTypes){
		while (cls != null) {
			Method fieldSetMet = null;
			try {
				fieldSetMet = cls.getMethod(name, parameterTypes);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			if(fieldSetMet != null){
				return fieldSetMet;
			}
			cls = cls.getSuperclass();
		}
		return null;
	}
}