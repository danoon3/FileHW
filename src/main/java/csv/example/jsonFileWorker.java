package csv.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class jsonFileWorker {
    public Basket loadFromJSON(File textFile, Basket basket) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(textFile));
            JSONObject jsonObject = (JSONObject) obj;
            String jsonText = String.valueOf(jsonObject);

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            basket = gson.fromJson(jsonText, Basket.class);
        } catch (IOException | ParseException e) {
            e.getMessage();
        }
        return basket;
    }

    public void saveToJSON(File textFile, Basket basket) {
        try (FileWriter fileWriter = new FileWriter(textFile)) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            fileWriter.write(gson.toJson(basket));
            fileWriter.flush();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
