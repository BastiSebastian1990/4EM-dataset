import java.io.FileWriter;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class WriteJson {

    public static void main(String[] args) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("vorname", "Paul");
        builder.add("nachname", "Schmitz");
        builder.add("alter", 14);

        JsonArrayBuilder arrb = Json.createArrayBuilder();

        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("stil", "rock");
        job.add("band", "U2");
        arrb.add(job);

        job = Json.createObjectBuilder();
        job.add("stil", "metal");
        job.add("band", "Black Sabbath");
        arrb.add(job);

        builder.add("musik", arrb);
        builder.add("hungrig", true);
        JsonObject jo = builder.build();

        try {
            FileWriter fw = new FileWriter("test.txt");
            JsonWriter jsonWriter = Json.createWriter(fw);
            jsonWriter.writeObject(jo);
            jsonWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}