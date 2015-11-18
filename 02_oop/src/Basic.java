
public abstract class Basic implements Comparable<Basic> {

	protected String color;
	protected double balance;

	/**
	 * Writes to console StringBuilder
	 * 
	 * @param stringBuilder
	 * @param String
	 * @return StringBuilder that had been written
	 */
	abstract void write(StringBuilder mainStr, String strwrite);

	/**
	 * Uses for "preparation" of writer
	 */
	protected void prepearing() {

		balance = 100;
	}

	/**
	 * Uses for sorting by balance field
	 */
	@Override
	public int compareTo(Basic o) {

		return (int) (this.balance * 100 - o.balance * 100);

	}
}
