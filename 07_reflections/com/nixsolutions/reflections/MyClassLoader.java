package com.nixsolutions.reflections;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * this class allows to load classes from a custom defined path
 * 
 * @author kryzhanovskiy
 *
 */
public class MyClassLoader extends ClassLoader implements PathClassLoader {
	private static final Logger LOG = LogManager.getLogger();
	private String directory;

	@Override
	public void setPath(String dir) {
		this.directory = dir;
	}

	@Override
	public String getPath() {
		return this.directory;
	}

	protected Class<?> findClass(String name) throws ClassNotFoundException {
		LOG.entry("class name: " + name + "; path: " + getPath());
		Class<?> result;
		byte classData[] = null;

		Path path = Paths.get(getPath(),
				name.replace(".", FileSystems.getDefault().getSeparator())
						+ ".class");
		LOG.debug(path.toString());

		if (Files.notExists(path)) {
			return LOG.exit(super.findClass(name));
		} else {
			try {
				classData = Files.readAllBytes(path);
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
			result = defineClass(null, classData, 0, classData.length);
			return LOG.exit(result);
		}
	}
}