package tfar.curiousjetpacks;

import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.UUID;

public class JetpackCurio implements ICurio {

	protected final ItemStack jet;

	public JetpackCurio(ItemStack jet) {
		this.jet = jet;
	}

	@Override
	public boolean canRightClickEquip() {
		return true;
	}

	@Override
	public void curioTick(String identifier, int index, LivingEntity livingEntity) {
		if (livingEntity instanceof PlayerEntity) {
			jet.onArmorTick(livingEntity.level, (PlayerEntity)livingEntity);
		}
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
		return jet.getAttributeModifiers(EquipmentSlotType.CHEST);
	}
}
