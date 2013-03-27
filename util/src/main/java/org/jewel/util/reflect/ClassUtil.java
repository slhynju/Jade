package org.jewel.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jewel.JewelException;
import org.jewel.util.FreeStringBuilder;
import org.jewel.util.collection.CollectionUtil;

public final class ClassUtil {

	public static Set<Class<?>> getSuperClasses(Class<?> c) {
		Set<Class<?>> superClazz = new HashSet<>(4);
		Class<?> superClass = c.getSuperclass();
		while (superClass != null) {
			superClazz.add(superClass);
			superClass = superClass.getSuperclass();
		}
		CollectionUtil.addAll(superClazz, c.getInterfaces());
		return superClazz;
	}

	public static boolean isAbstract(Class<?> c) {
		return Modifier.isAbstract(c.getModifiers());
	}

	public static boolean isAbstractClass(Class<?> c) {
		return isAbstract(c) && !c.isInterface();
	}

	public static boolean isImpl(Class<?> c) {
		int modifier = c.getModifiers();
		return !Modifier.isAbstract(modifier) && Modifier.isPublic(modifier)
				&& c.getConstructors().length > 0;
	}

	public static boolean isImplOf(Class<?> impl, Class<?> superClass) {
		return isImpl(impl) && superClass.isAssignableFrom(impl);
	}

	public static String getPackageName(Class<?> c) {
		Package p = c.getPackage();
		if (p == null) {
			return "";
		}
		return p.getName();
	}

	public static boolean isStatic(Method m) {
		return Modifier.isStatic(m.getModifiers());
	}

	public static boolean isVoidReturnType(Method m) {
		return Void.TYPE.equals(m.getReturnType());
	}

	public static boolean isGetter(Method m) {
		String name = m.getName();
		int modifier = m.getModifiers();
		return (name.startsWith("get") || name.startsWith("is"))
				&& Modifier.isPublic(modifier) && !Modifier.isStatic(modifier)
				&& m.getParameterTypes().length == 0 && !isVoidReturnType(m);
	}

	public static boolean isSetter(Method m) {
		int modifier = m.getModifiers();
		return m.getName().startsWith("set") && Modifier.isPublic(modifier)
				&& !Modifier.isStatic(modifier)
				&& m.getParameterTypes().length == 1 && isVoidReturnType(m);
	}

	public static List<Method> getGetters(Class<?> c) {
		return CollectionUtil.sub(c.getMethods(), new GetterMatcher());
	}

	public static List<Method> getSetters(Class<?> c) {
		return CollectionUtil.sub(c.getMethods(), new SetterMatcher());
	}

	public static Set<Class<?>> getDependencyClasses(Constructor<?> constructor) {
		return CollectionUtil.toSet(constructor.getParameterTypes());
	}

	public static Set<Type> getDependencyTypes(Constructor<?> constructor) {
		return CollectionUtil.toSet(constructor.getGenericParameterTypes());
	}

	public static Set<Class<?>> getDependencyClasses(Method method) {
		return CollectionUtil.toSet(method.getParameterTypes());
	}

	public static Set<Type> getDependencyTypes(Method method) {
		return CollectionUtil.toSet(method.getGenericParameterTypes());
	}

	public static Class<?> getSetterDependencyClass(Method setter) {
		return setter.getParameterTypes()[0];
	}

	public static Type getSetterDependencyType(Method setter) {
		return setter.getGenericParameterTypes()[0];
	}

	public static Set<Class<?>> getAllConstructorDependencyClasses(Class<?> c) {
		Set<Class<?>> dependencyClazz = new HashSet<>(4);
		for (Constructor<?> con : c.getConstructors()) {
			CollectionUtil.addAll(dependencyClazz, con.getParameterTypes());
		}
		return dependencyClazz;
	}

	public static Set<Class<?>> getAllSetterDependencyClasses(Class<?> c) {
		Set<Class<?>> dependencyClazz = new HashSet<>(4);
		for (Method m : c.getMethods()) {
			if (isSetter(m)) {
				dependencyClazz.add(getSetterDependencyClass(m));
			}
		}
		return dependencyClazz;
	}

	public static Set<Type> getAllSetterDependencyTypes(Class<?> c) {
		Set<Type> dependencyTypes = new HashSet<>(4);
		for (Method m : c.getMethods()) {
			if (isSetter(m)) {
				dependencyTypes.add(getSetterDependencyType(m));
			}
		}
		return dependencyTypes;
	}

	public static List<Method> getMethods(Class<?> c, String methodName) {
		List<Method> methods = new ArrayList<>(4);
		for (Method m : c.getMethods()) {
			if (methodName.equals(m.getName())) {
				methods.add(m);
			}
		}
		return methods;
	}

	public static Method getMethod(Class<?> c, String methodName,
			Class<?>... paramTypes) {
		try {
			return c.getMethod(methodName, paramTypes);
		} catch (SecurityException e) {
			FreeStringBuilder message = new FreeStringBuilder(
					"Cannot find method ");
			message.append(methodName);
			message.append(' ');
			message.append(paramTypes, "(", ", ", ")");
			message.append(" in Class ");
			message.append(c);
			message.append(" due to SecurityException.");
			throw new JewelException(message, e);
		} catch (NoSuchMethodException e) {
			return null;
		}
	}

	public static Method getStaticMethod(Class<?> c, String methodName,
			Class<?>... paramTypes) {
		Method m = getMethod(c, methodName, paramTypes);
		if (m != null && isStatic(m)) {
			return m;
		}
		return null;
	}

	public static boolean hasAnnotation(AccessibleObject member,
			Class<? extends Annotation> a) {
		return member.getAnnotation(a) != null;
	}

}
