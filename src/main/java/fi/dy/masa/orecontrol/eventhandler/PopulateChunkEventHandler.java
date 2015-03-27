package fi.dy.masa.orecontrol.eventhandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import fi.dy.masa.orecontrol.config.Configs;

public class PopulateChunkEventHandler
{
    @SubscribeEvent
    public void onPopulateChunkEvent(PopulateChunkEvent.Post event)
    {
        if (Configs.disableEmerald == true || Configs.disableMonsterEgg == true)
        {
            World world = event.world;
            int endX = (event.chunkX * 16) + 16;
            int endZ = (event.chunkZ * 16) + 16;

            // Get the highest possibly existing block location
            int maxY = event.chunkProvider.provideChunk(event.chunkX, event.chunkZ).getTopFilledSegment() + 15;

            for (int x = event.chunkX * 16; x < endX; ++x)
            {
                for (int z = event.chunkZ * 16; z < endZ; ++z)
                {
                    for (int y = 0; y < maxY; ++y)
                    {
                        BlockPos pos = new BlockPos(x, y, z);
                        IBlockState iBlockState = world.getBlockState(pos);

                        if ((Configs.disableEmerald == true && iBlockState.getBlock() == Blocks.emerald_ore)
                            || (Configs.disableMonsterEgg == true && iBlockState.getBlock() == Blocks.monster_egg))
                        {
                            world.setBlockState(pos, Blocks.stone.getDefaultState(), 2);
                        }
                    }
                }
            }
        }
    }
}
