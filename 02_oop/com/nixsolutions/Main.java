package com.nixsolutions;

import java.util.Random;

/** Implements the task from lab 02_oop. */
public class Main {
  private static final int NUMBER_OF_ITEM_TYPES = 3;
  private static final int NUMBER_OF_ITEM_INSTANCES = 10;
  private static final int NUMBER_OF_USE_LOOPS = 10;
  private static final int RANDOM_SB_MIN = 3;
  private static final int RANDOM_SB_MAX = 5;
  private static final String S = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private static Random random = new Random();
  private static WritingInstrument[] items = new WritingInstrument[NUMBER_OF_ITEM_INSTANCES];

  /**
   * Fires the methods to create array of items, prepare them,
   * use them, and finally to sort and show the results. 
   */
  public static void main(String[] args) {
    createItems();
    useItems();
    sortItems();
  }

  /**
   * Creates and prepares the items, puts them to the array,
   * prints status message for each new item.
   */
  protected static void createItems() {
    System.out.println("Creating new items and preparing them...\n");
    for (int i = 0; i < items.length; i++) {
      int randomIndex = random.nextInt(NUMBER_OF_ITEM_TYPES);
      items[i] = instantiateItem(randomIndex);
      items[i].index = i + 1;

      if (items[i] != (null)) {
        if (ensureItemPrepared(items[i])) {
          printItemStatus(items[i], "is ready.");
        } else {
          printItemStatus(items[i], "is NOT ready!");
        }
      } else {
        System.out.println("Item #" + items[i].index + " is NOT created!");
      }
    }
  }

  /**
   * Creates the item according to randomly generated index,
   * then calls object's initialization method.
   * @param randomIndex Represents the type of item.
   * @return Returns the reference to the initialized object.
   */
  protected static WritingInstrument instantiateItem(int randomIndex) {
    WritingInstrument newItem;
    if (randomIndex == 0) {
      newItem = new Pen();
    } else if (randomIndex == 1) {
      newItem = new Pencil();
    } else if (randomIndex == 2) {
      newItem = new FeltTipPen();
    } else {
      newItem = null;
    }
    newItem.initialize();
    return newItem;
  }
  
  /**
   * Makes sure the item is prepared to work. If it's not prepared yet,
   * calls its preparation method.
   * @param item The item to work with.
   * @return Returns the status of the item (prepared / not prepared).
   */
  protected static boolean ensureItemPrepared(WritingInstrument item) {
    if (!item.prepared) {
      item.prepare();
    }
    return item.prepared;
  }

  /**
   * Prints status message combined from data received from parameters
   * and from the details of the item.
   * @param item The item to work with.
   * @param s The string to add to the status message.
   */
  protected static void printItemStatus(WritingInstrument item, String s) {
    System.out
        .println("\nItem #" + item.index + " of type " + getItemType(item) + " (" + item.charsWritten
            + " chars written, " + item.pigmentLeftPercent.toString() + "% left) " + s);
  }

  /**
   * Gets the type of the item by calling its method to return the type.
   * @param item The item to work with.
   * @return Returns the type of the item.
   */
  protected static String getItemType(WritingInstrument item) {
    return item.returnType();
  }

  /**
   * Writes randomly generated strings NUMBER_OF_USE_LOOPS times using
   * each of the items available in items array. If the item can erase,
   * then asks it to erase one last character from the last string.
   * Also prints status messages. Items are printing their results by
   * themselves. 
   */
  protected static void useItems() {
    System.out.println("\nWriting " + NUMBER_OF_USE_LOOPS + " times using each of the "
        + NUMBER_OF_ITEM_INSTANCES + " in the inventory:\n");
    for (int i = 0; i < NUMBER_OF_ITEM_INSTANCES; i++) {
      for (int j = 0; j < NUMBER_OF_USE_LOOPS; j++) {
        StringBuilder sb = randomStringBuilder();
        printItemStatus(items[i], "writes \"" + sb + "\":");
        items[i].write(sb);
        System.out.println();
        if (items[i].canErase && !items[i].lowOnPigment) {
          printItemStatus(items[i], "can erase, so erases last char in \"" + sb + "\":");
          items[i].erase(sb);
        }
      }
    }
  }

  /**
   * Generates random StringBuilder from the alphabet in the S constant,
   * and the length of the string is based on the inclusive range
   * from RANDOM_SB_MIN to RANDOM_SB_MAX.
   * @return Returns generated StringBuilder.
   */
  protected static StringBuilder randomStringBuilder() {
    int stringLength = random.nextInt(RANDOM_SB_MAX - RANDOM_SB_MIN + 1) + RANDOM_SB_MIN;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < stringLength; i++) {
      sb.append(S.charAt(random.nextInt(S.length())));
    }
    return sb;
  }

  /**
   * Sorts the items by the percentage of pigment left, and prints
   * the results.
   */
  protected static void sortItems() {
    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for (int i = 1; i < items.length; i++) {
        if (items[i - 1].pigmentLeftPercent.compareTo(items[i].pigmentLeftPercent) > 0) {
          WritingInstrument temp = items[i - 1];
          items[i - 1] = items[i];
          items[i] = temp;
          swapped = true;
        }
      }
    }

    System.out.println("\nSorting items by percentage of pigment left:\n");
    for (int i = 0; i < items.length; i++) {
      printItemStatus(items[i], "");
    }
  }

}
