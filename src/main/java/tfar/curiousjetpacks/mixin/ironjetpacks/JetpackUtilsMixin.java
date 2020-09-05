package tfar.curiousjetpacks.mixin.ironjetpacks;

import com.blakebr0.ironjetpacks.item.JetpackItem;
import com.blakebr0.ironjetpacks.util.JetpackUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.curiousjetpacks.IronJetpackUtils;

@Mixin(JetpackUtils.class)
public class JetpackUtilsMixin {
	@Inject(method = "isFlying",at = @At("HEAD"), remap = false)
	private static void modifyCheck(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
		if (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof JetpackItem) return;
		IronJetpackUtils.redirect = true;
	}
}
