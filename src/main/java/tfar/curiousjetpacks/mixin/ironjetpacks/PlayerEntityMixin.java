package tfar.curiousjetpacks.mixin.ironjetpacks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.curiousjetpacks.IronJetpackUtils;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

	@Inject(method = "getItemBySlot",at = @At("INVOKE"),cancellable = true)
	private void interceptCheck(EquipmentSlotType slotIn, CallbackInfoReturnable<ItemStack> cir) {
		if (IronJetpackUtils.redirect.get()) {
			ItemStack stack = IronJetpackUtils.getJetpackCurio((PlayerEntity)(Object)this);
			cir.setReturnValue(stack);
			IronJetpackUtils.redirect.set(false);
		}
	}
}
