package tfar.curiousjetpacks.mixin;

import com.blakebr0.ironjetpacks.handler.JetpackClientHandler;
import com.blakebr0.ironjetpacks.item.JetpackItem;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tfar.curiousjetpacks.Utils;

//easier than copying the code into my own class
@Mixin(JetpackClientHandler.class)
public class JetpackClientHandlerMixin {

	@ModifyVariable(method = "onClientTick",
					at = @At(value = "INVOKE_ASSIGN",
									target = "net/minecraft/client/entity/player/ClientPlayerEntity.getItemStackFromSlot(Lnet/minecraft/inventory/EquipmentSlotType;)Lnet/minecraft/item/ItemStack;",
									ordinal = 0),remap = false)
	private ItemStack modifyCheck(ItemStack original) {
		if (original.getItem() instanceof JetpackItem) return original;
		return Utils.getJetpackCurio(original, Minecraft.getInstance().player);
	}
}
