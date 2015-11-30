/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflectionsworkshop;

/**
 *
 * @author mednorcom
 */
public class AnnotatedClass {

    private String privateField;
    public String publicField;
    @Public
    private String annotatedField;

    public AnnotatedClass() {
        this.privateField = "I'm very private";
        this.publicField = "I'm very public";
        this.annotatedField = "I'm very annotated";

    }

}
