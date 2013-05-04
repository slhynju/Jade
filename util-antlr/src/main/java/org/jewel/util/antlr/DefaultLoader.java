package org.jewel.util.antlr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.Tree;
import org.jade.JadeException;
import org.jade.util.io.PathUtil;

/**
 * Upgrade to ANTLR 4
 * @author slhynju
 * @param <T>
 */
public abstract class DefaultLoader<T> {

	public T load(Path path, Charset charset) {
		CharStream charStream = openFile(path, charset);
		Lexer lexer = buildLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		Tree tree = parseTokenStream(tokenStream);
		return parseTree(tree);
	}

	private static CharStream openFile(Path path, Charset charset) {
		BufferedReader reader = PathUtil.readTextFile(path, charset);
		try {
			return new ANTLRReaderStream(reader);
		} catch (IOException e) {
			StringBuilder sb = new StringBuilder("Failed to read file ");
			sb.append(path).append(" with Charset ").append(charset)
					.append('.');
			throw new JadeException(sb, e);
		}
	}

	protected abstract Lexer buildLexer(CharStream charStream);

	protected abstract Tree parseTokenStream(TokenStream tokenStream);

	protected abstract T parseTree(Tree tree);
}
