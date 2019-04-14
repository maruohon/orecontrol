package fi.dy.masa.orecontrol.util;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class GenerationUtils
{
    public static void generateUndergroundLavaLakes(World world, int chunkX, int chunkZ, Random rand)
    {
        int x = (chunkX << 4) + rand.nextInt(16) + 8;
        int y = rand.nextInt(rand.nextInt(248) + 8);
        int z = (chunkZ << 4) + rand.nextInt(16) + 8;

        if (y < world.getSeaLevel() && y < (world.getHeight(x, z) - 8))
        {
            (new WorldGenLakes(Blocks.LAVA)).generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
