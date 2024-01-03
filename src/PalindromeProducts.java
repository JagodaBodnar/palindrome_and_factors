import java.util.*;

public class PalindromeProducts {
    private int min;
    private int max;
    public static void main(String[] args) {
        PalindromeProducts palindromeProducts = new PalindromeProducts();
        System.out.println(palindromeProducts.getPalindromeProductsWithFactors(9901,9999));
    }
    public boolean checkIfNumberIsPalindrome(long number) {
        String numberToString = "" + number;
        StringBuilder numberToStringReversed = new StringBuilder(numberToString).reverse();
        return numberToString.contentEquals(numberToStringReversed);
    }
    public SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int minFactor, int maxFactor) {
        this.min = minFactor;
        this.max = maxFactor;
        TreeMap<Long, List<List<Integer>>> palindrome = new TreeMap<>();
        for(long i = minFactor; i <= maxFactor; i++){
            for(long j=i; j <= maxFactor; j++){
                if(checkIfNumberIsPalindrome(j*i)){
                    palindrome.put(i*j, getAllDistinctDividers(j*i));
                }
            }
        }
        SortedMap<Long, List<List<Integer>>> finalMap = new TreeMap<>();
        finalMap.put(palindrome.firstKey(), palindrome.get(palindrome.firstKey()));
        finalMap.put(palindrome.lastKey(), palindrome.get(palindrome.lastKey()));
        return finalMap;
    }
    public List<List<Integer>> getAllDistinctDividers(long number){
        List<Integer> listOfDivisors = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0 && min <= i && i <= max) {
                if (verifyDivisor(i, number)) {
                    listOfDivisors.add(i);
                }
            }
        }
        if (!listOfDivisors.isEmpty()) {
            List<List<Integer>> listOfFactors = new ArrayList<>();
            for (int i = 0; i < listOfDivisors.size(); i++) {
                List<Integer> sublistOfFactors = new ArrayList<>();
                sublistOfFactors.add((int) (number / listOfDivisors.get(i)));
                sublistOfFactors.add(listOfDivisors.get(i));
                Collections.sort(sublistOfFactors);
                listOfFactors.add(sublistOfFactors);
            }
            return listOfFactors.stream().distinct().toList();
        }
        return null;
    }
    public boolean verifyDivisor(int divisor, long number) {
        return (number / divisor) >= min && (number / divisor) <= max;
    }
}
