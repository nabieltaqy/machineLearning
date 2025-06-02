import java.io.*;
import java.util.Scanner;

class Node implements Serializable {
    String data;
    Node yes;
    Node no;

    Node(String data) {
        this.data = data;
    }

    boolean isLeaf() {
        return yes == null && no == null;
    }
}

public class YesNoGame {
    private static final String FILE_NAME = "knowledge.ser";
    private static Scanner scanner = new Scanner(System.in);
    private static Node root;

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("   SELAMAT DATANG DI YES/NO GAME 🎮");
        System.out.println("=======================================\n");

        root = loadTree();
        if (root == null) {
            root = new Node("kucing"); // tebakan awal
        }

        do {
            System.out.println("\n--- Mulai permainan ---");
            play(root);
            System.out.print("\nMain lagi? (yes/no): ");
        } while (getYesNo());

        System.out.print("\nIngin melihat pohon pengetahuan? (yes/no): ");
        if (getYesNo()) {
            System.out.println("\n--- Struktur Pohon Pengetahuan ---");
            printTree(root, "", "ROOT");
        }

        saveTree(root);
        System.out.println("\nTerima kasih sudah bermain!");
    }

    private static void play(Node current) {
        if (current.isLeaf()) {
            System.out.print("Apakah hewanmu adalah " + current.data + "? (yes/no): ");
            if (getYesNo()) {
                System.out.println("Yay! Aku berhasil menebaknya!");
            } else {
                System.out.print("Apa hewan yang kamu pikirkan? ");
                String correctAnimal = scanner.nextLine();

                System.out.print("Berikan pertanyaan yang membedakan " +
                        correctAnimal + " dari " + current.data + ": ");
                String newQuestion = scanner.nextLine();

                System.out.print("Untuk " + correctAnimal + ", apa jawabannya? (yes/no): ");
                boolean answerIsYes = getYesNo();

                Node oldAnswer = new Node(current.data);
                Node newAnswer = new Node(correctAnimal);

                current.data = newQuestion;
                current.yes = answerIsYes ? newAnswer : oldAnswer;
                current.no = answerIsYes ? oldAnswer : newAnswer;

                System.out.println("Terima kasih! Aku belajar sesuatu yang baru!");
            }
        } else {
            System.out.print(current.data + " (yes/no): ");
            if (getYesNo()) {
                play(current.yes);
            } else {
                play(current.no);
            }
        }
    }

    private static boolean getYesNo() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) return true;
            if (input.equals("no") || input.equals("n")) return false;
            System.out.print("Tolong jawab dengan 'yes' atau 'no': ");
        }
    }

    private static void saveTree(Node root) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(root);
            System.out.println("\n(Pohon pengetahuan disimpan ke " + FILE_NAME + ")");
        } catch (IOException e) {
            System.err.println("Gagal menyimpan: " + e.getMessage());
        }
    }

    private static Node loadTree() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Node) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Gagal memuat: " + e.getMessage());
            return null;
        }
    }

    private static void printTree(Node node, String indent, String branch) {
        if (node.isLeaf()) {
            System.out.println(indent + branch + ": " + node.data);
        } else {
            System.out.println(indent + branch + ": " + node.data);
            printTree(node.yes, indent + "    ", "yes");
            printTree(node.no, indent + "    ", "no");
        }
    }
}
