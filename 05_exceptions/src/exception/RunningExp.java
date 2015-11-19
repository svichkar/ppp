package exception;

public class RunningExp extends Exp {
	private static String workDir;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		RunningExp run = new RunningExp();
		workDir = System.getProperty("user.dir");
		run.save("Laba #5. Exeption. ", workDir + "\\Laba1.txt");
	}

}
