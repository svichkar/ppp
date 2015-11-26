/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericsworkshop;

/**
 *
 * @author mednorcom
 */
public class IntArrayToString implements Converter<Integer[], String> {

    @Override
    public String get(Integer[] i) {
        String out = "";
        for (Integer item : i) {
            out = out + " " + item.toString();
        }
        return out.trim();
    }

}
