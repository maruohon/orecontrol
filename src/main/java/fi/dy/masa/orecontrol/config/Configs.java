package fi.dy.masa.orecontrol.config;

import java.io.File;

import fi.dy.masa.orecontrol.OreControl;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Configs
{
    public static boolean disableAllVanillaGeneration;
    public static boolean disableVanillaOres;
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
    public static boolean disableSilverfish;


    public static void loadConfigs(File configFile)
    {
        OreControl.logger.info("Loading configuration...");

        Configuration conf = new Configuration(configFile);
        conf.load();

        Property prop;
        String category = "vanilla";

        prop = conf.get(category, "disableAllVanillaGeneration", false);
        prop.comment = "Disables all vanilla generation of \"pockets-of-resources-inside-stone\". This includes all ores and also Dirt/Gravel/Andesite/Diorite/Granite, and since v0.3.1 also Emerald Ore and Silverfish blocks in Extreme Hills biomes.";
        disableAllVanillaGeneration = prop.getBoolean();

        prop = conf.get(category, "disableVanillaOres", false);
        prop.comment = "Disables all vanilla _ore_ generation, including Emerald Ore in Extreme Hills biomes and Nether Quartz in the Nether.";
        disableVanillaOres = prop.getBoolean();

        prop = conf.get(category, "disableCustom", false);
        prop.comment = "Disables custom ore generation. NOTE: Very few mods actually use the OreGenEvent.GenerateMinable event though, and this only works with those that do.";
        disableCustom = prop.getBoolean();

        prop = conf.get(category, "disableAndesite", false);
        prop.comment = "Disables Andesite pocket generation inside regular stone.";
        disableAndesite = prop.getBoolean() || disableAllVanillaGeneration;

        prop = conf.get(category, "disableDiorite", false);
        prop.comment = "Disables Diorite pocket generation inside regular stone.";
        disableDiorite = prop.getBoolean() || disableAllVanillaGeneration;

        prop = conf.get(category, "disableGranite", false);
        prop.comment = "Disables Granite pocket generation inside regular stone.";
        disableGranite = prop.getBoolean() || disableAllVanillaGeneration;

        prop = conf.get(category, "disableDirt", false);
        prop.comment = "Disables Dirt pocket generation inside stone.";
        disableDirt = prop.getBoolean() || disableAllVanillaGeneration;

        prop = conf.get(category, "disableGravel", false);
        prop.comment = "Disables Gravel pocket generation inside stone.";
        disableGravel = prop.getBoolean() || disableAllVanillaGeneration;

        prop = conf.get(category, "disableCoal", false);
        prop.comment = "Disables Coal Ore generation.";
        disableCoal = prop.getBoolean() || disableVanillaOres;

        prop = conf.get(category, "disableDiamond", false);
        prop.comment = "Disables Diamond Ore generation.";
        disableDiamond = prop.getBoolean() || disableVanillaOres;

        prop = conf.get(category, "disableGold", false);
        prop.comment = "Disables Gold Ore generation.";
        disableGold = prop.getBoolean() || disableVanillaOres;

        prop = conf.get(category, "disableIron", false);
        prop.comment = "Disables Iron Ore generation.";
        disableIron = prop.getBoolean() || disableVanillaOres;

        prop = conf.get(category, "disableLapis", false);
        prop.comment = "Disables Lapis Ore generation.";
        disableLapis = prop.getBoolean() || disableVanillaOres;

        prop = conf.get(category, "disableNetherQuartz", false);
        prop.comment = "Disables Nether Quartz Ore generation.";
        disableNetherQuartz = prop.getBoolean() || disableVanillaOres;

        prop = conf.get(category, "disableRedstone", false);
        prop.comment = "Disables Redstone Ore generation.";
        disableRedstone = prop.getBoolean() || disableVanillaOres;

        prop = conf.get(category, "disableEmerald", false);
        prop.comment = "Disables Emerald Ore generation (in Extreme Hills biomes).";
        disableEmerald = prop.getBoolean() || disableVanillaOres;

        prop = conf.get(category, "disableSilverfish", false);
        prop.comment = "Disables Silverfish block (= Monster Egg) generation (in Extreme Hills biomes).";
        disableSilverfish = prop.getBoolean() || disableAllVanillaGeneration;

        if (conf.hasChanged() == true)
        {
            conf.save();
        }
    }
}
