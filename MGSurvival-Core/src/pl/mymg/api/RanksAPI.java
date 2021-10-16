package pl.mymg.api;

import org.bukkit.entity.Player;
import pl.mymg.data.RanksData;
import pl.mymg.main.Main;

import java.util.List;
import java.util.UUID;

public class RanksAPI {

    Main plugin;

    public RanksAPI (Main m) {
        plugin = m;
    }

    static RanksData rd;

    public RanksAPI() {
        rd = RanksData.getInstance();
    }

    public static void setRank(Player gracz, String rank) {
        if (gracz != null) {
            UUID uuid = gracz.getUniqueId();

            rd.getData().set(uuid + ".rank", rank);
            rd.saveData();
        }
    }

    public static void setDefaultRank(String rank) {
        rd.getData().set("defaultRank", rank);
        rd.saveData();
    }

    public static void addRankPermission(String rank, String permission) {
        List<String> permsList = rd.getData().getStringList("ranks. " + rank + ".permissions");

        permsList.add(permission);

        rd.getData().set("ranks. " + rank + ".permissions", permsList);
        rd.saveData();
    }

    public static void removeRankPermission(String rank, String permission) {
        List<String> permsList = rd.getData().getStringList("ranks. " + rank + ".permissions");

        permsList.remove(permission);

        rd.getData().set("ranks. " + rank + ".permissions", permsList);
        rd.saveData();
    }

    public static void setRankPrefix(String rank, String prefix) {
        rd.getData().set("ranks." + rank + ".prefix", prefix);
        rd.saveData();
    }

    public static String getRank(Player p) {
        String rank = rd.getData().getString(p.getUniqueId() + ".rank");

        if (rank == null) {
            rd.getData().getString("defaultRank");
        }

        return rank;
    }

    public static boolean hasPermission(Player p, String permission) {
        boolean solution = false;

        String rank = getRank(p);

        List<String> perms = rd.getData().getStringList("ranks." + rank + ".permission");

        if (perms.contains(permission)) {
            solution = true;
        } else {
            solution = false;
        }

        return solution;
    }

    public static String getRankPrefix(String rank) {
        String prefix = rd.getData().getString("ranks." + rank + ".prefix");

        return prefix;
    }

    public static String getDefaultRank(String rank) {
        String defaultRank = rd.getData().getString("defaultRank");

        return defaultRank;
    }

    public static void createRank(String rank, String prefix) {
        rd.getData().set("ranks." + rank + ".prefix", prefix);
        rd.saveData();
    }
}
