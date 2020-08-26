package tfar.curiousjetpacks.mixin;

import com.blakebr0.ironjetpacks.item.JetpackItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.curiousjetpacks.Utils;

@Mixin(JetpackItem.class)
public class JetpackItemMixin {
	@Redirect(method = "onArmorTick", at = @At(value = "INVOKE",
					target = "net/minecraft/entity/player/PlayerEntity.getItemStackFromSlot(Lnet/minecraft/inventory/EquipmentSlotType;)Lnet/minecraft/item/ItemStack;")
					,remap = false)
	private ItemStack modifyCheck(PlayerEntity playerEntity, EquipmentSlotType slotIn) {
		ItemStack original = playerEntity.getItemStackFromSlot(slotIn);
		if (original.getItem() instanceof JetpackItem) return original;
		return Utils.getJetpackCurio(original,playerEntity);
	}
}
