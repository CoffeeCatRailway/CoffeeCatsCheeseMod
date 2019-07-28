package coffeecatrailway.cheesemod.items;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.client.model.ModelCheeseSuit;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 26/07/2019
 */
public class CheeseSuitArmorItem extends ArmorItem {

    public CheeseSuitArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (slot != EquipmentSlotType.LEGS)
            return CheeseMod.MOD_ID + ":textures/models/armor/cheese_suit.png";
        else
            return "";
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        if (!itemStack.isEmpty())
            if (itemStack.getItem() instanceof CheeseSuitArmorItem) {
                BipedModel armorModel = new ModelCheeseSuit(1.0f, armorSlot);

                armorModel.bipedHeadwear.showModel = armorSlot == EquipmentSlotType.HEAD;

                armorModel.bipedBody.showModel = armorSlot == EquipmentSlotType.CHEST;
                armorModel.bipedRightArm.showModel = armorSlot == EquipmentSlotType.CHEST;
                armorModel.bipedLeftArm.showModel = armorSlot == EquipmentSlotType.CHEST;

                armorModel.isSneak = _default.isSneak;
                armorModel.isChild = _default.isChild;

                return (A) armorModel;
            }
        return null;
    }
}
