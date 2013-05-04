package org.jade.util.reflect;

import java.lang.reflect.Method;

import org.jade.util.collection.Matcher;

public final class SetterMatcher implements Matcher<Method> {

	@Override
	public boolean matches(Method m) {
		return ClassUtil.isSetter(m);
	}

}
