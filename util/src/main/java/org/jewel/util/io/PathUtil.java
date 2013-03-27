package org.jewel.util.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jewel.JewelException;
import org.jewel.util.StringUtil;
import org.jewel.util.collection.CollectionUtil;
import org.slf4j.Logger;

/**
 * Utility class of various IO operations. It is based on NIO2.0.
 * 
 * @author slhynju
 */
public final class PathUtil {

	public static List<Path> listAll(Path dir, String glob, Logger log) {
		FileNameMatcher matcher = new FileNameMatcher(dir, glob, log);
		try {
			Files.walkFileTree(dir, matcher);
		} catch (IOException e) {
			String message = String
					.format("Unexpected IOException occured when walking file tree %s.",
							dir);
			throw new JewelException(message, e);
		}
		return matcher.getPaths();
	}

	public static List<String> toNames(List<Path> paths) {
		return CollectionUtil.transform(paths, new PathNameTransformer());
	}

	public static List<Path> toPaths(List<String> pathNames) {
		return CollectionUtil.transform(pathNames, new PathTransformer());
	}

	public static Path toPath(String pathName) {
		if (StringUtil.isEmpty(pathName)) {
			return null;
		}
		return Paths.get(pathName);
	}

	public static Path openDirectory(String pathName) {
		Path path = toPath(pathName);
		if (path == null || !Files.exists(path)) {
			StringBuilder sb = new StringBuilder("Directory ");
			sb.append(pathName);
			sb.append(" doesn't exist.");
			throw new JewelException(sb);
		}
		if (!Files.isDirectory(path)) {
			StringBuilder sb = new StringBuilder("File ");
			sb.append(pathName);
			sb.append(" is not a directory.");
			throw new JewelException(sb);
		}
		if (!Files.isReadable(path)) {
			StringBuilder sb = new StringBuilder("Cannot read directory ");
			sb.append(pathName);
			sb.append('.');
			throw new JewelException(sb);
		}
		return path;
	}

	public static Path openFile(String pathName) {
		Path path = toPath(pathName);
		if (path == null || !Files.exists(path)) {
			StringBuilder sb = new StringBuilder("File ");
			sb.append(pathName);
			sb.append(" doesn't exist.");
			throw new JewelException(sb);
		}
		if (Files.isDirectory(path)) {
			StringBuilder sb = new StringBuilder("File ");
			sb.append(pathName);
			sb.append(" is a directory.");
			throw new JewelException(sb);
		}
		if (!Files.isReadable(path)) {
			StringBuilder sb = new StringBuilder("Cannot read File ");
			sb.append(pathName);
			sb.append('.');
			throw new JewelException(sb);
		}
		return path;
	}

	public static BufferedReader readTextFile(Path path) {
		return readTextFile(path, StandardCharsets.UTF_8);
	}

	public static BufferedReader readTextFile(Path path, Charset charset) {
		try {
			return Files.newBufferedReader(path, charset);
		} catch (IOException e) {
			StringBuilder message = new StringBuilder("Cannot open File ");
			message.append(path);
			message.append(" with Charset ");
			message.append(charset.name());
			message.append('.');
			throw new JewelException(message, e);
		}
	}

	public static void close(Reader reader, Logger log) {
		try {
			reader.close();
		} catch (IOException e) {
			if (log.isWarnEnabled()) {
				log.warn("Failed to close Reader.", e);
			}
		}
	}

	public static PrintWriter writeTextFile(Path path) {
		return writeTextFile(path, StandardCharsets.UTF_8);
	}

	public static PrintWriter writeTextFile(Path path, Charset charset) {
		try {
			return new PrintWriter(Files.newBufferedWriter(path, charset));
		} catch (IOException e) {
			String message = String.format(
					"Cannot write to File %s with Charset %s.", path, charset);
			throw new JewelException(message, e);
		}
	}

	public static void close(Writer writer, Logger log) {
		try {
			writer.close();
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("Failed to close Writer.", e);
			}
		}
	}

	public static List<String> readSmallTextFile(Path path) {
		return readSmallTextFile(path, StandardCharsets.UTF_8);
	}

	public static List<String> readSmallTextFile(Path path, Charset charset) {
		List<String> rawLines = readAllLines(path, charset);
		List<String> lines = new ArrayList<>(rawLines.size());
		for (String rawLine : rawLines) {
			lines.add(StringUtil.trim(rawLine));
		}
		return lines;
	}

	public static Map<String, String> readSmallPropertiesFile(Path path) {
		return readSmallPropertiesFile(path, StandardCharsets.UTF_8);
	}

	public static Map<String, String> readSmallPropertiesFile(Path path,
			Charset charset) {
		List<String> rawLines = readAllLines(path, charset);
		Map<String, String> map = new HashMap<>(rawLines.size());
		for (String rawLine : rawLines) {
			String line = StringUtil.trim(rawLine);
			if (line.isEmpty()) {
				continue;
			}
			if (line.startsWith("#")) {
				continue;
			}
			String[] pair = StringUtil.splitPair(line, "=");
			map.put(pair[0], pair[1]);
		}
		return map;
	}

	private static List<String> readAllLines(Path path, Charset charset) {
		try {
			return Files.readAllLines(path, charset);
		} catch (IOException e) {
			StringBuilder message = new StringBuilder(
					"IOException when reading File ");
			message.append(path);
			message.append(" with Charset ");
			message.append(charset.name());
			message.append('.');
			throw new JewelException(message, e);
		}
	}
}
