import java.util.*;

public class maxHeap {
    public static void insert(ArrayList<Integer> heap, int value){
        heap.add(value);
        int index = heap.size() - 1;

        while(index > 0 && heap.get((index-1)/2) < heap.get(index)){
            int part = heap.get(index);
            heap.set(index, heap.get((index-1)/2));
            heap.set((index-1)/2, part);


            index = (index - 1) / 2;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr =  new ArrayList<>();
        int [] values = {10, 7, 8, 5, 6, 3, 4, 1};

        for(int value : values) {
            insert(arr, value);
            System.out.print("Inserted " + value + " into the max-heap: ");
            for(int i = 0; i < arr.size(); i++){
                System.out.print(arr.get(i) + " ");
            }
            System.out.println();
        }
    }
}