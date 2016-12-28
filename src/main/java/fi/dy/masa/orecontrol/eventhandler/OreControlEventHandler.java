package fi.dy.masa.orecontrol.eventhandler;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import fi.dy.masa.orecontrol.config.Configs;

public class OreControlEventHandler
{
    @SubscribeEvent
    public void onOreGenMinable(OreGenEvent.GenerateMinable event)
    {
        Configs configs = Configs.get(event.getWorld());
        EventType type = event.getType();

        // Disable custom ores, if a mod happens to fire the event
        if (type == EventType.CUSTOM)
        {
            if (configs.minableIsDisabled(type))
            {
                event.setResult(Event.Result.DENY);
            }
        }
        // Disable ALL vanilla generation, including dirt and gravel pockets.
        else if (configs.minablesDisableAllVanillaGeneration())
        {
            event.setResult(Event.Result.DENY);
        }
        // Disable individual types
        else if (configs.minableIsDisabled(type))
        {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public void onDecorateBiome(DecorateBiomeEvent.Decorate event)
    {
        if (Configs.get(event.getWorld()).decorationIsDisabled(event.getType()))
        {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public void onPopulateChunk(PopulateChunkEvent.Populate event)
    {
        if (Configs.get(event.getWorld()).populationIsDisabled(event.getType()))
        {
            event.setResult(Event.Result.DENY);
        }
    }
}
