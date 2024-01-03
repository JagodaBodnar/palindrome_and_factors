import java.util.*;

public class PalindromeProducts {
    private final int min;
    private final int max;
    Map<Long, List<List<Integer>>> mapOfPalindromesAndDivisors = new HashMap<>();

    public PalindromeProducts(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static void main(String[] args) {
        PalindromeProducts palindrome = new PalindromeProducts(1,9);
        palindrome.getPalindromeProductsWithFactors();
    }

    public boolean checkIfNumberIsPalindrome(int number) {
        String numberToString = "" + number;
        StringBuilder numberToStringReversed = new StringBuilder();
        for (int i = numberToString.length() - 1; i >= 0; i--) {
            numberToStringReversed.append(numberToString.charAt(i));
        }
        return numberToString.contentEquals(numberToStringReversed);
    }

    public SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors() {
        List<Integer> allPalindromes = findAllPalindromes();
        for (int i = 0; i < allPalindromes.size(); i++) {
            getAllFactors(allPalindromes.get(i));
        }
        SortedMap<Long, List<List<Integer>>> treeMap = new TreeMap<>(mapOfPalindromesAndDivisors);
        SortedMap<Long, List<List<Integer>>> finalMap = new TreeMap<>();
        finalMap.put(treeMap.firstKey(), treeMap.get(treeMap.firstKey()));
        finalMap.put(treeMap.lastKey(), treeMap.get(treeMap.lastKey()));
        System.out.println(finalMap);
        return treeMap;
    }

    public List<List<Integer>> eliminateDuplicatedFactors(List<List<Integer>> listOfFactors){
       return listOfFactors.stream().distinct().toList();
    }

    public List<Integer> findAllPalindromes() {
        int minPal = min * min;
        int maxPal = max * max;
        List<Integer> palindromeList = new ArrayList<>();
        for (int i = minPal; i <= maxPal; i++) {
            if (checkIfNumberIsPalindrome(i)) {
                palindromeList.add(i);
            }
        }
        return palindromeList;
    }

    public void getAllFactors(long number) {
        List<Integer> listOfDivisors = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0 && min <= i && i <= max) {
                if (verifyDivisor(i, number)) {
                    listOfDivisors.add(i);
                }
            }
        }
        if (!listOfDivisors.isEmpty()) {
            createMapOfPalindromeAndItsFactors(createListOfFactors(listOfDivisors, number), number);
        }
    }

    public List<List<Integer>> createListOfFactors(List<Integer> listOfDivisors, long number) {
        List<List<Integer>> listOfFactors = new ArrayList<>();
        for (int i = 0; i < listOfDivisors.size(); i++) {
            List<Integer> sublistOfFactors = new ArrayList<>();
            sublistOfFactors.add((int) (number / listOfDivisors.get(i)));
            sublistOfFactors.add(listOfDivisors.get(i));
            Collections.sort(sublistOfFactors);
            listOfFactors.add(sublistOfFactors);
        }
        return eliminateDuplicatedFactors(listOfFactors);
    }


    public boolean verifyDivisor(int divisor, long number) {
        return (number / divisor) >= min && (number / divisor) <= max;
    }

    public void createMapOfPalindromeAndItsFactors(List<List<Integer>> listOfFactors, long number) {
       mapOfPalindromesAndDivisors.put(number,listOfFactors);
    }
}
