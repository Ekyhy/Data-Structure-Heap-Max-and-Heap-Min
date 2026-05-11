# 〽️HEAP STRUCTURE 
<p><b>Struktur data heap</b> adalah struktur data berbasis binary tree yang lengkap atau disebut dengan complete binary tree. Pada heap setiap node parents akan berelasi secara langsung dengan node child. Seperti halnya pada max-heap: setiap node parent memiliki nilai yang lebih besar ketimbang child node, begitu pula sebaliknya pada min-heap: setiap node parent memiliki nilai kurang dari node child. Struktur data heap umumnya digunakan untuk membangun sistem antrian dengan skala prioritas serta sebagai landasan pada Dijkstra's algorithm.
  
<h3>📝IMPLEMENTASI INTERNAL DARI MIN-HEAP</h3>

<p>Sebuah heap akan secara efisien Ketika direpresentasikan dengan menggunakan array. Dengan ketentuan sebagai berikut: </p>

<ul>
<li>Sebuah node dapat disimpan pada index i</li>
<li>Child kiri dengan index 2*1+1</li>
<li>Child kanan dengan index 2*i+2</li>
<li>Parent dari node pada index akan dapat ditemukan dengan [(i-1)/2]</li>
</ul>

<p>Pada projek ini fungsi heap disimpan pada file project:</p>
maxheap : maxHeap.java

```java
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
```
