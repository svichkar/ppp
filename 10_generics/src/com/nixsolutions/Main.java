package com.nixsolutions;

import java.util.*;

public class Main {

    public static void main(String[] args) {



    }

    	/*Реализовать метод-сумматор, на входе получающий Map со строковым ключем и списком любых Number
	(включая наследников) как значение, на выходе возвращающим Map с тем же ключем и Double
	(сумма списка по этому ключу) как значение, показать использование на примере*/

    public static Map<String, Double>  sumMethodForNumbersMap(Map<String, ArrayList<? extends Number>> inputMap){
        Map<String,Double> outputMap = new HashMap<>();
        for(Map.Entry<String, List<? extends Number>> itemOfHashMap : inputMap.entrySet()){
            List curArr = itemOfHashMap.getValue();
            Double resultValueForCurrentKey = new Double(0);
            for(Number elemOfCurArr : curArr){
                resultValueForCurrentKey += (Double) elemOfCurArr;
            }
        }

    }



    /*Реализовать интерфейс Converter с методом get, на вход принимающим параметризованный тип I
    и возвращающий параметризированный тип T. Реализовать 2 класса наследника - один преобразовывает Float в Double,
    другой - массив Integer в String (конкатенация всех элементов через пробел). Показать использование на примере*/


}
