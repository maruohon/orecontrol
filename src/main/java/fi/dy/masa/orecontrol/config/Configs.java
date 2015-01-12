package fi.dy.masa.orecontrol.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import fi.dy.masa.orecontrol.OreControl;

public class Configs
{
    public static Property confDisableRegularVanillaGen;
    public static Property confDisableRegularVanillaOres;
    public static Property confDisableDirt;
    public static Property confDisableGravel;
    public static Property confDisableCoal;
    public static Property confDisableDiamond;
    public static Property confDisableGold;
    public static Property confDisableIron;
    public static Property confDisableLapis;
    public static Property confDisableRedstone;
    public static Property confDisableNetherQuartz;
    public static Property confDisableEmerald;
    public static Property confDisableMonsterEgg;


    public static boolean disableRegularVanillaGen;
    public static boolean disableRegularVanillaOres;
    public static boolean disableDirt;
    public static boolean disableGravel;
    public static boolean disableCoal;
    public static boolean disableDiamond;
    public static boolean disableGold;
    public static boolean disableIron;
    public static boolean disableLapis;
    public static boolean disableRedstone;
    public static boolean disableNetherQuartz;
    public static boolean disableEmerald;
    public static boolean disableMonsterEgg;


    public static void loadConfigs(File configFile)
    {
        OreControl.logger.info("Loading configuration...");

        String category;
        Configuration conf = new Configuration(configFile);
        conf.load();

        category = "vanilla";
        confDisableRegularVanillaGen = conf.get(category, "disableRegularVanillaGen", false);
        confDisableRegularVanillaGen.comment = "Disables all \"regular\" vanilla generation. NOTE: This includes Dirt and Gravel pockets inside stone, but not Emeralds in Extreme Hills biomes.";

        confDisableRegularVanillaOres = conf.get(category, "disableRegularVanillaOres", false);
        confDisableRegularVanillaOres.comment = "Disables \"regular\" vanilla _ore_ generation, including Nether Quartz. This does not include Emeralds in Extreme Hills biomes, which are not part of the regular ore generation.";

        confDisableDirt = conf.get(category, "disableDirt", false);
        confDisableDirt.comment = "Disables Dirt pocket generation inside stone.";

        confDisableGravel = conf.get(category, "disableGravel", false);
        confDisableGravel.comment = "Disables Gravel pocket generation inside stone.";

        confDisableCoal = conf.get(category, "disableCoal", false);
        confDisableCoal.comment = "Disables Coal Ore generation.";

        confDisableDiamond = conf.get(category, "disableDiamond", false);
        confDisableDiamond.comment = "Disables Diamond Ore generation.";

        confDisableGold = conf.get(category, "disableGold", false);
        confDisableGold.comment = "Disables Gold Ore generation.";

        confDisableIron = conf.get(category, "disableIron", false);
        confDisableIron.comment = "Disables Iron Ore generation.";

        confDisableLapis = conf.get(category, "disableLapis", false);
        confDisableLapis.comment = "Disables Lapis Ore generation.";

        confDisableRedstone = conf.get(category, "disableRedstone", false);
        confDisableRedstone.comment = "Disables Redstone Ore generation.";

        confDisableNetherQuartz = conf.get(category, "disableNetherQuartz", false);
        confDisableNetherQuartz.comment = "Disables Nether Quartz Ore generation.";

        confDisableEmerald = conf.get(category, "disableEmerald", false);
        confDisableEmerald.comment = "Disables Emerald Ore generation in Extreme Hills biomes. NOTE: To achieve this, the BiomeGenHills generator will be replaced.";

        confDisableMonsterEgg = conf.get(category, "disableMonsterEgg", false);
        confDisableMonsterEgg.comment = "Disables Monster Egg (= Silverfish block) generation in Extreme Hills biomes. NOTE: To achieve this, the BiomeGenHills generator will be replaced.";

        if (conf.hasChanged() == true)
        {
            conf.save();
        }

        setValuesFromProperties();
    }

    public static void setValuesFromProperties()
    {
        disableRegularVanillaGen    = confDisableRegularVanillaGen.getBoolean();
        disableRegularVanillaOres   = confDisableRegularVanillaOres.getBoolean();
        disableDirt                 = disableRegularVanillaGen || confDisableDirt.getBoolean();
        disableGravel               = disableRegularVanillaGen || confDisableGravel.getBoolean();
        disableCoal                 = disableRegularVanillaOres || confDisableCoal.getBoolean();
        disableDiamond              = disableRegularVanillaOres || confDisableDiamond.getBoolean();
        disableGold                 = disableRegularVanillaOres || confDisableGold.getBoolean();
        disableIron                 = disableRegularVanillaOres || confDisableIron.getBoolean();
        disableLapis                = disableRegularVanillaOres || confDisableLapis.getBoolean();
        disableRedstone             = disableRegularVanillaOres || confDisableRedstone.getBoolean();
        disableNetherQuartz         = disableRegularVanillaOres || confDisableNetherQuartz.getBoolean();
        disableEmerald              = confDisableEmerald.getBoolean();
        disableMonsterEgg           = confDisableMonsterEgg.getBoolean();
    }
}
