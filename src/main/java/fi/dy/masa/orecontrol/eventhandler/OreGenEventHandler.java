package fi.dy.masa.orecontrol.eventhandler;

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

        // TODO Add configs to fine tune what is allowed to generate
        if (event.type != EventType.DIRT && event.type != EventType.GRAVEL && event.type != EventType.CUSTOM)
        {
            //System.out.println("DENIED!!");
            event.setResult(Event.Result.DENY);
        }
    }
}
