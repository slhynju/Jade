package org.jewel.util.io;

import java.nio.file.Path;

import org.jewel.util.collection.Transformer;

public class PathTransformer implements Transformer<String, Path> {

	@Override
	public Path transform(String pathName) {
		return PathUtil.toPath(pathName);
	}

}
