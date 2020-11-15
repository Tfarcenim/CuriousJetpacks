package tfar.curiousjetpacks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CuriousJetpacks.MODID)
public class CuriousJetpacks {
	// Directly reference a log4j logger.

	public static final String MODID = "curiousjetpacks";

	public CuriousJetpacks() {
      IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
      bus.addListener(this::comms);
      MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class,this::attachCaps);
	}

	private void comms(final FMLCommonSetupEvent event) {
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BODY.getMessageBuilder().build());
	}

	public static final ResourceLocation TAG_ID = new ResourceLocation(MODID,"jetpacks");

	public static final ITag<Item> jetpacks = ItemTags.makeWrapperTag(TAG_ID.toString());

	private void attachCaps(AttachCapabilitiesEvent<ItemStack> e) {
		ItemStack stack = e.getObject();
		if (ItemTags.getCollection().get(TAG_ID) != null && stack.getItem().isIn(jetpacks)) {
			JetpackCurio arrowCurio = new JetpackCurio(stack);
			e.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider() {
				final LazyOptional<ICurio> curio = LazyOptional.of(() -> arrowCurio);

				@Nonnull
				@Override
				public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap,
																								 @Nullable Direction side) {
					return CuriosCapability.ITEM.orEmpty(cap, curio);
				}
			});
		}
	}

}
