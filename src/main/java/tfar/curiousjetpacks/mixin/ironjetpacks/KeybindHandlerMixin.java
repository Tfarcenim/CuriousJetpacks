package tfar.curiousjetpacks.mixin.ironjetpacks;

import com.blakebr0.ironjetpacks.handler.KeybindHandler;
import com.blakebr0.ironjetpacks.item.JetpackItem;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.curiousjetpacks.IronJetpackUtils;

@Mixin(KeybindHandler.class)
public class KeybindHandlerMixin {

	@Inject(method = {"onKeyInput","onMouseInput"},
					at = @At(value = "HEAD"),remap = false)
	private void modifyCheck(CallbackInfo ci) {
		if (Minecraft.getInstance().player == null) return;
		if (Minecraft.getInstance().player.getItemBySlot(EquipmentSlotType.CHEST).getItem() instanceof JetpackItem) return;
		IronJetpackUtils.redirect.set(true);
	}
}
