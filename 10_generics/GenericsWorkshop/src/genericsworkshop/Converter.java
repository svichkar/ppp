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
public interface Converter<I, T> {

    public T get(I i);
}
