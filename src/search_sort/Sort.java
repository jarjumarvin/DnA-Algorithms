package search_sort;

public class Sort {

    private void swap(int[] A, int i, int j) {
        int t = A[j];
        A[j] = A[i];
        A[i] = t;
    }

    public void bubbleSort(int[] A) {
        for(int i = 0; i < A.length - 1; i++) {
            for(int j = 0; j < A.length - 1; j++) {
                if(A[j] > A[j + 1]) {
                    swap(A, j, j + 1);
                }
            }
        }
    }

    public void selectionSort(int[] A) {
        for(int i = 0; i < A.length - 1; i++) {
            int minPos = i;
            for (int j = i+1; j < A.length; j++) {
                if (A[j] < A[minPos]) minPos = j;
            }
            if(minPos != i) {
                swap(A, i, minPos);
            }
        }
    }

    public void insertionSort(int[] A) {
        for(int i = 1; i < A.length; i++) {
            int k = A[i];
            int j = i - 1;
            while(j >= 0 && A[j] > k) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = k;
        }
    }

    public void heapSort(int[] A) {
        int n = A.length - 1;
        for(int i = n / 2; i >= 0; i--) {
            heapify(A, i, n);
        }
        for(int i = n; i > 0; i--) {
            swap(A, 0, i);
            n--;
            heapify(A, 0, n);
        }
    }

    public void heapify(int[] A, int i, int n) {
        while(2*i+1 <= n) {
            int j = 2*i+1;
            if(j + 1 <= n && A[j] < A[j + 1]) j++;

            if(A[i] >= A[j]) break;
            swap(A, i, j);
            i = j;
        }
    }

    public void mergeSort(int[] a) {
        int n = a.length;
        if(n < 2) return; // empty array is sorted
        int m = n / 2; // midpoint

        // create subarrays
        int[] left = new int[m];
        int[] right = new int[n - m];
        // copy into subarrays
        for (int i = 0; i < m; i++) { left[i] = a[i]; }
        for (int i = 0; i < n - m; i++) { right[i] = a[m + i]; }

        mergeSort(left); // recur for left
        mergeSort(right); // recur for right
        merge(a, left, right, m, n - m); // merge
    }



    void merge(int[] a, int[] left, int[] right, int l, int r) {
        int i = 0; // index for left array
        int j = 0; // index for right array
        int k = 0; // current index to insert

        while(i < l && j < r) { // traverse both arrays in parallel and insert elements in order
            if(left[i] < right[j]) {
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
            }
        } // either both arrays have been copied, or one was longer than the other

        // if left array was longer, keep inserting
        while(i < l) { a[k++] = left[i++]; }
        // if right array was longer, keep inserting
        while(j < r) { a[k++] = right[j++]; }
    }


    public void quickSort(int[] A) {
        quickSort(A, 0, A.length - 1);
    }

    void quickSort(int[] A, int l, int r) {
        if(l < r) {
            int k = partition(A, l, r);
            quickSort(A, l, k - 1);
            quickSort(A, k + 1, r);
        }
    }

    int partition(int[] A, int l, int r) {
        int i = l;
        int j = r - 1;
        int p = A[r];
        do {
            while (i < r && A[i] < p) i++;
            while (j > l && A[j] > p) j--;
            if(i < j) swap(A, i, j);
        } while(i < j);
        swap(A, i, r);
        return i;
    }
}
