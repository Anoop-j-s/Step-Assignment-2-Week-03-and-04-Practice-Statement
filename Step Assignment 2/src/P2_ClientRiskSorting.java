public class P2_ClientRiskSorting {

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        public String toString() {
            return name + ":" + riskScore;
        }
    }

    static void bubbleSortAsc(Client[] arr) {
        int n = arr.length, swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.print("Bubble (asc): [");
        for (int i = 0; i < n; i++) System.out.print(arr[i] + (i < n - 1 ? ", " : ""));
        System.out.println("] // Swaps: " + swaps);
    }

    static void insertionSortDesc(Client[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;
            while (j >= 0 && (arr[j].riskScore < key.riskScore ||
                    (arr[j].riskScore == key.riskScore && arr[j].accountBalance < key.accountBalance))) {
                arr[j + 1] = arr[j]; j--;
            }
            arr[j + 1] = key;
        }
        System.out.print("Insertion (desc): [");
        for (int i = 0; i < n; i++) System.out.print(arr[i] + (i < n - 1 ? ", " : ""));
        System.out.println("]");
    }

    static void topRisks(Client[] arr, int top) {
        System.out.print("Top " + top + " risks: ");
        for (int i = 0; i < top && i < arr.length; i++)
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ")" + (i < top - 1 ? ", " : ""));
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2: Client Risk Score Ranking ===\n");

        Client[] bubbleArr = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 3000),
                new Client("clientB", 50, 4000)
        };

        Client[] insertionArr = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 3000),
                new Client("clientB", 50, 4000)
        };

        bubbleSortAsc(bubbleArr);
        insertionSortDesc(insertionArr);
        topRisks(insertionArr, 3);
    }
}