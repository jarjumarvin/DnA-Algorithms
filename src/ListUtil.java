import java.util.ArrayList;
import java.util.Collections;

class ListUtil {
    static int[] getOrderedList(int n) {
        int[] r = new int[n];
        for(int i = 0; i < n; i++) {
            r[i] = i;
        }
        return r;
    }

    static int[] getRandomList() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arr.add(i);
        }
        Collections.shuffle(arr);
        int[] list = new int[50];
        for(int i = 0; i < 50; i++) {
            list[i] = arr.get(i);
        }
        return list;
    }
}
