import java.util.ArrayList;
import java.util.List;

public class P1_TransactionFeeSorting {

    static class Transaction {
        String id;
        double fee;
        String timestamp;

        Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }
            passes++;
            if (!swapped) break;
        }
        System.out.println("BubbleSort (fees): " + list + " // " + passes + " passes, " + swaps + " swaps");
    }

    static void insertionSort(List<Transaction> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;
            while (j >= 0 && (list.get(j).fee > key.fee ||
                    (list.get(j).fee == key.fee && list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        System.out.println("InsertionSort (fee+ts): " + list);
    }

    static void flagOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50) outliers.add(t);
        }
        System.out.println("High-fee outliers: " + (outliers.isEmpty() ? "none" : outliers));
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Transaction Fee Sorting for Audit Compliance ===\n");

        List<Transaction> bubbleList = new ArrayList<>();
        bubbleList.add(new Transaction("id1", 10.5, "10:00"));
        bubbleList.add(new Transaction("id2", 25.0, "09:30"));
        bubbleList.add(new Transaction("id3", 5.0, "10:15"));

        List<Transaction> insertionList = new ArrayList<>();
        insertionList.add(new Transaction("id1", 10.5, "10:00"));
        insertionList.add(new Transaction("id2", 25.0, "09:30"));
        insertionList.add(new Transaction("id3", 5.0, "10:15"));

        bubbleSort(bubbleList);
        insertionSort(insertionList);
        flagOutliers(bubbleList);
    }
}