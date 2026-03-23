import java.util.Arrays;

public class P3_TradeVolumeAnalysis {

    static class Trade {
        String id;
        int volume;

        Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        public String toString() {
            return id + ":" + volume;
        }
    }

    static Trade[] mergeSort(Trade[] arr) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        Trade[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        Trade[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    static Trade[] merge(Trade[] left, Trade[] right) {
        Trade[] result = new Trade[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            result[k++] = left[i].volume <= right[j].volume ? left[i++] : right[j++];
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }

    static void quickSortDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }

    static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume, i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) {
                i++;
                Trade temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    static int totalVolume(Trade[] arr) {
        int total = 0;
        for (Trade t : arr) total += t.volume;
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 3: Historical Trade Volume Analysis ===\n");

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        Trade[] merged = mergeSort(trades.clone());
        System.out.println("MergeSort: " + Arrays.toString(merged));

        Trade[] quick = trades.clone();
        quickSortDesc(quick, 0, quick.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quick));

        Trade[] morning = {new Trade("t1", 100), new Trade("t2", 300)};
        Trade[] afternoon = {new Trade("t3", 500)};
        Trade[] combined = new Trade[morning.length + afternoon.length];
        System.arraycopy(morning, 0, combined, 0, morning.length);
        System.arraycopy(afternoon, 0, combined, morning.length, afternoon.length);
        System.out.println("Merged morning+afternoon total: " + totalVolume(combined));
    }
}