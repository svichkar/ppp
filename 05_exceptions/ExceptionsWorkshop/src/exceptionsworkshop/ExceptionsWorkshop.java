/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionsworkshop;

/**
 * Task exceptions
 *
 * @author mednorcom
 */
public class ExceptionsWorkshop {

    /**
     * Task exceptions endpoint
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            new FileSaver().save("teststring", "e2:\\out.txt");
        } catch (exceptionsworkshop.RuntimeIOException ex) {
            System.out.print("File write Error; Please check the details:\n" + ex.toString()
                    + "\n");
        } catch (NullPointerException ex) {
            System.out.print("Null pointer Exception; Please check the details:\n"
                    + ex.toString() + "\n");
        }
    }

}
