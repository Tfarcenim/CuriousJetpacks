package tfar.curiousjetpacks.mixin.ironjetpacks;

import com.blakebr0.ironjetpacks.item.JetpackItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Shadow @Final public PlayerEntity player;

    @Shadow public abstract ItemStack getItem(int p_70301_1_);

    @Inject(method = "setItem",at = @At("HEAD"))
    private void logJetpack(int p_70299_1_, ItemStack p_70299_2_, CallbackInfo ci) {
        if (p_70299_2_.getItem() instanceof JetpackItem) {
            ItemStack stack = getItem(p_70299_1_);
            System.out.println("Old Item: "+stack.getItem() +" NBT:"+stack.getTag());
            System.out.println("Clientside: "+player.level.isClientSide);
            new Throwable().printStackTrace();
        }
    }
}
