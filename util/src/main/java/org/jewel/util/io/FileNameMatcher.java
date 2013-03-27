package org.jewel.util.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.jewel.util.collection.Matcher;
import org.slf4j.Logger;

public class FileNameMatcher implements Matcher<Path>, FileVisitor<Path> {

	private final PathMatcher pathMatcher;

	private final List<Path> paths;

	private final Logger log;

	public FileNameMatcher(Path dir, String glob, Logger log) {
		pathMatcher = dir.getFileSystem().getPathMatcher(glob);
		paths = new ArrayList<>();
		this.log = log;
	}

	@Override
	public boolean matches(Path path) {
		Path fileName = path.getFileName();
		if (fileName == null) {
			return false;
		}
		return pathMatcher.matches(fileName);
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		if (matches(file)) {
			paths.add(file);
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc)
			throws IOException {
		log.warn("Failed to access file {}. Continue with other files.", file);
		return FileVisitResult.CONTINUE;
	}

	public List<Path> getPaths() {
		return paths;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
			throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc)
			throws IOException {
		if (log.isWarnEnabled()) {
			StringBuilder sb = new StringBuilder("Exception when access dir ");
			sb.append(dir.toString()).append(". Continue with other files.");
			log.warn(sb.toString(), exc);
		}
		return FileVisitResult.CONTINUE;
	}

}
