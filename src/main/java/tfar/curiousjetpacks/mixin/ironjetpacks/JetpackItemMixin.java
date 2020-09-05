package tfar.curiousjetpacks.mixin.ironjetpacks;

import com.blakebr0.ironjetpacks.item.JetpackItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.curiousjetpacks.IronJetpackUtils;

//easier than copying the code into my own class
@Mixin(JetpackItem.class)
public class JetpackItemMixin {

	@Inject(method = "onArmorTick", at = @At(value = "HEAD"),remap = false)
	private void modifyCheck(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {
		if (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof JetpackItem) return;
		IronJetpackUtils.redirect = true;
	}
}
