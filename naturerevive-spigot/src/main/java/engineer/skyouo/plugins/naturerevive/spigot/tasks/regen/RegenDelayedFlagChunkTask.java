package engineer.skyouo.plugins.naturerevive.spigot.tasks.regen;

import engineer.skyouo.plugins.naturerevive.spigot.NatureRevivePlugin;
import engineer.skyouo.plugins.naturerevive.spigot.listeners.ChunkRelatedEventListener;
import engineer.skyouo.plugins.naturerevive.spigot.tasks.Task;
import engineer.skyouo.plugins.naturerevive.spigot.util.ScheduleUtil;
import org.bukkit.Location;

import static engineer.skyouo.plugins.naturerevive.spigot.NatureRevivePlugin.blockQueue;
import static engineer.skyouo.plugins.naturerevive.spigot.NatureRevivePlugin.readonlyConfig;

public class RegenDelayedFlagChunkTask implements Task {
    @Override
    public void run() {
        for (int i = 0; i < readonlyConfig.blockProcessingAmountPerProcessing && blockQueue.hasNext(); i++) {
            Location location = blockQueue.pop();
            ScheduleUtil.REGION.runTask(NatureRevivePlugin.instance, location, () -> ChunkRelatedEventListener.flagChunk(location));
        }
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public long getDelay() {
        return 20L;
    }

    @Override
    public long getRepeatTime() {
        return readonlyConfig.blockProcessingTick;
    }
}
