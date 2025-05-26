import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MLPData {
    private String[] classtype = new String[] { "Bike", "Car", "Bus", "Truck" };

    public MLPData() {
        Random rand = new Random(System.nanoTime());

        try (BufferedWriter out = new BufferedWriter(new FileWriter("vehicledata100000.csv"))) {
            out.write("wheels,chassis,pax,vtype\n");

            for (int i = 0; i < 100000; i++) {
                StringBuilder sb = new StringBuilder();
                int type = rand.nextInt(4);

                switch (type) {
                    case 0: // Bike
                        sb.append((rand.nextInt(1) + 1)).append(",");      // wheels: 1
                        sb.append((rand.nextInt(1) + 1)).append(",");      // chassis: 1
                        sb.append((rand.nextInt(1) + 1)).append(",");      // pax: 1
                        sb.append(classtype[0]);
                        break;

                    case 1: // Car
                        sb.append((rand.nextInt(2) + 4)).append(",");      // wheels: 4–5
                        sb.append((rand.nextInt(4) + 1)).append(",");      // chassis: 1–4
                        sb.append((rand.nextInt(4) + 1)).append(",");      // pax: 1–4
                        sb.append(classtype[1]);
                        break;

                    case 2: // Bus
                        sb.append((rand.nextInt(6) + 4)).append(",");      // wheels: 4–9
                        sb.append((rand.nextInt(12) + 12)).append(",");    // chassis: 12–23
                        sb.append((rand.nextInt(30) + 10)).append(",");    // pax: 10–39
                        sb.append(classtype[2]);
                        break;

                    case 3: // Truck
                        sb.append("18,");                                  // wheels: 18
                        sb.append((rand.nextInt(10) + 20)).append(",");    // chassis: 20–29
                        sb.append((rand.nextInt(2) + 1)).append(",");      // pax: 1–2
                        sb.append(classtype[3]);
                        break;
                }

                out.write(sb.toString() + "\n");
            }

            System.out.println("Dataset 'vehicledata100000.csv' berhasil dibuat!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MLPData();
    }
}
