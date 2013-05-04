package org.jade.util.io;

import java.nio.file.Path;

import org.jade.util.collection.Transformer;

public class PathTransformer implements Transformer<String, Path> {

	@Override
	public Path transform(String pathName) {
		return PathUtil.toPath(pathName);
	}

}
