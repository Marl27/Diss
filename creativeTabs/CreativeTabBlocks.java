package Diss.creativeTabs;

import Diss.blocks.AllBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabBlocks extends CreativeTabs{

	public CreativeTabBlocks(String lable) {
		super(lable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item getTabIconItem() {
		
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(AllBlocks.castleBlock);
	}

	

}
