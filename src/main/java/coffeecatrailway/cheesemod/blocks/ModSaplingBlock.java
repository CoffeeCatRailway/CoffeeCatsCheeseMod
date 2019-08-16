package coffeecatrailway.cheesemod.blocks;

import coffeecatrailway.cheesemod.core.registries.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * @author CoffeeCatRailway
 * Created: 27/07/2019
 */
public class ModSaplingBlock extends SaplingBlock {

    public ModSaplingBlock(Tree tree, Properties properties) {
        super(tree, properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == ModBlocks.CHEESE_GRASS_BLOCK ||
                block == ModBlocks.GRILLED_CHEESE_GRASS_BLOCK ||
                block == ModBlocks.HAM_RAW_GRASS_BLOCK ||
                block == ModBlocks.HAM_COOKED_GRASS_BLOCK ||
                super.isValidGround(state, worldIn, pos);
    }
}