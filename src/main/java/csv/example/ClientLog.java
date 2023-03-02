package csv.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVWriter;


public class ClientLog {
    private ArrayList<String[]> arrayList = new ArrayList<>();

    public void log(int productNum, int amount){
        String[] clientLog = (productNum + "," + amount).split(",");
        arrayList.add(clientLog);
    }

    public void exportAsCSV(File textFile){
        try(CSVWriter writer = new CSVWriter(new FileWriter(textFile))){
            writer.writeNext(new String[]{"productNum" + ", " + "amount"});
            for(String[] i : arrayList){
                writer.writeNext(i);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
