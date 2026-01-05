package engineer.skyouo.plugins.naturerevive.spigot.integration.logging;

import engineer.skyouo.plugins.naturerevive.spigot.integration.IDependency;
import engineer.skyouo.plugins.naturerevive.spigot.structs.BlockDataChangeWithPos;
import engineer.skyouo.plugins.naturerevive.spigot.structs.BlockStateWithPos;

public interface ILoggingIntegration extends IDependency {
    boolean log(BlockDataChangeWithPos data);
    boolean logContainer(BlockStateWithPos data);

    boolean shouldLogContainer();
}
