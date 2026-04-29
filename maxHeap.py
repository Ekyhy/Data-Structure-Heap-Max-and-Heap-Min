def insert(heap, value):
    heap.append(value)

    index = len(heap)-1

    while index > 0 and heap[(index - 1) // 2] < heap[index]:
        heap[index], heap[(index - 1) // 2] = heap[(index - 1)//2], heap[index]

        index = (index - 1) // 2

if __name__ == "__main__":
    arr = [];
    values = [10, 7, 11, 5, 4, 13];

    for value in values :
        insert(arr, value)
        print(f"Inserted {value} into the max-heap: ", end="")
        for v in arr :
            print(v, end=" ")
        print()