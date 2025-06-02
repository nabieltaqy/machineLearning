import weka.core.converters.CSVLoader;
import weka.core.converters.ArffSaver;
import weka.core.Instances;

import java.io.File;

public class CSVtoARFF {
    public static void main(String[] args) throws Exception {
        // Load CSV
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("vehicledata.csv"));
        Instances data = loader.getDataSet();

        // Save ARFF
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File("VehicleDataWekaVersion.arff"));
        saver.writeBatch();

        System.out.println("CSV berhasil dikonversi ke ARFF.");
    }
}
