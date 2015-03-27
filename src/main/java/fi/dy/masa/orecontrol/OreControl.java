package fi.dy.masa.orecontrol;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import fi.dy.masa.orecontrol.config.Configs;
import fi.dy.masa.orecontrol.eventhandler.OreGenEventHandler;
import fi.dy.masa.orecontrol.eventhandler.PopulateChunkEventHandler;
import fi.dy.masa.orecontrol.reference.Reference;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptableRemoteVersions="*")
public class OreControl
{
    @Instance(Reference.MOD_ID)
    public static OreControl instance;

    public static org.apache.logging.log4j.Logger logger;
    public static File configFile;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;
        logger = event.getModLog();
        configFile = event.getSuggestedConfigurationFile();

        MinecraftForge.ORE_GEN_BUS.register(new OreGenEventHandler());
        MinecraftForge.EVENT_BUS.register(new PopulateChunkEventHandler());
    }

    @EventHandler
    public void onServerAboutToStart(FMLServerAboutToStartEvent event)
    {
        Configs.loadConfigs(new File(configFile.getAbsolutePath()));
    }
}
