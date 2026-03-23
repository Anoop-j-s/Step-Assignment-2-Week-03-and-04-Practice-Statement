import java.util.Arrays;

public class P6_RiskThresholdLookup {

    static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        for (int val : arr) {
            comparisons++;
            if (val == target) {
                System.out.println("Linear: threshold=" + target + " → found (" + comparisons + " comps)");
                return;
            }
        }
        System.out.println("Linear: threshold=" + target + " → not found (" + comparisons + " comps)");
    }

    static void floorCeilingBinary(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1, ceiling = -1, comparisons = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (arr[mid] == target) { floor = arr[mid]; ceiling = arr[mid]; break; }
            else if (arr[mid] < target) { floor = arr[mid]; low = mid + 1; }
            else { ceiling = arr[mid]; high = mid - 1; }
        }
        System.out.println("Binary floor(" + target + "): " + floor + ", ceiling: " + ceiling + " (" + comparisons + " comps)");
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 6: Risk Threshold Binary Lookup ===\n");

        int[] risks = {10, 25, 50, 100};
        System.out.println("Sorted risks: " + Arrays.toString(risks));

        linearSearch(risks, 30);
        floorCeilingBinary(risks, 30);
    }
}