package savepackage;

import java.io.*;

public class Savewrite implements exception.Save {

	private PrintWriter out;

	@Override
	public void save(String path, String text) throws RuntimeException {

		File flt = new File(path);
		try {
			if (!flt.exists()) {
				try {
					flt.createNewFile();
				} catch (IOException e) {

					throw new RuntimeException(e);
				}
			}

			out = new PrintWriter(flt.getAbsoluteFile());

			out.print(text);
			

		} catch (IOException e) {
			throw new RuntimeException(e);

		}
	}
}
