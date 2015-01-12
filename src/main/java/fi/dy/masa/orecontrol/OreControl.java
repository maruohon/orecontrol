package fi.dy.masa.orecontrol;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import fi.dy.masa.orecontrol.config.Configs;
import fi.dy.masa.orecontrol.eventhandler.OreGenEventHandler;
import fi.dy.masa.orecontrol.reference.Reference;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
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
        //Configs.loadConfigs(event.getSuggestedConfigurationFile());
        MinecraftForge.ORE_GEN_BUS.register(new OreGenEventHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }

    @EventHandler
    public void onServerAboutToStart(FMLServerAboutToStartEvent event)
    {
        Configs.loadConfigs(new File(configFile.getAbsolutePath()));
    }
}
