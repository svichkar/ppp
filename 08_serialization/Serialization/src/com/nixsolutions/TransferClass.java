/**
 * 
 */
package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author mixeyes
 *
 */
public class TransferClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1676427996025682608L;

	private String human;
	private int age;
	

	public TransferClass(String name, int age) {
		human=name;
		this.age = age;
	}

	@Override
	public String toString() {
		return human+";"+age;
	}

}
