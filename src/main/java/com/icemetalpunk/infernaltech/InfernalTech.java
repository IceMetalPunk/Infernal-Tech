/** TODO: Read TODO.txt file! */

package com.icemetalpunk.infernaltech;

import com.icemetalpunk.infernaltech.gui.InfernalTechGuiHandler;
import com.icemetalpunk.infernaltech.proxy.Proxy;
import com.icemetalpunk.infernaltech.registries.BlockRegistry;
import com.icemetalpunk.infernaltech.registries.ItemRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = InfernalTech.MODID, version = InfernalTech.VERSION)
public class InfernalTech {
	public static final String MODID = "infernaltech";
	public static final String VERSION = "1.0";

	@Instance(InfernalTech.MODID)
	public static InfernalTech instance = new InfernalTech();

	@SidedProxy(clientSide = "com.icemetalpunk.infernaltech.proxy.ClientProxy", serverSide = "com.icemetalpunk.infernaltech.proxy.ServerProxy")
	public static Proxy proxy;

	public static CreativeTabs tab = new CreativeTabs("infernaltech") {

		@Override
		public ItemStack getTabIconItem() {
			// TODO: Change item icon.
			return new ItemStack(InfernalTech.blocks.get("hellfire_smeltery_tier_3"));
		}

	};

	public static BlockRegistry blocks = new BlockRegistry();
	public static ItemRegistry items = new ItemRegistry();

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(InfernalTech.instance, new InfernalTechGuiHandler());
	}
}
