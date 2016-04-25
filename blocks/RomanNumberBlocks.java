package Diss.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class RomanNumberBlocks extends Block{

	public AllBlocks allBlocks;
	public int value=0;
	
	public RomanNumberBlocks(Material material) {
		super(material);
		//this.setBlockUnbreakable();
		//this.setResistance(2000.0F);
		// TODO Auto-generated constructor stub
	}
	
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
	/*public void blockValue(Block[] name, int[] value){
		for(int i = 0; i<allBlocks.blockArray.length;i++){
			allBlocks.blockArray[i]=  allBlocks.blockValue[i];
			
		}
	}*/
	
	
}
