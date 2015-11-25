package exception;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import exception.MyOwnExeption;

/**
 * The Class Exp.
 */
public abstract class Exp implements Save {

	@Override
	public void save(String stringForSaving, String absolutPathToFile) {
		File file = new File(absolutPathToFile);
		try {
			if (stringForSaving.isEmpty()) {
				throw new MyOwnExeption(
						"String for saving is empty! Please enter data to string for saving in the file.");
			} else {
				if (file.exists()) {
					System.out.println("Rewriting input string to file.");
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.append(stringForSaving);
					fileWriter.flush();
					fileWriter.close();
					System.out.println("String is saved to file.");
				} else {
					System.out.println("Creating file for writing.");
					file.createNewFile();
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.append(stringForSaving);
					fileWriter.flush();
					fileWriter.close();
					System.out.println("String is saved to file.");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new MyOwnExeption();
		}

	}
}
