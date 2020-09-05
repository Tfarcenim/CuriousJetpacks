package tfar.curiousjetpacks.mixin;

import net.minecraftforge.fml.ModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.util.List;
import java.util.Locale;
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
		boolean[] loaded = new boolean[]{isLoaded(modids[0]),isLoaded(modids[1])};
		for (int i = 0; i < modids.length; i++) {
			if (targetClassName.contains(modids[i]) && loaded[i]) {
				return true;
			}
		}
		return false;
	}

	public static boolean isLoaded(String modid) {
		try {
			File mods = new File("./mods");
			File[] files = mods.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.getName().toLowerCase(Locale.ROOT).startsWith(modid)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
