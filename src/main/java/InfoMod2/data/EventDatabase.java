package InfoMod2.data;

import InfoMod2.InfoMod2;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.TreeMap;

public class EventDatabase {
    // Raw data (from GSON)
    @SerializedName("act1")
    @Expose
    private List<EventDetail> act1;

    @SerializedName("act2")
    @Expose
    private List<EventDetail> act2;

    @SerializedName("act3")
    @Expose
    private List<EventDetail> act3;

    @SerializedName("shrines")
    @Expose
    private List<EventDetail> shrines;

    // Cleaned data
    public static TreeMap<String, EventDetail> act1_events = new TreeMap<>();

    //"InfoMod2/data/act1.json"
    public static void load(String internalPath) {
        InputStream stream = InfoMod2.class.getResourceAsStream(internalPath);
        if (stream != null) {
            Reader reader = new InputStreamReader(stream);
            EventDatabase database = new Gson().fromJson(reader, EventDatabase.class);

            System.out.println("Loaded " + database.act1.size() + " act 1 events from the JSON:");

            // Put the data into the maps for easier access
            for (EventDetail event : database.act1)
                act1_events.put(event.name, event);

            // Output
//            String json = new GsonBuilder().setPrettyPrinting().create().toJson(database);
//            System.out.println(json);
        } else {
            System.out.println("ERROR: internal path " + internalPath + " does not exist");
        }

    }
}
