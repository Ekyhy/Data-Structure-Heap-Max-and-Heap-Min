def insert(heap, value):
    # Menambahkan element baru pada akhir heap
    heap.append(value)

    # Mengambil index pada heap terakhir
    index = len(heap) - 1
    
    while index > 0 and heap[(index - 1)//2] > heap[index]:
        heap[index], heap[(index - 1)//2] = heap[(index - 1)//2], heap[index]

        index = (index - 1)//2

if __name__ == "__main__":
    arr = []
    values = [ 10, 2, 3, 4, 5, 1]
    n = len(values)
    for i in range(n):
        insert(arr, values[i])
        print(f"Inserted {values[i]} into the min-heap: ", end="")
        for j in range(len(arr)):
            print(arr[j], end=" ")
        print()

