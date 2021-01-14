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
    public enum DatabaseType {
        ACT_1, ACT_2, ACT_3, SHRINES
    }

    private static class DatabaseHelper {
        @SerializedName("type") @Expose
        private DatabaseType type;

        @SerializedName("events") @Expose
        private List<EventDetail> events;
    }

//    // Raw data (from GSON)
//    @SerializedName("act1")
//    @Expose
//    private List<EventDetail> act1;
//
//    @SerializedName("act2")
//    @Expose
//    private List<EventDetail> act2;
//
//    @SerializedName("act3")
//    @Expose
//    private List<EventDetail> act3;
//
//    @SerializedName("shrines")
//    @Expose
//    private List<EventDetail> shrines;

    // Cleaned data
    public static TreeMap<String, EventDetail> act1_events = new TreeMap<>();
    public static TreeMap<String, EventDetail> act2_events = new TreeMap<>();
    public static TreeMap<String, EventDetail> act3_events = new TreeMap<>();
    public static TreeMap<String, EventDetail> shrine_events = new TreeMap<>();

    //"InfoMod2/data/act1.json"
    public static void load(String internalPath) {
        InputStream stream = InfoMod2.class.getResourceAsStream(internalPath);
        if (stream != null) {
            Reader reader = new InputStreamReader(stream);
            //EventDatabase database = new Gson().fromJson(reader, EventDatabase.class);
            DatabaseHelper db = new Gson().fromJson(reader, DatabaseHelper.class);
            DatabaseType type = db.type;
            System.out.println("Loaded " + db.events.size() + " events for " + type);

            // Put the data into the maps for easier access
            TreeMap<String, EventDetail> target = null;
            if (type == DatabaseType.ACT_1)
                target = act1_events;
            else if (type == DatabaseType.ACT_2)
                target = act2_events;
            else if (type == DatabaseType.ACT_3)
                target = act3_events;
            else if (type == DatabaseType.SHRINES)
                target = shrine_events;

            if (target != null) {
                for (EventDetail event : db.events)
                    target.put(event.name, event);
            }
            else {
                System.out.println("ERROR: not a valid target");
            }


            // Output
//            String json = new GsonBuilder().setPrettyPrinting().create().toJson(database);
//            System.out.println(json);
        } else {
            System.out.println("ERROR: internal path " + internalPath + " does not exist");
        }

    }
}
