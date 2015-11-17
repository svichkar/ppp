/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * OOP task
 *
 * @author mednorcom
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        //создания массива рандомных пишущих средств размером в 10 элементов 
        //и подготовка их к работе.
        int amountOfTools = 10;
        AbstractWriter[] writingTools = new AbstractWriter[amountOfTools];
        Class[] availableTools = new Class[]{Marker.class, Pen.class, Pencil.class};
        String[] availableColors = new String[]{"Red", "Green", "Blue", "Cyan",
            "Magenta", "Yellow", "Black"};
        Random rand = new Random();
        for (int i = 0; i < amountOfTools; i++) {
            writingTools[i] = (AbstractWriter) availableTools[rand
                    .nextInt(availableTools.length)].getConstructor(String.class)
                    .newInstance(availableColors[rand.nextInt(availableColors.length)]);
            writingTools[i].prepare();
        }

        //трехкратное написание в StringBuilder 3-5 символов каждым из устройств, 
        //если устройство умеет стирать то стираем последний символ в каждой итерации.
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder strResult = new StringBuilder();
        for (AbstractWriter particularTool : writingTools) {
            for (int i = 0; i < 3; i++) {
                strResult.append(particularTool.write(new StringBuilder(new BigInteger(rand.
                        nextInt(3) * 5 + 15, secureRandom).toString(32))));
                for (Method method : particularTool.getClass().getMethods()) {
                    if (method.getName().equals("erase")) {
                        strResult = ((AbstractErasableWriter) particularTool).erase(strResult);
                        break;
                    }
                }
            }
        }
        System.out.println(strResult.toString());

        //сортировка устройств по остатку пишущего средства в %.
        Arrays.sort(writingTools, new Comparator<AbstractWriter>() {
            @Override
            public int compare(AbstractWriter o1, AbstractWriter o2) {

                return (o2.getRemainingResource().compareTo(o1.getRemainingResource()));
            }
        }
        );

        for (AbstractWriter particularTool : writingTools) {
            System.out.printf("%.1f%%\n", particularTool.getRemainingResourcePercentage());
        }

    }

}
