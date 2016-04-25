package Diss.teleport;

import java.awt.event.KeyEvent;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TeleportHome extends Block{

	public TeleportHome(Material material) {
		super(material);
		this.setBlockUnbreakable();
		this.setResistance(2000.0F);
		//this.setHardness(0.5F);
		this.setBlockName("block");
		this.setLightLevel(1.0F);
		//this.onBlockAdded(null,null,null,null);
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
    public void onEntityCollidedWithBlock (World world, int x, int y, int z, Entity entity)
    {
		if(entity instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer)entity;
			KeyEvent event = null;
			
			if (org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_NUMPAD1)){
				
				int Y = y-100;
				for(int i = y - 2; i > Y; i-- ){
				
					if (world.blockExists(x, i, z)){
						System.out.println("**********************************************");
						System.out.println("**********************************************");
						System.out.println("**********************************************");
						System.out.println(world.blockExists(x, i, z));
						System.out.println(world.getBlock(x, i, z));
						System.out.println(world.getBlock(x, i, z) == Block.getBlockFromName("TeleportHome"));
						System.out.println("**********************************************");
						System.out.println("**********************************************");
						System.out.println("**********************************************");

						if (world.getBlock(x, i, z) != this.getBlockFromName("TeleportHome")) { 
							//player.setPositionAndUpdate(x + 0.5d, i + 1, z + 0.5d);
							player.setPositionAndUpdate(7.1d , 240.500d, 7.5d);
							world.playSoundAtEntity(player, "mob.endermen.portal", 1.0f, 1.0f);	

							break;	
						}
					}
				}
			}
			/*else if (org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_NUMPAD0)){
			int Y= y + 100; 
			for(int i = y + 2; i < Y; i++ ){
				
				if (world.blockExists(x, i, z)){
					if (world.getBlock(x, i, z) != this.getBlockFromName("TeleportBlock")){
						player.setPositionAndUpdate(x + 0.5d, i + 1, z + 0.5d);
						 world.playSoundAtEntity(player, "mob.endermen.portal", 1.0f, 1.0f);	
						player.motionY = 0;
						
						break;
						
					}

				}
				}	
			}*/
		}
    }

	
	
	
	
	
	/*@Override
    public void onEntityCollidedWithBlock (World world, int x, int y, int z, Entity entity)
    {
		if(entity instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer)entity;
			KeyEvent event = null;
			
			if (org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_NUMPAD1)){
				
				int Y = y-100;
				for(int i = y - 2; i > Y; i-- ){
				
					if (world.blockExists(x, i, z)){
						System.out.println("**********************************************");
						System.out.println("**********************************************");
						System.out.println("**********************************************");
						System.out.println(world.getBlock(x, i, z));
						System.out.println(world.getBlock(x, i, z) == Block.getBlockFromName("TeleportBlock"));
						System.out.println("**********************************************");
						System.out.println("**********************************************");
						System.out.println("**********************************************");

						if (world.getBlock(x, i, z) != this.getBlockFromName("TeleportBlock")) { 
							player.setPositionAndUpdate(x + 0.5d, i + 1, z + 0.5d);
							world.playSoundAtEntity(player, "mob.endermen.portal", 1.0f, 1.0f);	

							break;	
						}
					}
				}
			}
			else if (org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_NUMPAD0)){
			int Y= y + 100; 
			for(int i = y + 2; i < Y; i++ ){
				
				if (world.blockExists(x, i, z)){
					if (world.getBlock(x, i, z) != this.getBlockFromName("TeleportBlock")){
						player.setPositionAndUpdate(x + 0.5d, i + 1, z + 0.5d);
						 world.playSoundAtEntity(player, "mob.endermen.portal", 1.0f, 1.0f);	
						player.motionY = 0;
						
						break;
						
					}

				}
				}	
			}
		}
    }
*/
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x, int y, int z)
    {
        return AxisAlignedBB.getBoundingBox(x, y, z, (double) x + 1.0D, (double) y + 0.9D, (double) z + 1.0D);
    }
}
