package org.jewel.util.reflect;

import java.lang.reflect.Method;

import org.jewel.util.collection.Matcher;

public final class GetterMatcher implements Matcher<Method> {

	@Override
	public boolean matches(Method m) {
		return ClassUtil.isGetter(m);
	}

}
