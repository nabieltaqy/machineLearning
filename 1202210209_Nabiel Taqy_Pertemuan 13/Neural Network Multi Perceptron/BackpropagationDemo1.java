public class BackpropagationDemo1 {
    // Konfigurasi jaringan
    static int nInput = 4, nHidden = 2, nOutput = 2;

    static double[][] input = {
        {0, 0, 1, 0},
        {0, 0, 0, 1},
        {1, 0, 0, 0},
        {0, 1, 0, 0}
    };

    static int[][] target = {
        {1, 0},
        {0, 1},
        {1, 0},
        {0, 1}
    };

    static double[][] y1 = new double[input.length][nHidden];   // output hidden
    static double[][] y2 = new double[input.length][nOutput];   // output final

    static double[][] delta1 = new double[input.length][nHidden];
    static double[][] delta2 = new double[input.length][nOutput];

    static double[][] w1 = new double[nInput][nHidden];  // input → hidden
    static double[][] w2 = new double[nHidden][nOutput]; // hidden → output

    static double[] b1 = new double[nHidden];
    static double[] b2 = new double[nOutput];

    static double learningRate = 0.4;
    static int count = 0;
    static int maxCount = 1000;
    static boolean loop = true;

    public static void main(String[] args) {
        generateWR(); // Generate bobot dan bias awal

        while (loop) {
            for (int i = 0; i < input.length; i++) {
                calculateY(i);
                calculateDelta(i);
                calculateNewWeights(i);
                calculateNewBias();
            }

            count++;

            if (count >= maxCount) {
                loop = false;
            }

            System.out.println("Output: " + y2[0][0] + ", " + y2[0][1]);
            System.out.println("-------------------------------------");
        }
    }

    // Inisialisasi bobot dan bias dengan nilai acak
    private static void generateWR() {
        for (int i = 0; i < nInput; i++) {
            for (int j = 0; j < nHidden; j++) {
                w1[i][j] = Math.abs(Math.random() - 0.5);
            }
        }

        for (int i = 0; i < nHidden; i++) {
            for (int j = 0; j < nOutput; j++) {
                w2[i][j] = Math.abs(Math.random() - 0.5);
            }
        }

        for (int i = 0; i < nHidden; i++) {
            b1[i] = Math.abs(Math.random() - 0.5);
        }

        for (int i = 0; i < nOutput; i++) {
            b2[i] = Math.abs(Math.random() - 0.5);
        }
    }

    // Menghitung output layer tersembunyi dan output akhir
    private static void calculateY(int i) {
        for (int j = 0; j < nHidden; j++) {
            y1[i][j] = 0;
            for (int k = 0; k < nInput; k++) {
                y1[i][j] += input[i][k] * w1[k][j];
            }
            y1[i][j] = sigmoid(y1[i][j] + b1[j]);
        }

        for (int j = 0; j < nOutput; j++) {
            y2[i][j] = 0;
            for (int k = 0; k < nHidden; k++) {
                y2[i][j] += y1[i][k] * w2[k][j];
            }
            y2[i][j] = sigmoid(y2[i][j] + b2[j]);
        }
    }

    // Menghitung error dari output dan hidden layer
    private static void calculateDelta(int i) {
        for (int j = 0; j < nOutput; j++) {
            delta2[i][j] = (target[i][j] - y2[i][j]) * y2[i][j] * (1 - y2[i][j]);
        }

        for (int j = 0; j < nHidden; j++) {
            double sum = 0;
            for (int k = 0; k < nOutput; k++) {
                sum += w2[j][k] * delta2[i][k];
            }
            delta1[i][j] = sum * y1[i][j] * (1 - y1[i][j]);
        }
    }

    // Update bobot berdasarkan gradien error
    private static void calculateNewWeights(int i) {
        for (int j = 0; j < nInput; j++) {
            for (int k = 0; k < nHidden; k++) {
                w1[j][k] += learningRate * delta1[i][k] * input[i][j];
            }
        }

        for (int j = 0; j < nHidden; j++) {
            for (int k = 0; k < nOutput; k++) {
                w2[j][k] += learningRate * delta2[i][k] * y1[i][j];
            }
        }
    }

    // Update bias berdasarkan delta
    private static void calculateNewBias() {
        for (int i = 0; i < nHidden; i++) {
            b1[i] += delta1[0][i];
        }

        for (int i = 0; i < nOutput; i++) {
            b2[i] += delta2[0][i];
        }
    }

    // Fungsi aktivasi sigmoid
    private static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }
}
