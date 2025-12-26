package engineer.skyouo.plugins.naturerevive.spigot.integration.land;

import cn.lunadeer.dominion.api.DominionAPI;
import cn.lunadeer.dominion.api.dtos.CuboidDTO;
import engineer.skyouo.plugins.naturerevive.spigot.NatureRevivePlugin;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class DominionIntegration implements ILandPluginIntegration {
    private static DominionAPI dominionAPI;
    @Override
    public boolean checkHasLand(Chunk chunk) {
        CuboidDTO chunkCuboid = new CuboidDTO(
                chunk.getX() * 16, NatureRevivePlugin.nmsWrapper.getWorldMinHeight(chunk.getWorld()), chunk.getZ() * 16,
                (chunk.getX() + 1) * 16 - 1, chunk.getWorld().getMaxHeight(), (chunk.getZ() + 1) * 16 - 1
        );

        return dominionAPI.getAllDominions().stream()
                .anyMatch(dominionDTO -> dominionDTO.getCuboid().intersectWith(chunkCuboid));
    }

    @Override
    public boolean isInLand(Location location) {
        return dominionAPI.getDominion(location) != null;
    }

    @Override
    public boolean isStrictMode() {
        return NatureRevivePlugin.readonlyConfig.dominionStrictCheck;
    }

    @Override
    public String getPluginName() {
        return "Dominion";
    }

    @Override
    public Type getType() {
        return Type.LAND;
    }

    @Override
    public boolean load() {
        Plugin dominionPlugin = NatureRevivePlugin.instance.getServer().getPluginManager().getPlugin("Dominion");
        try {
            dominionAPI = dominionPlugin != null ? DominionAPI.getInstance() : null;
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            dominionAPI = null;
        }
        return dominionAPI != null;
    }

    @Override
    public boolean isEnabled() {
        return dominionAPI != null;
    }

    @Override
    public boolean shouldExitOnFatal() {
        return isStrictMode();
    }
}
