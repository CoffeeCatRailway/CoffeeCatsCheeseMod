package coffeecatrailway.cheesemod.world.dimensions;

import coffeecatrailway.cheesemod.core.registries.ModBiomes;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

/**
 * @author CoffeeCatRailway
 * Created: 1/08/2019
 */
public class HamDimension extends FoodDimension {

    public HamDimension(World world, DimensionType type) {
        super(world, type, ModBiomes.HAM_RAW_FOREST_DIM, ModBiomes.HAM_RAW_PLAINS_DIM, ModBiomes.HAM_COOKED_FOREST_DIM, ModBiomes.HAM_COOKED_PLAINS_DIM);
    }
}