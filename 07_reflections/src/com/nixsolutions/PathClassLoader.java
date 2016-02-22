package com.nixsolutions;

/**
 * The Interface PathClassLoader.
 */
public interface PathClassLoader {
	
	/**
	 * Sets the path.
	 *
	 * @param dir the new path
	 */
	void setPath(String dir);

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	String getPath();
}
