import java.util.*;

public class PalindromeProducts {
    private final int min;
    private final int max;
    Map<Long, List<Integer>> mapOfPalindromesAndDivisors = new HashMap<>();

    public PalindromeProducts(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static void main(String[] args) {
        PalindromeProducts palindrome = new PalindromeProducts(1, 9);
        palindrome.getAllPalindromesWithDivisors();
    }

    public boolean checkIfNumberIsPalindrome(int number) {
        String numberToString = "" + number;
        StringBuilder numberToStringReversed = new StringBuilder();
        for (int i = numberToString.length() - 1; i >= 0; i--) {
            numberToStringReversed.append(numberToString.charAt(i));
        }
        return numberToString.contentEquals(numberToStringReversed);
    }
    public void getAllPalindromesWithDivisors(){
        List<Integer> allPalindromes = findAllPalindromes();
        for (int i = 0; i < allPalindromes.size(); i++) {
            printDivisors(allPalindromes.get(i));
        }
        TreeMap<Long, List<Integer>> treeMap = new TreeMap<>(mapOfPalindromesAndDivisors);
        System.out.println(treeMap.firstEntry());
        System.out.println(treeMap.lastEntry());
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

    public void printDivisors(long number) {
        List<Integer> listOfDivisors = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0 && min <= i && i <= max) {
                if (verifyDivisor(i, number)) {
                    listOfDivisors.add(i);
                }
            }
        }
        if (!listOfDivisors.isEmpty()) {
            mapOfPalindromesAndDivisors.put(number, listOfDivisors);
        }
    }

    public boolean verifyDivisor(int divisor, long number) {
        return (number / divisor) >= min && (number / divisor) <= max;
    }
}
