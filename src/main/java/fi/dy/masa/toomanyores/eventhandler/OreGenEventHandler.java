package fi.dy.masa.toomanyores.eventhandler;

import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class OreGenEventHandler
{
    @SubscribeEvent
    public void onOreGenMinable(OreGenEvent.GenerateMinable event)
    {
        //TooManyOres.logger.info("Type: " + event.type.toString() + (event.generator != null ? " generator: " + event.generator.toString() : ""));

        // TODO Add configs to fine tune what is allowed to generate
        if (event.type != EventType.DIRT && event.type != EventType.GRAVEL && event.type != EventType.CUSTOM)
        {
            //System.out.println("DENIED!!");
            event.setResult(Event.Result.DENY);
        }
    }
}
