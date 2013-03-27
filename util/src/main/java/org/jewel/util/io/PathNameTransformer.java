package org.jewel.util.io;

import java.nio.file.Path;

import org.jewel.util.collection.Transformer;

public class PathNameTransformer implements Transformer<Path, String> {

	@Override
	public String transform(Path path) {
		if (path == null) {
			return "";
		}
		return path.toString();
	}

}
