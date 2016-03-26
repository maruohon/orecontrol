package fi.dy.masa.orecontrol.eventhandler;

import fi.dy.masa.orecontrol.config.Configs;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OreGenEventHandler
{
    @SubscribeEvent
    public void onOreGenMinable(OreGenEvent.GenerateMinable event)
    {
        //OreControl.logger.info("Type: " + event.type.toString() + (event.generator != null ? " generator: " + event.generator.toString() : ""));
        //OreControl.logger.info("Type: " + event.type + " worldX: " + event.worldX + " worldZ: " + event.worldZ);

        EventType type = event.getType();

        if (type == EventType.CUSTOM)
        {
            if (Configs.disableCustom == true)
            {
                event.setResult(Event.Result.DENY);
            }

            return;
        }

        // Disable ALL vanilla generation, including dirt and gravel pockets.
        if (Configs.disableAllVanillaGeneration == true)
        {
            event.setResult(Event.Result.DENY);
            return;
        }

        // Individual ore types
        switch(type)
        {
            case DIORITE:
                if (Configs.disableDiorite == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case GRANITE:
                if (Configs.disableGranite == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case ANDESITE:
                if (Configs.disableAndesite == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case DIRT:
                if (Configs.disableDirt == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case GRAVEL:
                if (Configs.disableGravel == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case COAL:
                if (Configs.disableCoal == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case DIAMOND:
                if (Configs.disableDiamond == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case GOLD:
                if (Configs.disableGold == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case IRON:
                if (Configs.disableIron == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case LAPIS:
                if (Configs.disableLapis == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case REDSTONE:
                if (Configs.disableRedstone == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case QUARTZ:
                if (Configs.disableNetherQuartz == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case EMERALD:
                if (Configs.disableEmerald == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case SILVERFISH:
                if (Configs.disableSilverfish == true)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            default:
        }
    }
}
