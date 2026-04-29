import java.util.*;
public class minHeap {
    public static void insert(ArrayList<Integer> heap, int value){
        // Menambahkan elemen baru di akhir heap
        heap.add(value);

        // Mengambil index dari element terakhir
        int index = heap.size() - 1;

        // Bandingakan elemen baru dengan elemen induk dan tukar jika diperlukan
        while (index > 0 && heap.get((index - 1) /2) > heap.get(index)) {
            int part = heap.get(index);
            heap.set(index, heap.get((index - 1)/2));
            heap.set((index - 1) / 2, part);

            // Melangkah keatas menjunu ke parent element
            index = (index - 1)/2;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new  ArrayList<>();
        int [] value =  {10, 2, 3, 4, 5, 1};
        int n = value.length;
        for(int i = 0; i < n; i++){
            insert(arr, value[i]);
            System.out.print("Inserted " + value[i] + " into the min-heap: ");
            for (int j = 0; j < arr.size(); j++) {
                System.out.print(arr.get(j) + " ");
            }           
            System.out.println("");
        }
    }
}
