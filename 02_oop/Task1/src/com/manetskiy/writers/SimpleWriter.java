package com.manetskiy.writers;


public abstract class SimpleWriter {

    private float initialDurability;
    private float currentDurability;

    /**
     * Constructs and initializes object of SimpleWriter with initial durability
     *
     * @param initialDurability initial durability, must be &gt;= 1.
     */
    public SimpleWriter(float initialDurability) {
        this.initialDurability = initialDurability;
        currentDurability = initialDurability;
    }

    /**
     * Reduces durability of writing means in percents.
     *
     * @param deduction value in percents
     */
    protected void reduceDurability(float deduction) {
        currentDurability = initialDurability - (initialDurability - ((deduction * initialDurability) / 100));
    }

    /**
     * Returns current durability of writing means in percents.
     *
     * @return percents of durability
     */
    protected float getDurability() {
        return (currentDurability * 100) / initialDurability;
    }

    /**
     * Writes an input text and reduces durability of writing means.
     *
     * <p>Note that depending on implementations returned string
     * can be truncated if <code>currentDurability</code> cannot be reduced
     * anymore by <code>reduceDurability(float deduction)</code> method.</p>
     *
     * @param text StringBuilder object
     * @return StringBuilder object
     */
    protected abstract StringBuilder write(StringBuilder text);

    /**
     * Deletes char at specified position
     *
     * @param text      StringBuilder object
     * @param charIndex char at specified position
     * @return StringBuilder object without deleted char
     */
    protected abstract StringBuilder erase(StringBuilder text, int charIndex);

    public String toString() {
        return getClass().getSimpleName() + ". Current durability: " + String.format("%.2f", getDurability()) + "%";
    }

}
