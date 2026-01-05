package engineer.skyouo.plugins.naturerevive.common;

import org.bukkit.Bukkit;

public class VersionUtil {
    private static Boolean isPaperCache;
    private static Boolean isFoliaCache;
    public static boolean isPaper() {
        if (isPaperCache != null)
            return isPaperCache;

        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            isPaperCache = true;
            return true;
        } catch (Exception e) {
            isPaperCache = false;
            return false;
        }
    }

    public static boolean isFolia() {
        if (isFoliaCache != null)
            return isFoliaCache;

        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            isFoliaCache = true;
            return true;
        } catch (Exception ignored) {
            isFoliaCache = false;
            return false;
        }
    }

    public static int[] getVersion() {
        int[] version = {0, 0, 0};
        String[] splited = Bukkit.getBukkitVersion().split("-")[0].split("\\.");
        for (int i = 0; i < splited.length; i++) {
            try {
                version[i] = Integer.parseInt(splited[i]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                version[i] = 0;
            }
        }

        return version;
    }

    public static boolean isVersionMinorThan(int major) {
        return getVersion()[1] > major;
    }
}