public class Search {

    int[] getOrderedList(int n) {
        int[] r = new int[n];
        for(int i = 0; i < n; i++) {
            r[i] = i;
        }
        return r;
    }
    int linearSearch(int[] A, int k) {
        for(int i = 0; i < A.length; i++) {
            if(A[i] == k) return i;
        }
        return -1;
    }

    int binarySearch(int[] A, int k) {
        int l = 0;
        int r = A.length - 1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(A[m] == k) return m;
            else if(A[m] > k) r = m - 1;
            else l = m + 1;
        }
        return -1;
    }

    int interpolationSearch(int[] A, int k) {
        int l = 0;
        int r = A.length - 1;

        while(A[r] != A[l] && (k >= A[l]) && (k <= A[r])) {
            int m = l + ((k - A[l])) * (r - l) / (A[r] - A[l]);
            if(A[m] == k) return m;
            else if(A[m] < k) r = m + 1;
            else l = m - 1;
        }
        if(k == A[l]) {
            return l;
        } else {
            return -1;
        }
    }
}
