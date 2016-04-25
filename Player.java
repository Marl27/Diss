package Diss;
import com.mojang.authlib.GameProfile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;



public class Player {//extends EntityPlayer{
	
	public Player (){//(World world, GameProfile profile) {
//		super(world, profile);
//		
//		ChunkCoordinates newspawn=new ChunkCoordinates(0,60,0);
//		boolean p_71063_2_=true;
//		this.setSpawnChunk(newspawn, p_71063_2_);
//		System.out.println("Player working");
	}

	public void Playerstart(World world, Entity entity){
		//world
		if(entity instanceof EntityPlayer){
		
			EntityPlayer player = (EntityPlayer)entity;
			player.setPositionAndUpdate(446.5d, 4, 593.5d);
			ChunkCoordinates newspawn=new ChunkCoordinates(100,10,10);
			boolean p_71063_2_=true;
			player.setSpawnChunk(newspawn, p_71063_2_);
			
			System.out.println("Player working");
			//player.setSpawnChunk(chunkCoordinates, forced, dimension)
			//player.setSpawnPoint();
			//player.posX()
		}
	}

//	@Override
//	public void addChatMessage(IChatComponent chat) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public ChunkCoordinates getPlayerCoordinates() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
