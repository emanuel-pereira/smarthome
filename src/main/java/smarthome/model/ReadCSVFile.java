package smarthome.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCSVFile {

    Scanner scanner;
    String commaDelimiter = ",";
    FileWriter fileWriter;


    public void readCsvFile(String fileName) throws FileNotFoundException {
        this.scanner = null;
        this.scanner = new Scanner(new File(fileName));
        this.scanner.useDelimiter(this.commaDelimiter);
    }

    public List<String[]> getValuesFromCSVFile() {
        List<String[]> csvValues = new ArrayList<>();
        while (this.scanner.hasNext()) {
            String line = this.scanner.nextLine();
            String[] tokens = line.split(this.commaDelimiter);
            csvValues.add(tokens);
        }
        return csvValues;
    }
}
