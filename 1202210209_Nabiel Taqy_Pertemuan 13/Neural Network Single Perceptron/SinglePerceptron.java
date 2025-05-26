import java.util.Random;

public class SinglePerceptron {
    double[] w;              // weights
    double threshold;        // bias threshold

    public SinglePerceptron(int inputSize, double threshold) {
        this.threshold = threshold;
        this.w = new double[inputSize];
        Random r = new Random();
        for (int i = 0; i < inputSize; i++) {
            w[i] = r.nextDouble(); // random weights
        }
    }

    public int output(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < x.length; i++) {
            sum += w[i] * x[i];
        }
        return (sum >= threshold) ? 1 : 0;
    }

    public void train(double[][] x, int[] y, double learnrate, int epoch) {
        int n = x.length;
        for (int e = 0; e < epoch; e++) {
            int totalError = 0;
            for (int i = 0; i < n; i++) {
                int result = output(x[i]);
                int error = y[i] - result;
                totalError += Math.abs(error);

                for (int j = 0; j < w.length; j++) {
                    double delta = learnrate * x[i][j] * error;
                    w[j] += delta;
                }
            }

            // Stop early if perfect prediction
            if (totalError == 0) {
                System.out.println("Training complete at epoch: " + e);
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Dataset untuk logika AND
        double[][] inputs = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };

        int[] outputs = {0, 0, 0, 1}; // AND logic

        // Inisialisasi dan training perceptron
        SinglePerceptron neuron = new SinglePerceptron(2, 0.5);
        neuron.train(inputs, outputs, 0.1, 100);

        // Uji hasil
        for (double[] input : inputs) {
            System.out.println(
                (int) input[0] + " AND " + (int) input[1] + " = " + neuron.output(input)
            );
        }
    }
}
