package Diss.blocks;

import Diss.teleport.TeleportMaths;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CastleBlock extends Block {
	
	public CastleBlock(Material material) {
		super(material);
		// TODO Auto-generated constructor stub
	}

	public int currentX, currentY, currentZ;
	public int[] position  = {currentX, currentY, currentZ};
	public TeleportMaths teleportMaths;
	
	
	public void onBlockAdded(World w,int x,int y,int z) {
		currentX = x;
		currentY = y;
		currentZ = z;
		
		Block temp = w.getBlock(x+1, y, z);
		//System.out.println("Hardness -"+temp.getBlockHardness(w, x, y, z));
		temp = helpUnbreakble(temp);
		w.setBlock(x+1, y, z, temp);
		
/*//		System.out.println("******************************************");
//		System.out.println("******************************************");
//		System.out.println(w.blockExists(x, y, z));
//		System.out.println(x+"-" + "-"+ y+ "-" + "-"+z+"-");
//		System.out.println("******************************************");
*/		this.unbreakableCastle(w);
	}
	
	public void unbreakableCastle(World w){
//		System.out.println("******************************************");
//		System.out.println("******************************************");
//		System.out.println("******************************************");
//		System.out.println("currentX -"+currentX +" currentY-"+currentY +" currentZ-"+ currentZ);
//	
//		System.out.println("Outside while");
		
		
		for(int i = currentX; i<=currentX+2; i++ ){
			for(int j = currentY; j<=currentY+2; j++ ){
				for(int k = currentZ; k<=currentZ+2; k++ ){	
//					System.out.println("******************************************");
//					System.out.println("currentX -"+i+" currentY-"+j+" currentZ-"+ k);

					Block temp = w.getBlock(i, j, k);
					//System.out.println("Hardness -"+temp.getBlockHardness(w, i, j, k));
					temp = helpUnbreakble(temp);
					w.setBlock(i, j, k, Blocks.bedrock);
					
					
				}
			}
		}
		
	}
	
	public Block helpUnbreakble(Block b){
		b.setBlockUnbreakable();
		b.setResistance(2000.0F);
		b.setLightLevel(1.0F);
		return b;
	
	}
	
}
