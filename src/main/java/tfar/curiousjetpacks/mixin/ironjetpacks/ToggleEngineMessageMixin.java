package tfar.curiousjetpacks.mixin.ironjetpacks;

import com.blakebr0.ironjetpacks.item.JetpackItem;
import com.blakebr0.ironjetpacks.network.message.ToggleEngineMessage;
import com.blakebr0.ironjetpacks.util.JetpackUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.NetworkEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.curiousjetpacks.IronJetpackUtils;

import java.util.function.Supplier;

@Mixin(ToggleEngineMessage.class)
public class ToggleEngineMessageMixin {

	@Inject(method = "onMessage",at = @At(value = "HEAD"),remap = false)
	private static void modifyCheck(ToggleEngineMessage message, Supplier<NetworkEvent.Context> context, CallbackInfo ci) {
		context.get().enqueueWork(() -> {
			ServerPlayerEntity player = context.get().getSender();
			if (player != null) {
				ItemStack stack = IronJetpackUtils.getJetpackCurio(player);
				Item item = stack.getItem();
				if (item instanceof JetpackItem) {
					JetpackUtils.toggleEngine(stack);
				}
			}
		});
	}
}
