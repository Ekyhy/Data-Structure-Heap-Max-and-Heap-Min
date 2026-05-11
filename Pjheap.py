import pandas as pd

class Mahasiswa:
    def __init__(self, id, nama):
        self.id = id
        self.nama = nama
    def __repr__(self):
        return f"[ID: {self.id}, Nama: {self.nama}]"

class HeapSystem:
    def __init__(self):
        self.min_heap = []
        self.max_heap = []

    def insert_data(self, id, nama):
        new_node = Mahasiswa(id, nama)
        self._insert_min(new_node)
        self._insert_max(Mahasiswa(id, nama))

    def _insert_min(self, node):
        self.min_heap.append(node)
        idx = len(self.min_heap) - 1
        while idx > 0 and self.min_heap[(idx-1)//2].id > self.min_heap[idx].id:
            self.min_heap[idx], self.min_heap[(idx-1)//2] = self.min_heap[(idx-1)//2], self.min_heap[idx]
            idx = (idx-1)//2

    def _insert_max(self, node):
        self.max_heap.append(node)
        idx = len(self.max_heap) - 1
        while idx > 0 and self.max_heap[(idx-1)//2].id < self.max_heap[idx].id:
            self.max_heap[idx], self.max_heap[(idx-1)//2] = self.max_heap[(idx-1)//2], self.max_heap[idx]
            idx = (idx-1)//2

    def remove_min(self):
        if not self.min_heap: return None
        root = self.min_heap[0]
        self.min_heap[0] = self.min_heap[-1]
        self.min_heap.pop()
        self._min_heapify(0)
        return root

    def _min_heapify(self, i):
        smallest = i
        l, r = 2*i + 1, 2*i + 2
        if l < len(self.min_heap) and self.min_heap[l].id < self.min_heap[smallest].id: smallest = l
        if r < len(self.min_heap) and self.min_heap[r].id < self.min_heap[smallest].id: smallest = r
        if smallest != i:
            self.min_heap[i], self.min_heap[smallest] = self.min_heap[smallest], self.min_heap[i]
            self._min_heapify(smallest)

    def remove_max(self):
        if not self.max_heap: return None
        root = self.max_heap[0]
        self.max_heap[0] = self.max_heap[-1]
        self.max_heap.pop()
        self._max_heapify(0)
        return root

    def _max_heapify(self, i):
        largest = i
        l, r = 2*i + 1, 2*i + 2
        if l < len(self.max_heap) and self.max_heap[l].id > self.max_heap[largest].id: largest = l
        if r < len(self.max_heap) and self.max_heap[r].id > self.max_heap[largest].id: largest = r
        if largest != i:
            self.max_heap[i], self.max_heap[largest] = self.max_heap[largest], self.max_heap[i]
            self._max_heapify(largest)

    def display_ascending(self):
        temp_heap = list(self.min_heap)
        print("\n--- Data Ascending (Min-Heap) ---")
        while self.min_heap:
            print(self.remove_min())
        self.min_heap = temp_heap 

    def display_descending(self):
        temp_heap = list(self.max_heap)
        print("\n--- Data Descending (Max-Heap) ---")
        while self.max_heap:
            print(self.remove_max())
        self.max_heap = temp_heap

    # PERBAIKAN: Fungsi ini sekarang mendukung CSV dan Excel secara fleksibel
    def load_data(self, path):
        if path.endswith('.csv'):
            df = pd.read_csv(path)
        else:
            df = pd.read_excel(path)
            
        for _, row in df.iterrows():
            self.insert_data(int(row['id']), str(row['nama']))

def main():
    sys = HeapSystem()
    
    while True:
        print("\n--- MENU STRUKTUR DATA HEAP ---")
        print("1. Tambah Data (Manual)")
        print("2. Tampilkan Ascending (Min-Heap)")
        print("3. Tampilkan Descending (Max-Heap)")
        print("4. Hapus Root Min-Heap")
        print("5. Hapus Root Max-Heap")
        print("6. Load Data (CSV/Excel)")
        print("0. Keluar")
        
        pilihan = input("Pilih menu: ")
        
        if pilihan == "1":
            try:
                id_data = int(input("Masukkan ID: "))
                nama = input("Masukkan Nama: ")
                sys.insert_data(id_data, nama)
                print("Data berhasil ditambahkan!")
            except ValueError:
                print("ID harus berupa angka!")
            
        elif pilihan == "2":
            sys.display_ascending()
            
        elif pilihan == "3":
            sys.display_descending()
            
        elif pilihan == "4":
            removed = sys.remove_min()
            print(f"Dihapus: {removed}")
            
        elif pilihan == "5":
            removed = sys.remove_max()
            print(f"Dihapus: {removed}")
            
        elif pilihan == "6":
            # Perbaikan input path
            filename = input("Masukkan nama file (contoh: data_barang.csv): ")
            try:
                sys.load_data(filename)
                print(f"Data dari {filename} berhasil dimuat!")
            except Exception as e:
                print(f"Gagal memuat file: {e}")
                
        elif pilihan == "0":
            print("Keluar dari program...")
            break
        else:
            print("Pilihan tidak valid!")

if __name__ == "__main__":
    main()