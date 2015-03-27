package fi.dy.masa.orecontrol.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import fi.dy.masa.orecontrol.OreControl;

public class Configs
{
    public static boolean disableRegularVanillaGen;
    public static boolean disableRegularVanillaOres;
    public static boolean disableCustom;

    public static boolean disableDiorite;
    public static boolean disableGranite;
    public static boolean disableAndesite;

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

        Configuration conf = new Configuration(configFile);
        conf.load();

        Property prop;
        String category = "vanilla";

        prop = conf.get(category, "disableRegularVanillaGen", false);
        prop.comment = "Disables all \"regular\" vanilla generation. NOTE: This includes Dirt, Gravel, Andesite, Diorite and Granite pockets inside stone, but not Emeralds or Monster Eggs in Extreme Hills biomes.";
        disableRegularVanillaGen = prop.getBoolean();

        prop = conf.get(category, "disableRegularVanillaOres", false);
        prop.comment = "Disables \"regular\" vanilla _ore_ generation, including Nether Quartz. This does not include Emeralds and Monster Eggs in Extreme Hills biomes, which are not part of the regular ore generation.";
        disableRegularVanillaOres = prop.getBoolean();

        prop = conf.get(category, "disableCustom", false);
        prop.comment = "Disables custom ore generation. NOTE: Very few mods actually use the OreGenEvent.GenerateMinable event though, and this only works with those that do.";
        disableCustom = prop.getBoolean();

        prop = conf.get(category, "disableAndesite", false);
        prop.comment = "Disables Andesite pocket generation inside regular stone.";
        disableAndesite = prop.getBoolean() || disableRegularVanillaGen;

        prop = conf.get(category, "disableDiorite", false);
        prop.comment = "Disables Diorite pocket generation inside regular stone.";
        disableDiorite = prop.getBoolean() || disableRegularVanillaGen;

        prop = conf.get(category, "disableGranite", false);
        prop.comment = "Disables Granite pocket generation inside regular stone.";
        disableGranite = prop.getBoolean() || disableRegularVanillaGen;

        prop = conf.get(category, "disableDirt", false);
        prop.comment = "Disables Dirt pocket generation inside stone.";
        disableDirt = prop.getBoolean() || disableRegularVanillaGen;

        prop = conf.get(category, "disableGravel", false);
        prop.comment = "Disables Gravel pocket generation inside stone.";
        disableGravel = prop.getBoolean() || disableRegularVanillaGen;

        prop = conf.get(category, "disableCoal", false);
        prop.comment = "Disables Coal Ore generation.";
        disableCoal = prop.getBoolean() || disableRegularVanillaOres;

        prop = conf.get(category, "disableDiamond", false);
        prop.comment = "Disables Diamond Ore generation.";
        disableDiamond = prop.getBoolean() || disableRegularVanillaOres;

        prop = conf.get(category, "disableGold", false);
        prop.comment = "Disables Gold Ore generation.";
        disableGold = prop.getBoolean() || disableRegularVanillaOres;

        prop = conf.get(category, "disableIron", false);
        prop.comment = "Disables Iron Ore generation.";
        disableIron = prop.getBoolean() || disableRegularVanillaOres;

        prop = conf.get(category, "disableLapis", false);
        prop.comment = "Disables Lapis Ore generation.";
        disableLapis = prop.getBoolean() || disableRegularVanillaOres;

        prop = conf.get(category, "disableNetherQuartz", false);
        prop.comment = "Disables Nether Quartz Ore generation.";
        disableNetherQuartz = prop.getBoolean() || disableRegularVanillaOres;

        prop = conf.get(category, "disableRedstone", false);
        prop.comment = "Disables Redstone Ore generation.";
        disableRedstone = prop.getBoolean() || disableRegularVanillaOres;

        // Special ores, not part of regular ore generation
        prop = conf.get(category, "disableEmerald", false);
        prop.comment = "Disables Emerald Ore generation in Extreme Hills biomes. NOTE: This will be done after chunk population by replacing all Emerald ore with Stone.";
        disableEmerald = prop.getBoolean();

        prop = conf.get(category, "disableMonsterEgg", false);
        prop.comment = "Disables Monster Egg (= Silverfish block) generation in Extreme Hills biomes. NOTE: This will be done after chunk population by replacing all Monster Eggs with Stone.";
        disableMonsterEgg = prop.getBoolean();

        if (conf.hasChanged() == true)
        {
            conf.save();
        }
    }
}
