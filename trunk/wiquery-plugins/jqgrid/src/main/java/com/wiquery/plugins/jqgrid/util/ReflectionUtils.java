package com.wiquery.plugins.jqgrid.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.lang.StringUtils;

public class ReflectionUtils {

	public static final String NESTED_PROPERTY_SEPARATOR = ".";

	public static Field getPropertyField(Class<?> beanClass,String property) throws NoSuchFieldException {
		if (beanClass == null)
			throw new IllegalArgumentException("beanClass cannot be null");
		if (StringUtils.isEmpty(property))
			throw new IllegalArgumentException("propertyPath cannot be empty");

		Field field = null;
		try {
			field = beanClass.getDeclaredField(property);
		} catch (NoSuchFieldException e) {
			if (beanClass.getSuperclass() == null)
				throw e;
			// look for the field in the superClass
			field = getPropertyField(beanClass.getSuperclass(),property);
		}
		return field;
	}

	public static Field getField(Class<?> beanClass,String propertyPath) throws NoSuchFieldException {
 		if (beanClass == null)
			throw new IllegalArgumentException("beanClass cannot be null");
		if (StringUtils.isEmpty(propertyPath))
			throw new IllegalArgumentException("propertyPath cannot be empty");

		if (propertyPath.indexOf("[") != -1)
			propertyPath = propertyPath.substring(0,propertyPath.indexOf("["));

		// if the property path is simple then look for it directly on the class.
		if (propertyPath.indexOf(NESTED_PROPERTY_SEPARATOR) == -1) {
			// look if the field is declared in this class.
			return getPropertyField(beanClass,propertyPath);
		} else {
			// if the property is a compound one then split it and look for the first field.
			// and recursively locate fields of fields.
			String propertyName = propertyPath.substring(0,propertyPath.indexOf(NESTED_PROPERTY_SEPARATOR));
			Field field = getField(beanClass,propertyName);

			// try to locate sub-properties
			return getField(getTargetType(field),propertyPath.substring(propertyPath.indexOf(NESTED_PROPERTY_SEPARATOR)+1));
		}
	}

	public static Class<?> getTargetType(Field field) {
		//	Generic type, case when we have a Collection of ?
		if (field.getGenericType() instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType)field.getGenericType();
			if (type.getActualTypeArguments().length == 1 && type.getActualTypeArguments()[0] instanceof Class<?>)
			return (Class<?>)type.getActualTypeArguments()[0];
		}

		return field.getType();
	}

	public static Class<?> getPropertyClass(Class<?> beanClass,String propertyPath) {
		try {
			Field field = getField(beanClass, propertyPath);
			return(getTargetType(field));
		} catch (NoSuchFieldException e) {
			throw new IllegalArgumentException(propertyPath+" is not a property of "+beanClass.getName());
		}
	}

	public static boolean isFieldDeclared(Class<?> beanClass,String propertyPath) {
		try {
			return getField(beanClass, propertyPath) != null;
		} catch(NoSuchFieldException e) {
			return false;
		}
	}

	public static Object getPropertyValue(Object bean,String propertyPath) throws NoSuchFieldException {
		if (bean == null)
				throw new IllegalArgumentException("bean cannot be null");
        StringBuffer getterName = new StringBuffer();
        getterName.append( "get" ).append( propertyPath.substring( 0, 1 ).toUpperCase() ).append( propertyPath.substring( 1 ) );
        try
        {
            Method getter = bean.getClass().getMethod( getterName.toString(), new Class[]{} );
            Object value = getter.invoke( bean, new Object[]{} );
            return value;
        }
        catch ( Exception e )
        {
            // fall through
        }
			Field field = ReflectionUtils.getField(bean.getClass(), propertyPath);
			field.setAccessible(true);
			try {
				return(field.get(bean));
			} catch (IllegalAccessException e) {
				return(null);
		}
	}
}