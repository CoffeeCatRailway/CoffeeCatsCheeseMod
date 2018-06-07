package coffeecatteam.cheesemod.objects.items.base;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.item.ItemFood;
import net.minecraftforge.oredict.OreDictionary;

public class ItemBaseFood extends ItemFood implements IOreDict {
	
	private String oreDict;

	public ItemBaseFood(String name, String oreDict, int amount, boolean isWolfFood) {
		super(amount, isWolfFood);
		this.oreDict = oreDict;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEFOODSTAB);
  	}

	@Override
	public String registerOre() {
		return oreDict;
	}
}