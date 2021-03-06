package coffeecatrailway.coffeecheese.common.item.crafting;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class GrillRecipe extends AbstractCookingRecipe {

    private final ItemStack result;
    private final int oil;

    public GrillRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, float experience, int cookTime, int oil) {
        super(ModRecipes.GRILLING, id, "", ingredient, result, experience, cookTime);
        this.result = result;
        this.oil = oil;
    }

    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.GRILL.get());
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.GRILLING_SERIALIZER.get();
    }

    public ItemStack getResult() {
        return result;
    }

    public int getOil() {
        return oil;
    }
}
