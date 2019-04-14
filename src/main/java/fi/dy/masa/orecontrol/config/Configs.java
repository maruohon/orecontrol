package fi.dy.masa.orecontrol.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import fi.dy.masa.orecontrol.OreControl;
import fi.dy.masa.orecontrol.reference.Reference;
import gnu.trove.map.hash.TIntObjectHashMap;

public class Configs
{
    private static final Pattern CONFIG_FILE_PATTERN = Pattern.compile(Reference.MOD_ID + "_dim(-?[0-9]+)\\.cfg");
    private static final TIntObjectHashMap<Configs> DIM_CONFIGS = new TIntObjectHashMap<Configs>();
    private static Configs masterConfig;
    private boolean enablePerDimensionConfigs;
    private boolean minablesDisableAllVanillaGeneration;

    private Map<OreGenEvent.GenerateMinable.EventType, Boolean> disabledMinables;
    private Map<DecorateBiomeEvent.Decorate.EventType, Boolean> disabledDecorations;
    private Map<PopulateChunkEvent.Populate.EventType, Boolean> disabledPopulations;

    public boolean disableSurfaceLavaLakes;

    private Configs(File configFile, boolean isMaster)
    {
        this.disabledMinables = new HashMap<OreGenEvent.GenerateMinable.EventType, Boolean>();
        this.disabledDecorations = new HashMap<DecorateBiomeEvent.Decorate.EventType, Boolean>();
        this.disabledPopulations = new HashMap<PopulateChunkEvent.Populate.EventType, Boolean>();
        this.loadConfigs(configFile, isMaster);
    }

    public static Configs get(World world)
    {
        int dimension = world.provider.getDimension();
        Configs cfg = DIM_CONFIGS.get(dimension);

        return cfg != null ? cfg : masterConfig;
    }

    public static void readConfigs(File modConfigDir)
    {
        modConfigDir.mkdirs();
        DIM_CONFIGS.clear();
        masterConfig = new Configs(new File(modConfigDir, Reference.MOD_ID + ".cfg"), true);

        if (masterConfig.enablePerDimensionConfigs)
        {
            for (File file : modConfigDir.listFiles())
            {
                Matcher matcher = CONFIG_FILE_PATTERN.matcher(file.getName());

                if (matcher.matches())
                {
                    try
                    {
                        int dimension = Integer.parseInt(matcher.group(1));
                        DIM_CONFIGS.put(dimension, new Configs(file, false));
                    }
                    catch (NumberFormatException e)
                    {
                        OreControl.logger.warn("Failed to parse dimension id from config filename from '{}'", file.getName());
                    }
                }
            }
        }
    }

    private void loadConfigs(File configFile, boolean isMaster)
    {
        OreControl.logger.info("Loading configuration from '{}'", configFile.getName());

        Configuration conf = new Configuration(configFile, null, true);
        conf.load();

        Property prop;
        String category;

        if (isMaster)
        {
            category = "Generic";

            prop = conf.get(category, "enablePerDimensionConfigs", false);
            prop.setComment("If true, then per-dimension configs named like 'orecontrol_dim1.cfg' will override the master config, if they exist");
            this.enablePerDimensionConfigs = prop.getBoolean();
        }

        category = "Minable";

        prop = conf.get(category, "disableAllVanillaGeneration", false);
        prop.setComment("Disables all vanilla generation of \"pockets-of-resources-inside-stone\". This includes all ores and also Dirt/Gravel/Andesite/Diorite/Granite, and since v0.3.1 also Emerald Ore and Silverfish blocks in Extreme Hills biomes.");
        this.minablesDisableAllVanillaGeneration = prop.getBoolean();

        this.disabledMinables.clear();

        for (OreGenEvent.GenerateMinable.EventType type : OreGenEvent.GenerateMinable.EventType.values())
        {
            prop = conf.get(category, "disable" + type, false);
            this.disabledMinables.put(type, prop.getBoolean());

            String comment = COMMENTS_MINABLES.get(type);

            if (comment != null)
            {
                prop.setComment(comment);
            }
        }

        category = "Decorate";
        this.disabledDecorations.clear();

        for (DecorateBiomeEvent.Decorate.EventType type : DecorateBiomeEvent.Decorate.EventType.values())
        {
            prop = conf.get(category, "disable" + type, false);
            this.disabledDecorations.put(type, prop.getBoolean());
        }

        category = "Populate";
        this.disabledPopulations.clear();

        for (PopulateChunkEvent.Populate.EventType type : PopulateChunkEvent.Populate.EventType.values())
        {
            prop = conf.get(category, "disable" + type, false);
            this.disabledPopulations.put(type, prop.getBoolean());
        }

        category = "Custom";

        prop = conf.get(category, "disableSurfaceLavaLakes", false, "Disable lava lakes on the surface.\nThis disables all lava lakes via the vanilla code, and tries to\n generate the underground lakes via the mod code.");
        this.disableSurfaceLavaLakes = prop.getBoolean();

        if (conf.hasChanged())
        {
            conf.save();
        }
    }

    public boolean minablesDisableAllVanillaGeneration()
    {
        return this.minablesDisableAllVanillaGeneration;
    }

    public boolean minableIsDisabled(OreGenEvent.GenerateMinable.EventType type)
    {
        Boolean value = this.disabledMinables.get(type);
        return value != null && value.booleanValue();
    }

    public boolean decorationIsDisabled(DecorateBiomeEvent.Decorate.EventType type)
    {
        Boolean value = this.disabledDecorations.get(type);
        return value != null && value.booleanValue();
    }

    public boolean populationIsDisabled(PopulateChunkEvent.Populate.EventType type)
    {
        Boolean value = this.disabledPopulations.get(type);
        return value != null && value.booleanValue();
    }

    private static final Map<OreGenEvent.GenerateMinable.EventType, String> COMMENTS_MINABLES
        = new HashMap<OreGenEvent.GenerateMinable.EventType, String>();

    static
    {
        COMMENTS_MINABLES.put(OreGenEvent.GenerateMinable.EventType.ANDESITE, "Disables Andesite pocket generation inside regular stone.");
        COMMENTS_MINABLES.put(OreGenEvent.GenerateMinable.EventType.CUSTOM, "Disables custom ore generation. NOTE: Very few mods seem to actually use the OreGenEvent.GenerateMinable event though, and this only works with those that do.");
        COMMENTS_MINABLES.put(OreGenEvent.GenerateMinable.EventType.DIORITE, "Disables Diorite pocket generation inside regular stone.");
        COMMENTS_MINABLES.put(OreGenEvent.GenerateMinable.EventType.DIRT, "Disables Dirt pocket generation inside stone.");
        COMMENTS_MINABLES.put(OreGenEvent.GenerateMinable.EventType.EMERALD, "Disables Emerald Ore generation (in Extreme Hills biomes).");
        COMMENTS_MINABLES.put(OreGenEvent.GenerateMinable.EventType.GRANITE, "Disables Granite pocket generation inside regular stone.");
        COMMENTS_MINABLES.put(OreGenEvent.GenerateMinable.EventType.GRAVEL, "Disables Gravel pocket generation inside stone.");
        COMMENTS_MINABLES.put(OreGenEvent.GenerateMinable.EventType.SILVERFISH, "Disables Silverfish block (= Monster Egg) generation (in Extreme Hills biomes).");
    }
}
