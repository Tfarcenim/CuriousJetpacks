package tfar.curiousjetpacks.mixin.ironjetpacks;

import com.blakebr0.ironjetpacks.handler.HudHandler;
import com.blakebr0.ironjetpacks.item.JetpackItem;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.curiousjetpacks.IronJetpackUtils;

@Mixin(HudHandler.class)
public class HudHandlerMixin {
	@Inject(method = "onRenderGameOverlay", at = @At(value = "FIELD",
			target = "Lnet/minecraft/inventory/EquipmentSlotType;CHEST:Lnet/minecraft/inventory/EquipmentSlotType;")
					,remap = false)
	private void modifyCheck(RenderGameOverlayEvent.Post event, CallbackInfo ci) {
		ItemStack original = Minecraft.getInstance().player.getItemBySlot(EquipmentSlotType.CHEST);
		if (original.getItem() instanceof JetpackItem) return;
		IronJetpackUtils.redirect = true;
	}
}
