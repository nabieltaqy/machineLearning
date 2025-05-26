import java.text.DecimalFormat;
import java.util.Random;

public class Qlearning {
    final DecimalFormat df = new DecimalFormat("#.##");

    // path finding
    final double alpha = 0.1;
    final double gamma = 0.9;

    // states A - L (3x4 grid)
    // posisi:
    //  _________
    // |A|B|C|D|  (D goal state)
    // |________|
    // |E|F|G|H|  (F obstacle, H trap)
    // |________|
    // |I|J|K|L|  (start at I)
    // |________|

    final int stateA = 0;
    final int stateB = 1;
    final int stateC = 2;
    final int stateD = 3; // goal
    final int stateE = 4;
    final int stateF = 5; // obstacle
    final int stateG = 6;
    final int stateH = 7; // trap
    final int stateI = 8; // start
    final int stateJ = 9;
    final int stateK = 10;
    final int stateL = 11;

    final int statesCount = 12;
    final int[] states = {stateA, stateB, stateC, stateD,
                          stateE, stateF, stateG, stateH,
                          stateI, stateJ, stateK, stateL};

    int[][] R = new int[statesCount][statesCount]; // Reward lookup table
    double[][] Q = new double[statesCount][statesCount]; // Q-learning

    // Define possible actions from each state (up, down, left, right) except obstacles
    int[][] actions = new int[statesCount][];

    String[] stateNames = new String[] {
        "A", "B", "C", "D",
        "E", "F", "G", "H",
        "I", "J", "K", "L"
    };

    public Qlearning() {
        init();
    }

    public void init() {
        // Setup possible actions for each state (no moves from obstacle F)
        for (int s = 0; s < statesCount; s++) {
            if (s == stateF) {
                // obstacle, no actions
                actions[s] = new int[]{};
                continue;
            }

            int row = s / 4; // 3 rows, 4 cols
            int col = s % 4;

            int[] temp = new int[4];
            int count = 0;

            // up
            if (row > 0) {
                int up = s - 4;
                if (up != stateF) temp[count++] = up;
            }
            // down
            if (row < 2) {
                int down = s + 4;
                if (down != stateF) temp[count++] = down;
            }
            // left
            if (col > 0) {
                int left = s - 1;
                if (left != stateF) temp[count++] = left;
            }
            // right
            if (col < 3) {
                int right = s + 1;
                if (right != stateF) temp[count++] = right;
            }

            actions[s] = new int[count];
            System.arraycopy(temp, 0, actions[s], 0, count);
        }

        // Set rewards:
        // Goal D: +100
        // Trap H: -100
        for (int i = 0; i < statesCount; i++) {
            for (int j : actions[i]) {
                if (j == stateD) {
                    R[i][j] = 100;
                } else if (j == stateH) {
                    R[i][j] = -100;
                } else {
                    R[i][j] = 0;
                }
            }
        }
    }

    public void run() {
        /*
         1. Set parameter, and environment reward matrix R
         2. Initialize matrix Q as zero matrix
         3. For each episode: Select initial start state I (fixed)
            Do while not reach goal or trap state
                Select one among all possible actions for current state
                Using this possible action, consider to go to the next state
                Get maximum Q value of this next state based on all possible actions
                Compute Q update
                Set the next state as the current state
         */

        Random rand = new Random();
        for (int episode = 0; episode < 1000; episode++) { // train episodes
            int state = stateI; // start from I
            while (state != stateD && state != stateH) { // until goal or trap
                int[] possibleActions = actions[state];
                if (possibleActions.length == 0) break; // no move possible (obstacle or dead end)

                // random action selection
                int index = rand.nextInt(possibleActions.length);
                int action = possibleActions[index];

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
        for (int nextState : actionsFromState) {
            double value = Q[s][nextState];
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue == Double.MIN_VALUE ? 0 : maxValue; // handle no actions case
    }

    int policy(int state) {
        int[] actionsFromState = actions[state];
        double maxValue = Double.MIN_VALUE;
        int policyGotoState = state; // default go to self if no actions
        for (int nextState : actionsFromState) {
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
    System.out.println("Q-learning results (formatted):");

    // Cetak header kolom (A, B, C, ..., L)
    System.out.print("     ");
    for (String col : stateNames) {
        System.out.printf("%6s", col);
    }
    System.out.println("\n" + "    " + "-".repeat(6 * stateNames.length));

    // Cetak isi Q-table
    for (int i = 0; i < Q.length; i++) {
        System.out.printf("%-3s |", stateNames[i]); // Baris label
        for (int j = 0; j < Q[i].length; j++) {
            System.out.printf("%6s", df.format(Q[i][j]));
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
