import java.util.Scanner;
import java.util.ArrayList;

public class Waage {
    private final static int[] weights = {1,3,8,20};

    public static void getWeightCombinations(ArrayList<String> basket, int weight) {
        getWeightCombinations(basket, 0, weight, new ArrayList<>(), 0);
    }

    public static void getWeightCombinations(ArrayList<String> basket, int cWeight, int weight, ArrayList<String> comb, int wIndex) {
        if (cWeight == weight) {
            basket.add(String.format("(%s)", String.join(", ", comb)));
            return;
        } else if (wIndex < weights.length) {
            ArrayList<String> tList1 = new ArrayList<>(comb);
            ArrayList<String> tList2 = new ArrayList<>(comb);
            tList1.add(String.valueOf(weights[wIndex]));
            tList2.add(String.valueOf(-1*weights[wIndex]));

            getWeightCombinations(basket, cWeight+weights[wIndex], weight, tList1, wIndex+1);
            getWeightCombinations(basket, cWeight, weight, comb, wIndex+1);
            getWeightCombinations(basket, cWeight-weights[wIndex], weight, tList2, wIndex+1);
        } else 
            return;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Gewicht des Artikels (ganzzahlig): ");
        int weight = sc.nextInt();
        sc.close();
        System.out.println();
        ArrayList<String> combinations = new ArrayList<>();
        getWeightCombinations(combinations, weight);

        System.out.printf("Es gibt %d mögliche Gewichts-%s:\n", combinations.size(), combinations.size() > 1 ? "Kombinationen" : "Kombination");
        for (String s : combinations)
            System.out.println(s);
    }
}
