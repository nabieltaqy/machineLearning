import java.text.DecimalFormat;
import java.util.Random;

public class Qlearning {
    final DecimalFormat df = new DecimalFormat("#.##");

    // path finding
    final double alpha = 0.1;
    final double gamma = 0.9;

    // states A, B, C, D, E, F
    // e.g. from A we can go to B or D
    // from C we can only go to C
    // C is goal state, reward 100 when B->C or F->C
    //
    // _______
    // |A|B|C|
    // |_____|
    // |D|E|F|
    // |_____|

    final int stateA = 0;
    final int stateB = 1;
    final int stateC = 2;
    final int stateD = 3;
    final int stateE = 4;
    final int stateF = 5;

    final int statesCount = 6;
    final int[] states = {stateA, stateB, stateC, stateD, stateE, stateF};

    int[][] R = new int[statesCount][statesCount]; // Reward lookup table
    double[][] Q = new double[statesCount][statesCount]; // Q-learning

    int[] actionsFromA = new int[]{stateB, stateD};
    int[] actionsFromB = new int[]{stateA, stateC, stateE};
    int[] actionsFromC = new int[]{stateC}; // only self-loop
    int[] actionsFromD = new int[]{stateA, stateE};
    int[] actionsFromE = new int[]{stateB, stateD, stateF};
    int[] actionsFromF = new int[]{stateC, stateE};
    int[][] actions = {
        actionsFromA, // from A
        actionsFromB, // from B
        actionsFromC, // from C
        actionsFromD, // from D
        actionsFromE, // from E
        actionsFromF  // from F
    };

    String[] stateNames = new String[] {
        "A", "B", "C", "D", "E", "F"
    };

    public Qlearning() {
        init();
    }

    public void init() {
        R[stateB][stateC] = 100; // B -> C
        R[stateF][stateC] = 100; // F -> C
    }

    public void run() {
        /*
         1. Set parameter, and environment reward matrix R
         2. Initialize matrix Q as zero matrix
         3. For each episode: Select random initial state
            Do while not reach goal state o
                Select one among all possible actions for current state o
                Using this possible action, consider to go to the next state o
                Get maximum Q value of this next state based on all possible actions o
                Compute o Set the next state as the current state
         */

        Random rand = new Random();
        for (int i = 0; i < 1000; i++) { // train episodes
            int state = rand.nextInt(statesCount);
            while (state != stateC) { // until goal state C
                int[] actionsFromState = actions[state];

                // selection strategy is random in this example
                int index = rand.nextInt(actionsFromState.length);
                int action = actionsFromState[index];

                int nextState = action;

                double q = Q[state][action];
                double maxQ = maxQ(nextState);
                int r = R(state, action);

                double value = q + alpha * (r + gamma * maxQ - q);
                setQ(state, action, value);

                state = nextState;
            }
        }
    }

    double maxQ(int s) {
        int[] actionsFromState = actions[s];
        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < actionsFromState.length; i++) {
            int nextState = actionsFromState[i];
            double value = Q[s][nextState];

            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    int policy(int state) {
        int[] actionsFromState = actions[state];
        double maxValue = Double.MIN_VALUE;
        int policyGotoState = state;
        for (int i = 0; i < actionsFromState.length; i++) {
            int nextState = actionsFromState[i];
            double value = Q[state][nextState];

            if (value > maxValue) {
                maxValue = value;
                policyGotoState = nextState;
            }
        }
        return policyGotoState;
    }

    double Q(int s, int a) {
        return Q[s][a];
    }

    void setQ(int s, int a, double value) {
        Q[s][a] = value;
    }

    int R(int s, int a) {
        return R[s][a];
    }

    void printResults() {
        System.out.println("Q-learning results:");
        for (int i = 0; i < Q.length; i++) {
            System.out.print("State " + stateNames[i] + ": ");
            for (int j = 0; j < Q[i].length; j++) {
                System.out.print(df.format(Q[i][j]) + " ");
            }
            System.out.println();
        }
    }

    void showPolicy() {
        System.out.println("\nShow Policy:");
        for (int i = 0; i < states.length; i++) {
            int from = states[i];
            int to = policy(from);
            System.out.println("From state " + stateNames[from] + " go to state " + stateNames[to]);
        }
    }

    public static void main(String[] args) {
        long BEGIN = System.currentTimeMillis();

        Qlearning obj = new Qlearning();

        obj.run();
        obj.printResults();
        obj.showPolicy();

        long END = System.currentTimeMillis();
        System.out.println("Time taken: " + (END - BEGIN) / 1000.0 + " seconds");
    }
}
