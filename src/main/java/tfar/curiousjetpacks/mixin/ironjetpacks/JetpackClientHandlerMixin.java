package tfar.curiousjetpacks.mixin.ironjetpacks;

import com.blakebr0.ironjetpacks.handler.JetpackClientHandler;
import com.blakebr0.ironjetpacks.item.JetpackItem;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.curiousjetpacks.IronJetpackUtils;

//easier than copying the code into my own class
@Mixin(JetpackClientHandler.class)
public class JetpackClientHandlerMixin {

	@Inject(method = "onClientTick", at = @At(value = "HEAD"),remap = false)
	private void modifyCheck(TickEvent.ClientTickEvent event, CallbackInfo ci) {
		if (Minecraft.getInstance().player == null)return;
		ItemStack original = Minecraft.getInstance().player.getItemBySlot(EquipmentSlotType.CHEST);
		if (original.getItem() instanceof JetpackItem) return;
		IronJetpackUtils.redirect.set(true);
	}
}
