package Learn;

import java.util.ArrayList;
import java.util.Scanner;

public class Learn {
    public static void main(String[] args) {

        int[] array2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i < array2.length; i++) { // i= 1 10
            System.out.println(array2 [i]);

        }

        ArrayList<String>  arrayList = new ArrayList<>();
         arrayList.add("Dani");
         arrayList.add("Pichi");

         for (String a :  arrayList) {
             System.out.println(a);
         }




    }
}
