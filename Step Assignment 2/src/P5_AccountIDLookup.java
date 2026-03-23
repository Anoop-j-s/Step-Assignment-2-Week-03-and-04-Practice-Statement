import java.util.Arrays;

public class P5_AccountIDLookup {

    static void linearSearchFirst(String[] arr, String target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear first " + target + ": index " + i + " (" + comparisons + " comparisons)");
                return;
            }
        }
        System.out.println("Linear: " + target + " not found (" + comparisons + " comparisons)");
    }

    static void binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1, comparisons = 0, result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) { result = mid; break; }
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        int count = 0;
        for (String s : arr) if (s.equals(target)) count++;
        System.out.println("Binary " + target + ": index " + result + " (" + comparisons + " comparisons), count=" + count);
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: Account ID Lookup in Transaction Logs ===\n");

        String[] logs = {"accA", "accB", "accB", "accC"};
        System.out.println("Sorted logs: " + Arrays.toString(logs));

        linearSearchFirst(logs, "accB");
        binarySearch(logs, "accB");
    }
}