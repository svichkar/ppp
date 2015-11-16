/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nixsolutions;

/**
 *
 * @author mednorcom Task1: Создать com.nixsolutions.Arrays со статическим
 * методом sort который на вход будет получать массив целых чисел (int). В
 * методе реализовать сортировку методом пузырька. Исходный массив не должен
 * изменяться методом. Написать программу которая будет в цикле (20 раз) создать
 * массив размером 10000 из случайных целых чисел состоящих от -100 до 100, и
 * запускать сортировку пузырьком и метод сортировки из класса java.util.Arrays.
 * При каждой итерации замерять время выполнения сортировки каждым способом и
 * результаты складывать в двумерный массив. После окончания цикла вывести
 * среднее для значений, полученных каждым методом сортировки.
 */
public class Arrays {

    public static int[] sort(int[] inArray) {
        int[] outArray = inArray.clone();
        int j;
        boolean flag = true;   // set flag to true to begin first pass
        int temp;   //holding variable
        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (j = 0; j < outArray.length - 1; j++) {
                if (outArray[j] < outArray[j + 1]) { // change to > for ascending sort
                    temp = outArray[j];                //swap elements
                    outArray[j] = outArray[j + 1];
                    outArray[j + 1] = temp;
                    flag = true;              //shows a swap occurred  
                }
            }
        }
        return outArray;
    }

}
