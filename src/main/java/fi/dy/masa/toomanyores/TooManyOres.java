package fi.dy.masa.toomanyores;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fi.dy.masa.toomanyores.eventhandler.OreGenEventHandler;
import fi.dy.masa.toomanyores.reference.Reference;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class TooManyOres
{
    @Instance(Reference.MOD_ID)
    public static TooManyOres instance;

    //@SidedProxy(clientSide = Reference.PROXY_CLASS_CLIENT, serverSide = Reference.PROXY_CLASS_SERVER)
    //public static IProxy proxy;
    public static org.apache.logging.log4j.Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;
        logger = event.getModLog();
        MinecraftForge.ORE_GEN_BUS.register(new OreGenEventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
