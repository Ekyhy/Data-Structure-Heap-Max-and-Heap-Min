import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Membuat class barang
class Barang {
    int id;
    String nama;

    Barang(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", Nama: " + nama + "]";
    }
}

// Membuat class Heap Sistem
class HeapSystem {
    private List<Barang> minHeap = new ArrayList<>();
    private List<Barang> maxHeap = new ArrayList<>();

    // (1) Tambah data ke kedua heap sekaligus
    public void insertData(int id, String nama) {
        minHeap.add(new Barang(id, nama));
        bubbleUpMin(minHeap.size() - 1);

        maxHeap.add(new Barang(id, nama));
        bubbleUpMax(maxHeap.size() - 1);
    }

    // --- Logika Min Heap ---
    private void bubbleUpMin(int idx) {
        while (idx > 0 && minHeap.get(idx).id < minHeap.get((idx - 1) / 2).id) {
            Collections.swap(minHeap, idx, (idx - 1) / 2);
            idx = (idx - 1) / 2;
        }
    }

    public Barang removeMin() {
        if (minHeap.isEmpty()) return null;
        Barang root = minHeap.get(0);
        minHeap.set(0, minHeap.get(minHeap.size() - 1));
        minHeap.remove(minHeap.size() - 1);
        minHeapify(0);
        return root;
    }

    private void minHeapify(int i) {
        int smallest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < minHeap.size() && minHeap.get(l).id < minHeap.get(smallest).id) smallest = l;
        if (r < minHeap.size() && minHeap.get(r).id < minHeap.get(smallest).id) smallest = r;
        if (smallest != i) {
            Collections.swap(minHeap, i, smallest);
            minHeapify(smallest);
        }
    }

    // --- Logika Max Heap ---
    private void bubbleUpMax(int idx) {
        while (idx > 0 && maxHeap.get(idx).id > maxHeap.get((idx - 1) / 2).id) {
            Collections.swap(maxHeap, idx, (idx - 1) / 2);
            idx = (idx - 1) / 2;
        }
    }

    public Barang removeMax() {
        if (maxHeap.isEmpty()) return null;
        Barang root = maxHeap.get(0);
        maxHeap.set(0, maxHeap.get(maxHeap.size() - 1));
        maxHeap.remove(maxHeap.size() - 1);
        maxHeapify(0);
        return root;
    }

    private void maxHeapify(int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < maxHeap.size() && maxHeap.get(l).id > maxHeap.get(largest).id) largest = l;
        if (r < maxHeap.size() && maxHeap.get(r).id > maxHeap.get(largest).id) largest = r;
        if (largest != i) {
            Collections.swap(maxHeap, i, largest);
            maxHeapify(largest);
        }
    }

    // (2) Tampilkan Ascending (Pakai Min-Heap)
    public void displayAscending() {
        List<Barang> temp = new ArrayList<>(minHeap);
        System.out.println("\n--- Data Ascending (Min-Heap) ---");
        while (!minHeap.isEmpty()) {
            System.out.println(removeMin());
        }
        minHeap = temp; // Restore data
    }

    // (3) Tampilkan Descending (Pakai Max-Heap)
    public void displayDescending() {
        List<Barang> temp = new ArrayList<>(maxHeap);
        System.out.println("\n--- Data Descending (Max-Heap) ---");
        while (!maxHeap.isEmpty()) {
            System.out.println(removeMax());
        }
        maxHeap = temp; // Restore data
    }

    // (6) Load data dari CSV
    public void loadFromCSV(String filename) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        // Lewati header jika ada
        br.readLine(); 
        while ((line = br.readLine()) != null) {
            String[] data = line.split(","); // Sesuaikan jika CSV pakai ;
            if (data.length >= 2) {
                int id = Integer.parseInt(data[0].trim());
                String nama = data[1].trim();
                insertData(id, nama);
            }
        }
        br.close();
    }
}

public class MainApp {
    public static void main(String[] args) {
        HeapSystem sys = new HeapSystem();
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== SISTEM HEAP BARANG ===");
            System.out.println("1. Tambah Data (Manual)");
            System.out.println("2. Tampilkan Ascending (Min-Heap)");
            System.out.println("3. Tampilkan Descending (Max-Heap)");
            System.out.println("4. Hapus Root Min-Heap");
            System.out.println("5. Hapus Root Max-Heap");
            System.out.println("6. Load Data dari CSV");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan ID: ");
                    int id = sc.nextInt();
                    System.out.print("Masukkan Nama: ");
                    sc.nextLine(); // consume newline
                    String nama = sc.nextLine();
                    sys.insertData(id, nama);
                    break;
                case 2:
                    sys.displayAscending();
                    break;
                case 3:
                    sys.displayDescending();
                    break;
                case 4:
                    System.out.println("Dihapus: " + sys.removeMin());
                    break;
                case 5:
                    System.out.println("Dihapus: " + sys.removeMax());
                    break;
                case 6:
                    System.out.print("Masukkan nama file (ex: data_barang.csv): ");
                    String file = sc.next();
                    try {
                        sys.loadFromCSV(file);
                        System.out.println("Data berhasil dimuat.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Opsi tidak valid.");
            }
        } while (pilihan != 0);
        sc.close();
    }
}
