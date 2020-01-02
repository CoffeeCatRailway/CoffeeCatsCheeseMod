package coffeecatrailway.coffeecheese.common.entity;

import coffeecatrailway.coffeecheese.registry.ModEntities;
import coffeecatrailway.coffeecheese.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 14/09/2019
 */
public class CheeseFoodie extends FoodieEntity {

    public CheeseFoodie(World world) {
        this(ModEntities.CHEESE_FOODIE.get(), world);
    }

    public CheeseFoodie(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean breedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.CHEESE_SLICE.get();
    }

    @Override
    public boolean foodItem(ItemStack stack) {
        return stack.getItem() == ModItems.BLOCK_O_CHEESE.get();
    }

    @Override
    public FoodieEntity createChild() {
        return ModEntities.CHEESE_FOODIE.get().create(world);
    }

    @Override
    public ItemStack getDroppedItem() {
        return new ItemStack(ModItems.BLOCK_O_CHEESE.get());
    }
}