package tfar.curiousjetpacks;

import net.minecraftforge.fml.loading.FMLLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {
	@Override
	public void onLoad(String mixinPackage) {

	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		String[] modids = new String[]{"simplyjetpacks","ironjetpacks"};
		for (String modid : modids) {
			if (mixinClassName.contains(modid) && isLoaded(modid)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isLoaded(String modid) {
		boolean isLoaded = FMLLoader.getLoadingModList()
				.getModFiles().stream()
				.anyMatch(modFileInfo -> modFileInfo.getMods().stream()
				.anyMatch(iModInfo -> iModInfo.getModId().equals(modid)));
		//System.out.println(modid+" : "+isLoaded);
		return isLoaded;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}
}
