package com.nixsolutions;

import java.util.*;

public class Main {

    public static void main(String[] args) {



    }

    	/*����������� �����-��������, �� ����� ���������� Map �� ��������� ������ � ������� ����� Number
	(������� �����������) ��� ��������, �� ������ ������������ Map � ��� �� ������ � Double
	(����� ������ �� ����� �����) ��� ��������, �������� ������������� �� �������*/

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



    /*����������� ��������� Converter � ������� get, �� ���� ����������� ����������������� ��� I
    � ������������ ������������������� ��� T. ����������� 2 ������ ���������� - ���� ��������������� Float � Double,
    ������ - ������ Integer � String (������������ ���� ��������� ����� ������). �������� ������������� �� �������*/


}
