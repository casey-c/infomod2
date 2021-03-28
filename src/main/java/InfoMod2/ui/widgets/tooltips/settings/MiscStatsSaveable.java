package InfoMod2.ui.widgets.tooltips.settings;

import InfoMod2.ui.widgets.tooltips.groups.SettingsTips;
import basemod.BaseMod;
import basemod.abstracts.CustomSavableRaw;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.JsonElement;

// Ensure the data is properly saved/loaded between game boots
@SpireInitializer
public class MiscStatsSaveable implements CustomSavableRaw {
    public static void initialize() { new MiscStatsSaveable(); }

    public MiscStatsSaveable() {
        BaseMod.addSaveField("OJB_INFOMOD_MISC_STATS", this);
    }

    @Override
    public JsonElement onSaveRaw() {
        System.out.println("MiscStatsSaveable: onSaveRaw");

        return SettingsTips.serialize();
    }

    @Override
    public void onLoadRaw(JsonElement jsonElement) {
        System.out.println("MiscStatsSaveable: onLoadRaw");
        System.out.println("jsonElement: " + jsonElement);

        if (jsonElement != null && jsonElement.isJsonObject())
            SettingsTips.deserialize(jsonElement.getAsJsonObject());
    }
}
