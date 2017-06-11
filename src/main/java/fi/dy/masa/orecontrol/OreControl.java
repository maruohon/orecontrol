package fi.dy.masa.orecontrol;

import java.io.File;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import fi.dy.masa.orecontrol.config.Configs;
import fi.dy.masa.orecontrol.eventhandler.OreControlEventHandler;
import fi.dy.masa.orecontrol.reference.Reference;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION,
    acceptableRemoteVersions="*", acceptedMinecraftVersions = "1.12")
public class OreControl
{
    @Mod.Instance(Reference.MOD_ID)
    public static OreControl instance;

    public static org.apache.logging.log4j.Logger logger;
    public static File configDir;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;
        logger = event.getModLog();
        configDir = new File(event.getModConfigurationDirectory(), Reference.MOD_ID);
        Configs.readConfigs(configDir);

        OreControlEventHandler handler = new OreControlEventHandler();
        MinecraftForge.ORE_GEN_BUS.register(handler);
        MinecraftForge.TERRAIN_GEN_BUS.register(handler);
    }

    @Mod.EventHandler
    public void onServerAboutToStart(FMLServerAboutToStartEvent event)
    {
        Configs.readConfigs(configDir);
    }
}
