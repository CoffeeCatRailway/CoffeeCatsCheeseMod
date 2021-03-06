package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 27/07/2019
 */
public class HamTreeFeature extends TreeFeature {

    private final BlockState trunk;
    private final BlockState leaf;
    private final BlockState food;

    public HamTreeFeature(boolean cooked, Function<Dynamic<?>, ? extends FoodTreeFeatureConfig> function) {
        super(function);
        if (cooked) {
            this.trunk = ModBlocks.HAM_COOKED_LOG.get().getDefaultState();
            this.leaf = ModBlocks.HAM_COOKED_LEAVES.get().getDefaultState();
            this.food = ModBlocks.HAM_COOKED_BLOCK.get().getDefaultState();
        } else {
            this.trunk = ModBlocks.HAM_RAW_LOG.get().getDefaultState();
            this.leaf = ModBlocks.HAM_RAW_LEAVES.get().getDefaultState();
            this.food = ModBlocks.HAM_RAW_BLOCK.get().getDefaultState();
        }
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader world, Random rand, BlockPos position, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, FoodTreeFeatureConfig config) {
        int i = rand.nextInt(4) + 6;
        int j = 1 + rand.nextInt(2);
        int k = i - j;
        int l = 2 + rand.nextInt(3);
        boolean flag = true;
        if (position.getY() >= 1 && position.getY() + i + 1 <= world.getMaxHeight()) {
            for (int i1 = position.getY(); i1 <= position.getY() + 1 + i && flag; ++i1) {
                int j1;
                if (i1 - position.getY() < j)
                    j1 = 0;
                else
                    j1 = l;

                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

                for (int k1 = position.getX() - j1; k1 <= position.getX() + j1 && flag; ++k1) {
                    for (int l1 = position.getZ() - j1; l1 <= position.getZ() + j1 && flag; ++l1) {
                        if (i1 >= 0 && i1 < world.getMaxHeight()) {
                            blockpos$mutableblockpos.setPos(k1, i1, l1);
                            if (!isAirOrLeaves(world, blockpos$mutableblockpos))
                                flag = false;
                        } else
                            flag = false;
                    }
                }
            }

            if (!flag)
                return false;
            else if (isSoil(world, position.down()) && position.getY() < world.getMaxHeight() - i - 1) {
                this.setDirtAt(world, position.down(), position);
                int i3 = rand.nextInt(3);
                int j3 = 1;
                int k3 = 0;

                for (int l3 = 0; l3 <= k; ++l3) {
                    int j4 = position.getY() + i - l3;

                    for (int i2 = position.getX() - i3; i2 <= position.getX() + i3; ++i2) {
                        int j2 = i2 - position.getX();

                        for (int k2 = position.getZ() - i3; k2 <= position.getZ() + i3; ++k2) {
                            int l2 = k2 - position.getZ();
                            if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= rand.nextInt(3)) {
                                BlockPos blockpos = new BlockPos(i2, j4, k2);
                                if (isAirOrLeaves(world, blockpos) || isSoil(world, blockpos))
                                    this.func_227219_b_(world, rand, blockpos, leavesPos, boundingBox, config);
                            }
                        }
                    }

                    if (i3 >= j3) {
                        i3 = k3;
                        k3 = 1;
                        ++j3;
                        if (j3 > l)
                            j3 = l;
                    } else
                        ++i3;
                }

                int i4 = rand.nextInt(3);

                for (int k4 = 0; k4 < i - i4; ++k4)
                    if (isAirOrLeaves(world, position.up(k4)))
                        this.func_227216_a_(world, rand, position.up(k4), logPos, boundingBox, config);

                return true;
            } else
                return false;
        } else
            return false;
    }
}
