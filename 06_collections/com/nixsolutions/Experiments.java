package com.nixsolutions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Experiments {
	public static void main(String[] args) {

		Collection<Integer> customCol = genRandCusColl(50);
		Collection<Integer> regularCol = genRandRegCol(100);

		// check adAll
		customCol.addAll(regularCol);
		System.out.println("size after addAll= " + customCol.size());

		// check containsAll - has to be true as we added all the elements in
		// the prev step
		System.out.println(
				"is it containsAll= " + customCol.containsAll(regularCol));

		// check removeAll
		customCol.removeAll(regularCol);
		System.out.println("size after removeAll= " + customCol.size());

		// check contains - has to be false in all cases as we removed all
		// similarities in previous step
		for (Integer integer : regularCol) {
			if (customCol.contains(integer)) {
				System.out.println("that is not possible");
			}
		}

		// check retainAll
		customCol = genRandCusColl(50);
		customCol.retainAll(regularCol);
		System.out.println("size after retainAll= " + customCol.size());

		// check iterator
		System.out.println("check iterator");
		for (Iterator i = customCol.iterator(); i.hasNext();) {
			System.out.println(i.next());
		}
	}

	public static Collection<Integer> genRandCusColl(int size) {
		int min = 0;
		int max = 100;
		Random rand = new Random();

		Collection<Integer> randColl = new CustomCollection<>();
		for (int i = 0; i < size; i++) {
			int randomNum = rand.nextInt((max - min) + 1) + min;
			randColl.add(randomNum);
		}
		return randColl;
	}

	public static Collection<Integer> genRandRegCol(int size) {
		int min = 0;
		int max = 100;
		Random rand = new Random();

		List<Integer> randList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			int randomNum = rand.nextInt((max - min) + 1) + min;
			randList.add(randomNum);
		}
		return randList;
	}
}
