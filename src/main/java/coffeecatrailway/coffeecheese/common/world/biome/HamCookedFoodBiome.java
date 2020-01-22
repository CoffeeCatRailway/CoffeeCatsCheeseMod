package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModBiomes;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2020
 */
public class HamCookedFoodBiome extends BaseFoodBiome {

    public HamCookedFoodBiome(Category category) {
        super(ModBiomes.HAM_COOKED_WATER_COLOR, ModBiomes.HAM_COOKED_WATER_FOG_COLOR, ModBiomes.HAM_COOKED_GRASS_COLOR, ModBlocks.HAM_COOKED_GRASS_BLOCK.get().getDefaultState(), category);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(
                            new Feature[]{ModFeatures.HAM_RAW_TREE.get()}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.2F}, ModFeatures.HAM_COOKED_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG),
                    Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(10, 0.1f, 1)));
    }
}
