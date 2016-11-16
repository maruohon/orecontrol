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
            if (Configs.disableCustom)
            {
                event.setResult(Event.Result.DENY);
            }

            return;
        }

        // Disable ALL vanilla generation, including dirt and gravel pockets.
        if (Configs.disableAllVanillaGeneration)
        {
            event.setResult(Event.Result.DENY);
            return;
        }

        // Individual ore types
        switch(type)
        {
            case DIORITE:
                if (Configs.disableDiorite)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case GRANITE:
                if (Configs.disableGranite)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case ANDESITE:
                if (Configs.disableAndesite)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case DIRT:
                if (Configs.disableDirt)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case GRAVEL:
                if (Configs.disableGravel)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case COAL:
                if (Configs.disableCoal)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case DIAMOND:
                if (Configs.disableDiamond)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case GOLD:
                if (Configs.disableGold)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case IRON:
                if (Configs.disableIron)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case LAPIS:
                if (Configs.disableLapis)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case REDSTONE:
                if (Configs.disableRedstone)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case QUARTZ:
                if (Configs.disableNetherQuartz)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case EMERALD:
                if (Configs.disableEmerald)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            case SILVERFISH:
                if (Configs.disableSilverfish)
                {
                    event.setResult(Event.Result.DENY);
                }
                break;
            default:
        }
    }
}
