package org.jade.util.collection.tree;

import org.jade.util.collection.Matcher;

public class RootMatcher implements Matcher<TreeNode<?>> {

	@Override
	public boolean matches(TreeNode<?> node) {
		return node.isRoot();
	}

}
