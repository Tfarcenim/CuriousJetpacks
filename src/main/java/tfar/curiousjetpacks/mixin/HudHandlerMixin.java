package tfar.curiousjetpacks.mixin;

import com.blakebr0.ironjetpacks.handler.HudHandler;
import com.blakebr0.ironjetpacks.item.JetpackItem;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.curiousjetpacks.Utils;

@Mixin(HudHandler.class)
public class HudHandlerMixin {
	@Redirect(method = "onRenderGameOverlay", at = @At(value = "INVOKE",
					target = "net/minecraft/client/entity/player/ClientPlayerEntity.getItemStackFromSlot(Lnet/minecraft/inventory/EquipmentSlotType;)Lnet/minecraft/item/ItemStack;")
					,remap = false)
	private ItemStack modifyCheck(ClientPlayerEntity clientPlayerEntity, EquipmentSlotType slotIn) {
		ItemStack original = clientPlayerEntity.getItemStackFromSlot(slotIn);
		if (original.getItem() instanceof JetpackItem) return original;
		return Utils.getJetpackCurio(original,clientPlayerEntity);
	}
}
