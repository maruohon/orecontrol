package fi.dy.masa.orecontrol.eventhandler;

import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import fi.dy.masa.orecontrol.config.Configs;


public class OreGenEventHandler
{
    @SubscribeEvent
    public void onOreGenMinable(OreGenEvent.GenerateMinable event)
    {
        //OreControl.logger.info("Type: " + event.type.toString() + (event.generator != null ? " generator: " + event.generator.toString() : ""));
        //OreControl.logger.info("Type: " + event.type + " worldX: " + event.worldX + " worldZ: " + event.worldZ);

        if (event.type == EventType.CUSTOM)
        {
            return;
        }

        if (Configs.disableRegularVanillaGen == true)
        {
            event.setResult(Event.Result.DENY);
            return;
        }

        switch(event.type)
        {
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
            default:
        }
    }
}
