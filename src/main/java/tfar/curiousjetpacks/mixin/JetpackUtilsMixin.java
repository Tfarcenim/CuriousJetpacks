package tfar.curiousjetpacks.mixin;

import com.blakebr0.ironjetpacks.item.JetpackItem;
import com.blakebr0.ironjetpacks.util.JetpackUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tfar.curiousjetpacks.Utils;

@Mixin(JetpackUtils.class)
public class JetpackUtilsMixin {
	@ModifyVariable(method = "isFlying",
					at = @At(value = "INVOKE_ASSIGN",
									target = "net/minecraft/entity/player/PlayerEntity.getItemStackFromSlot(Lnet/minecraft/inventory/EquipmentSlotType;)Lnet/minecraft/item/ItemStack;")
									,remap = false)
	private static ItemStack modifyCheck(ItemStack original,PlayerEntity player) {
		if (original.getItem() instanceof JetpackItem) return original;
		return Utils.getJetpackCurio(original, player);
	}
}
