import java.util.Arrays;

public class P4_PortfolioReturnSorting {

    static class Asset {
        String name;
        double returnRate;
        double volatility;

        Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        public String toString() {
            return name + ":" + returnRate + "%";
        }
    }

    static Asset[] mergeSort(Asset[] arr) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        Asset[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        Asset[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    static Asset[] merge(Asset[] left, Asset[] right) {
        Asset[] result = new Asset[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            result[k++] = left[i].returnRate <= right[j].returnRate ? left[i++] : right[j++];
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }

    static void quickSortDesc(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;
        if (arr[low].returnRate < arr[mid].returnRate) { Asset t = arr[low]; arr[low] = arr[mid]; arr[mid] = t; }
        if (arr[low].returnRate < arr[high].returnRate) { Asset t = arr[low]; arr[low] = arr[high]; arr[high] = t; }
        if (arr[mid].returnRate < arr[high].returnRate) { Asset t = arr[mid]; arr[mid] = arr[high]; arr[high] = t; }
        double pivot = arr[mid].returnRate;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot ||
                    (arr[j].returnRate == pivot && arr[j].volatility < arr[high].volatility)) {
                i++;
                Asset temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        Asset temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 4: Portfolio Return Sorting ===\n");

        Asset[] assets = {
                new Asset("AAPL", 12.0, 0.15),
                new Asset("TSLA", 8.0, 0.30),
                new Asset("GOOG", 15.0, 0.12)
        };

        Asset[] sorted = mergeSort(assets.clone());
        System.out.println("Merge: " + Arrays.toString(sorted));

        Asset[] quick = assets.clone();
        quickSortDesc(quick, 0, quick.length - 1);
        System.out.println("Quick (desc): " + Arrays.toString(quick));
    }
}