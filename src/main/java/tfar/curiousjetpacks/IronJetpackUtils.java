package tfar.curiousjetpacks;

import com.blakebr0.ironjetpacks.item.JetpackItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

public class IronJetpackUtils {

	public static ThreadLocal<Boolean> redirect = ThreadLocal.withInitial(() -> false);

	public static ItemStack getJetpackCurio(ItemStack original, LivingEntity playerEntity) {
		ItemStack stack1 = CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof JetpackItem, playerEntity)
						.map(stringIntegerItemStackImmutableTriple -> stringIntegerItemStackImmutableTriple.right).orElse(original);
		return stack1;
	}

	public static ItemStack getJetpackCurio(LivingEntity playerEntity) {
		return getJetpackCurio(ItemStack.EMPTY,playerEntity);
	}
}
