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

<p>Pada projek ini fungsi heap disimpan pada file project:
</p>

<h4>Fungsi Max Heap pada Java dan Python</h4>

 ☕PROGRAM JAVA
```java
========================= maxHeap.java ============================
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
========================= MainApp.java ============================
    private void bubbleUpMax(int idx) {
        while (idx > 0 && maxHeap.get(idx).id > maxHeap.get((idx - 1) / 2).id) {
            Collections.swap(maxHeap, idx, (idx - 1) / 2);
            idx = (idx - 1) / 2;
        }
    }
```

<H4>🐍PROGRAM PYTHON</H4>

```pyhon
=============================== maxHeap.py ===================================
def insert(heap, value):
    heap.append(value)

    index = len(heap)-1

    while index > 0 and heap[(index - 1) // 2] < heap[index]:
        heap[index], heap[(index - 1) // 2] = heap[(index - 1)//2], heap[index]

        index = (index - 1) // 2

=============================== Pjheap.py ====================================
 def _max_heapify(self, i):
        largest = i
        l, r = 2*i + 1, 2*i + 2
        if l < len(self.max_heap) and self.max_heap[l].id > self.max_heap[largest].id: largest = l
        if r < len(self.max_heap) and self.max_heap[r].id > self.max_heap[largest].id: largest = r
        if largest != i:
            self.max_heap[i], self.max_heap[largest] = self.max_heap[largest], self.max_heap[i]
            self._max_heapify(largest)
```


```java
========================= minHeap.java ============================
  public static void insert(ArrayList<Integer> heap, int value){
        heap.add(value);
        int index = heap.size() - 1;
        while (index > 0 && heap.get((index - 1) /2) > heap.get(index)) {
            int part = heap.get(index);
            heap.set(index, heap.get((index - 1)/2));
            heap.set((index - 1) / 2, part);
            index = (index - 1)/2;
        }
    }
========================= MainApp.java ==============================
    private void bubbleUpMin(int idx) {
        while (idx > 0 && minHeap.get(idx).id < minHeap.get((idx - 1) / 2).id) {
            Collections.swap(minHeap, idx, (idx - 1) / 2);
            idx = (idx - 1) / 2;
        }
    }
```
