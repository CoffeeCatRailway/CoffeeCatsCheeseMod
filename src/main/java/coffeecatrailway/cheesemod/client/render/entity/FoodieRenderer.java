package coffeecatrailway.cheesemod.client.render.entity;

import coffeecatrailway.cheesemod.client.render.entity.model.FoodieModel;
import coffeecatrailway.cheesemod.common.entity.FoodieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 13/09/2019
 */
@OnlyIn(Dist.CLIENT)
public class FoodieRenderer<E extends FoodieEntity> extends MobRenderer<E, FoodieModel<E>> {

    private final ResourceLocation textures;

    public FoodieRenderer(EntityRendererManager manager, ResourceLocation textures) {
        super(manager, new FoodieModel<E>(), 0.5f);
        this.textures = textures;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(E entity) {
        return this.textures;
    }
}
