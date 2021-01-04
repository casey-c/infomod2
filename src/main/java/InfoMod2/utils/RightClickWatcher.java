package InfoMod2.utils;

import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.util.LinkedList;

// Designed for long lived hitboxes (e.g. you can only add hitboxes to track them, not remove them individually)
// Note: hitboxes aren't updated from here since the intent is for the watcher to watch existing, already totally functional
// hitboxes (e.g. from the base game)
public class RightClickWatcher {
    private static class HitboxAssistant {
        private Hitbox hb;
        private boolean rightMouseDown;

        private Runnable callback;

        private HitboxAssistant(Hitbox hb, Runnable callback) {
            this.hb = hb;
            this.callback = callback;
        }

        private void update() {
            // Don't update if a screen is already up
            if (ScreenHelper.isScreenUp())
                return;

            if (InputHelper.isMouseDown_R) {
                if (hb.hovered) {
                    // Start right click
                    rightMouseDown = true;
                }
                else {
                    // Left the hitbox early
                    rightMouseDown = false;
                }
            }
            else {
                if (hb.hovered) {
                    // Right click event
                    if (rightMouseDown) {
                        rightMouseDown = false;
                        callback.run();
                    }
                }
                else {
                    rightMouseDown = false;
                }
            }
        }
    }

    public static LinkedList<HitboxAssistant> hitboxes = new LinkedList<>();

    public static void watch(Hitbox hb, Runnable callback) {
        if (hb != null)
            hitboxes.add(new HitboxAssistant(hb, callback));
        else {
            System.out.println("ojb ERROR: null hitbox given to RightClickWatcher");
        }
    }

    public static void clearAll() {
        hitboxes.clear();
    }

    public static void update() {
        for (HitboxAssistant hb : hitboxes)
            hb.update();
    }
}
