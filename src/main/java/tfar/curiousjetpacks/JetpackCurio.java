package tfar.curiousjetpacks;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class JetpackCurio implements ICurio {

	protected final ItemStack jet;

	public JetpackCurio(ItemStack jet) {
		this.jet = jet;
	}

	@Override
	public boolean canRightClickEquip() {
		return true;
	}

	@Override
	public void curioTick(String identifier, int index, LivingEntity livingEntity) {
		if (livingEntity instanceof PlayerEntity) {
			jet.onArmorTick(livingEntity.world, (PlayerEntity)livingEntity);
		}
	}
}
