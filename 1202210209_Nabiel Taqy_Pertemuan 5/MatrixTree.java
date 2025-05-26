import java.util.*;

class MatrixNode {
    Integer[][] matrix; // Menggunakan Integer agar bisa menyimpan null
    List<MatrixNode> children;
    int level;

    MatrixNode(Integer[][] matrix, int level) {
        this.matrix = matrix;
        this.level = level;
        this.children = new ArrayList<>();
    }

    void addChild(MatrixNode child) {
        children.add(child);
    }

    void printTree(String prefix, boolean isLast) {
        System.out.println(prefix + (isLast ? "└── " : "├── ") + "Level " + level);
        printMatrix(prefix + (isLast ? "    " : "│   "));
        for (int i = 0; i < children.size(); i++) {
            children.get(i).printTree(prefix + (isLast ? "    " : "│   "), i == children.size() - 1);
        }
    }

    void printMatrix(String prefix) {
        for (Integer[] row : matrix) {
            System.out.print(prefix);
            System.out.println(formatRow(row));
        }
    }

    private String formatRow(Integer[] row) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < row.length; i++) {
            if (row[i] == null) {
                sb.append("   "); // Tambahkan spasi kosong jika null
            } else {
                sb.append(String.format("%2d", row[i])); // Format angka agar rata
            }
            if (i < row.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

public class MatrixTree {
    public static void main(String[] args) {
        // Definisi matriks dengan gap kosong (null)
        Integer[][] rootMatrix = {
            {1, 5, 6},
            {3, null, 2},
            {4, 7, 8}
        };

        Integer[][] child1Matrix = {
            {1, null, 6},
            {3, 5, 2},
            {4, 7, 8}
        };

        Integer[][] child2Matrix = {
            {1, 5, 6},
            {null, 3, 2},
            {4, 7, 8}
        };

        Integer[][] leafMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, null}
        };

        Integer[][] child3Matrix = {
            {1, 5, 6},
            {3, 2, null},
            {4, 7, 8}
        };

        Integer[][] child4Matrix = {
            {1, 5, 6},
            {3, 7, 2},
            {4, null, 8}
        };

        Integer[][] subChild1Matrix = {
            {1, 5, 6},
            {3, 7, 2},
            {null, 4, 8}
        };

        Integer[][] subChild2Matrix = {
            {1, 5, 6},
            {3, 7, 2},
            {4, 8, null}
        };

        // Bangun struktur pohon
        MatrixNode root = new MatrixNode(rootMatrix, 0);
        MatrixNode child1 = new MatrixNode(child1Matrix, 1);
        MatrixNode child2 = new MatrixNode(child2Matrix, 1);
        MatrixNode leaf = new MatrixNode(leafMatrix, 2);
        MatrixNode child3 = new MatrixNode(child3Matrix, 1);
        MatrixNode child4 = new MatrixNode(child4Matrix, 1);
        MatrixNode subChild1 = new MatrixNode(subChild1Matrix, 2);
        MatrixNode subChild2 = new MatrixNode(subChild2Matrix, 2);

        // Hubungkan node
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        root.addChild(child4);

        child2.addChild(leaf);
        child4.addChild(subChild1);
        child4.addChild(subChild2);

        // Cetak pohon dengan garis
        root.printTree("", true);
    }
}
