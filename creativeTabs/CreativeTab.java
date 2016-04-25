package Diss.creativeTabs;

import Diss.blocks.AllBlocks;
import Diss.items.AllItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTab {
	
	/*public static CreativeTabs blocks = new CreativeTabs("blocks"){
		@Override
		public Item getTabIconItem(){
			return new ItemStack(AllBlocks.teleportMaths).getItem();
		}
	};
	
	public static CreativeTabs weapon = new CreativeTabs("weapon"){
		@Override
		public Item getTabIconItem(){
			return new ItemStack(AllItems.romanSword).getItem();
		}
	};*/

	public static CreativeTabs blocks;
	public static CreativeTabs weapon;
	
	public static void registerToMain(){
		blocks = new CreativeTabBlocks("blocks");
		weapon = new CreativeTabItem("weapon");
	}
	
	

}
