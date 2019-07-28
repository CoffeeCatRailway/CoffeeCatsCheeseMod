package coffeecatrailway.cheesemod;

import coffeecatrailway.cheesemod.CheeseMod;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

/**
 * @author CoffeeCatRailway
 * Created: 20/07/2019
 */
@Mod.EventBusSubscriber(modid = CheeseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfig {

    public static final String FILE = CheeseMod.MOD_ID + ".toml";

    // Config
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    /// Properties ///
    public static ForgeConfigSpec.BooleanValue playerLoggedIn;
    public static ForgeConfigSpec.BooleanValue stickyFoodBlock;
    static {
        playerLoggedIn = BUILDER.comment("If false it means the player has not logged in before")
                .define(CheeseMod.MOD_ID + ".loggedIn", false);

        stickyFoodBlock = BUILDER.comment("If true all food blocks (cheese, grilled cheese & ham raw or cooked) will act like slime blocks when pushed by a piston")
                .define(CheeseMod.MOD_ID + ".stickyFoodBlock", true);
    }

    public static final Modifiers MODIFIERS = new Modifiers(BUILDER);
    public static final Biome BIOME = new Biome(BUILDER);

    public static final ForgeConfigSpec CONFIG = BUILDER.build();

    public static class Modifiers {

        public ForgeConfigSpec.DoubleValue grilledFoodMultiplier;

        public ForgeConfigSpec.IntValue grillSpeedMultiplier;
        public ForgeConfigSpec.IntValue crackerMakerSpeedMultiplier;

        public ForgeConfigSpec.DoubleValue cheeseSuitScale;
        public ForgeConfigSpec.DoubleValue cheeseSuitBindingScale;

        public ForgeConfigSpec.DoubleValue hamSuitScale;
        public ForgeConfigSpec.DoubleValue hamSuitBindingScale;

        public Modifiers(ForgeConfigSpec.Builder config) {
            config.comment("CheeseMod modifier settings");

            grilledFoodMultiplier = config.comment("The amount of how much the saturation changes when grilled")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.grilledSaturation", 1.5d, 0.5d, 10.0d);

            int foodMakerMin = 1;
            int foodMakerMax = 20;
            grillSpeedMultiplier = config.comment("Speed multiplier for the grill")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.grillSpeed", 5, foodMakerMin, foodMakerMax);

            crackerMakerSpeedMultiplier = config.comment("Speed multiplier for the cracker maker")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.crackerMakerSpeed", 5, foodMakerMin, foodMakerMax);

            double minScale = (double) 0.0f;
            double maxScale = (double) 10.0f;
            cheeseSuitScale = config.comment("Cheese suit scale")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.cheeseSuitScale", 0.4d, minScale, maxScale);

            cheeseSuitBindingScale = config.comment("Cheese suit binding scale")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.cheeseSuitBindingScale", 0.3d, minScale, maxScale);

            hamSuitScale = config.comment("Ham suit scale")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.hamSuitScale", 0.6d, minScale, maxScale);

            hamSuitBindingScale = config.comment("Ham suit binding scale")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.hamSuitBindingScale", 0.3d, minScale, maxScale);
        }
    }

    public static class Biome {

        public ForgeConfigSpec.BooleanValue spawnCheeseVillager;
        public ForgeConfigSpec.IntValue cheeseVillagerWeight;

        public ForgeConfigSpec.BooleanValue spawnHamVillager;
        public ForgeConfigSpec.IntValue hamVillagerWeight;

        public ForgeConfigSpec.BooleanValue spawnCheeseGolem;
        public ForgeConfigSpec.IntValue cheeseGolemWeight;

        public ForgeConfigSpec.BooleanValue spawnHamGolem;
        public ForgeConfigSpec.IntValue hamGolemWeight;

        public Biome(ForgeConfigSpec.Builder config) {
            config.comment("CheeseMod biome settings");

            int minWeight = 1;
            int maxWeight = 20;

            config.comment("- Food villager");

            spawnCheeseVillager = config.comment("Allows the cheese villager to spawn in the cheese biome")
                    .define(CheeseMod.MOD_ID + ".biome.spawnCheeseVillager", true);

            cheeseVillagerWeight = config.comment("The weight/rate of cheese villager spawns")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.cheeseVillagerWeight", 1, minWeight, maxWeight);

            spawnHamVillager = config.comment("Allows the ham villager to spawn in the ham biome")
                    .define(CheeseMod.MOD_ID + ".biome.spawnHamVillager", true);

            hamVillagerWeight = config.comment("The weight/rate of ham villager spawns")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.hamVillagerWeight", 1, minWeight, maxWeight);

            config.comment("- Food golem");

            spawnCheeseGolem = config.comment("Allows the cheese golem to spawn in the cheese biome")
                    .define(CheeseMod.MOD_ID + ".biome.spawnCheeseGolem", true);

            cheeseGolemWeight = config.comment("The weight/rate of cheese golem spawns")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.cheeseGolemWeight", 1, minWeight, maxWeight);

            spawnHamGolem = config.comment("Allows the ham golem to spawn in the ham biome")
                    .define(CheeseMod.MOD_ID + ".biome.spawnHamGolem", true);

            hamGolemWeight = config.comment("The weight/rate of ham golem spawns")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.hamGolemWeight", 1, minWeight, maxWeight);
        }
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CheeseMod.LOGGER.info("Config: " + path);
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        CheeseMod.LOGGER.info("Config built: " + path);
        file.load();
        CheeseMod.LOGGER.info("Loaded Config: " + path);
        config.setConfig(file);
    }
}
