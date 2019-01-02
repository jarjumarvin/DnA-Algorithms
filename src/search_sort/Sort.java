package search_sort;

import java.util.ArrayList;
import java.util.Collections;

public class Sort {

    public int[] getRandomList() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arr.add(i);
        }
        Collections.shuffle(arr);
        int[] list = new int[20];
        for(int i = 0; i < 20; i++) {
            list[i] = arr.get(i);
        }
        return list;
    }

    void swap(int[] A, int i, int j) {
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

    public void mergeSort(int[] A) {
        //TODO
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
