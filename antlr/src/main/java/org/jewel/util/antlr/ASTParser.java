package org.jewel.util.antlr;

import java.util.Set;

import org.antlr.runtime.tree.Tree;
import org.jewel.JewelException;
import org.jewel.util.FreeStringBuilder;
import org.jewel.util.collection.CollectionUtil;

public abstract class ASTParser<T> {

	public abstract T parse(Tree tree);

	public static void confirmNotNull(Tree tree) {
		if (tree == null) {
			throw new JewelException("Unexpected AST Tree. It is null.");
		}
	}

	public static void confirm(Tree tree, int... expectedTokens) {
		confirmNotNull(tree);
		Set<Integer> set = CollectionUtil.toSet(expectedTokens);
		if (set.contains(new Integer(tree.getType()))) {
			return;
		}
		FreeStringBuilder sb = new FreeStringBuilder("Unexpected AST tree. ");
		sb.appendWithoutBoundary(set)
				.append(" is expected while actual result is ")
				.append(tree.getType()).append(" with text ")
				.append(tree.getText()).append('.');
		throw new JewelException(sb);
	}

}
