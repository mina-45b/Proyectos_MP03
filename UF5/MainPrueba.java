package Proyectos_MP03.UF5;

import java.util.HashMap;

public class MainPrueba {
    public static void main(String[] args) {

            // Create a HashMap and add some values
            HashMap<String, Integer> count= new HashMap<>();
            count.put("Yellow", 1);
            count.put("Black", 2);
            count.put("Green", 3);

            System.out.println("Count before :\n "+ count);

            count.computeIfPresent("Black", (key, val) -> val + 100);

            System.out.println("Count After :\n " + count);

        HashMap<String, Integer> prices = new HashMap<>();
        prices.put("Mustang", 10000);
        prices.put("Megane", 55000);
        prices.put("Toledo", 44300);
        prices.put("I30", 53200);

        // print map details
        System.out.println("Car prices before update: " + prices.toString());

        prices.computeIfAbsent("A6", k -> 2000 + 33000);
        prices.computeIfAbsent("A5", k -> 2000 * 34);

        System.out.println("Car prices after update " + prices);
    }
}
