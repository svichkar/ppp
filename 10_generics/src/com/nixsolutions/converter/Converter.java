package com.nixsolutions.converter;

/**
 * Created by pixtart on 12/5/2015.
 */
public interface Converter<T, I> {
    public T get(I i);
}




    /*����������� ��������� Converter � ������� get, �� ���� ����������� ����������������� ��� I
    � ������������ ������������������� ��� T. ����������� 2 ������ ���������� - ���� ��������������� Float � Double,
    ������ - ������ Integer � String (������������ ���� ��������� ����� ������). �������� ������������� �� �������*/