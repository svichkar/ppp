/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringworkshop;

import java.util.Arrays;

/**
 *
 * @author Mednor
 */
public class StringWorkshop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new StringWorkshop().javaForever();
        System.out.println(new StringWorkshop().shortName("Шевченко Валреий Юрьевич"));
        System.out.println(new StringWorkshop().checkAnagrama("Mast.er!_era", "retsaM are"));

    }

    public void javaForever() {
        String java = "Java";
        String forever = "forever";
        String firstOption = java + " " + forever;
        String secondOption = java.concat(" ").concat(forever);
        String thirdOption = new StringBuilder(java).append(" ").append(forever).toString();
        String fourthOption = new StringBuffer(java).append(" ").append(forever).toString();
        String fifthOption = new String(java + " " + forever);
    }

    /**
     * @param fullName - Full name with spaces
     * @return string of short name
     */
    public String shortName(String fullName) {
        String[] credentials = fullName.split(" ");
        String shortname = "";
        for (String credential : credentials) {
            shortname = shortname + credential.substring(0, 1);
        }
        return shortname;
    }

    /**
     * @param firstWord - first string to compare
     * @param secondWord - second string to compare
     * @return true if the words are anagrama
     */
    public boolean checkAnagrama(String firstWord, String secondWord) {
        char[] firstWordChars = firstWord.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}]", "")
                .toCharArray();
        char[] secondWordChars = secondWord.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}]", "")
                .toCharArray();
        if (firstWordChars.length == secondWordChars.length) {
            Arrays.sort(firstWordChars);
            Arrays.sort(secondWordChars);
            for (int i = 0; i < firstWordChars.length; i++) {
                if (firstWordChars[i] != secondWordChars[i]) {
                    return false;
                }

            }
        }
        return true;

    }

}
