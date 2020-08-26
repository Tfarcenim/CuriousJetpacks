package tfar.curiousjetpacks.mixin;

import com.blakebr0.ironjetpacks.item.JetpackItem;
import com.blakebr0.ironjetpacks.util.JetpackUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tfar.curiousjetpacks.Utils;

@Mixin(JetpackUtils.class)
public class JetpackUtilsMixin {
	@ModifyVariable(method = "isFlying", at = @At(value = "INVOKE",target = "net/minecraft/item/ItemStack.getItem()Lnet/minecraft/item/Item;",ordinal = 0),remap = false)
	private static ItemStack modifyCheck(ItemStack original,PlayerEntity player) {
		if (original.getItem() instanceof JetpackItem) return original;
		return Utils.getJetpackCurio(original,player);
	}
}
