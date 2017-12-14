/** TODO: Read TODO.txt file! */

package com.icemetalpunk.infernaltech;

import com.icemetalpunk.infernaltech.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = InfernalTech.MODID, version = InfernalTech.VERSION)
public class InfernalTech {
	public static final String MODID = "infernaltech";
	public static final String VERSION = "1.0";

	@Instance(InfernalTech.MODID)
	public static InfernalTech instance = new InfernalTech();

	@SidedProxy(clientSide = "com.icemetalpunk.infernaltech.proxy.ClientProxy", serverSide = "com.icemetalpunk.infernaltech.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tab = new CreativeTabs("infernaltech") {

		@Override
		public ItemStack getTabIconItem() {
			// TODO: Change item icon.
			return new ItemStack(Item.getItemFromBlock(Blocks.NETHER_BRICK));
		}

	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
