package Diss.gen;

import java.util.List;
import java.util.Random;

import Diss.blocks.AllBlocks;
import Diss.main.FirstMod;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.ForgeModContainer;
import cpw.mods.fml.common.IWorldGenerator;

public class OreGen implements IWorldGenerator {

	public static boolean structureAlreadySpawned = false;
	public FirstMod fm = new FirstMod();
	StructureGeneratorData SGD = new StructureGeneratorData("Himalya");



	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		
		/* check structureAlreadySpawned data from world data 
		 *	if the value is true do nothing 
		 *	if false call generate overworld and 
		 *	save structureAlreadySpawned to be true in the world data.*/
		
		/*StructureGeneratorData data = StructureGeneratorData.forWorld(player.worldObj);
		NBTTagCompound tag = data.getData();
		tag.setString("5", "3");
		data.markDirty();*/
		
		
		NBTTagCompound tag = SGD.getData();
		
		if(tag.getBoolean("genBool") == false){
			List playerEntities = world.playerEntities;
			 for (int i = 0; i < playerEntities.size(); ++i){
				 EntityPlayer entityplayer = (EntityPlayer)playerEntities.get(i);
				 //ChunkCoordinates newspawn=new ChunkCoordinates(100,10,100);
					//boolean p_71063_2_=true;
				// entityplayer.setSpawnChunk(newspawn, p_71063_2_);
				 entityplayer.setPositionAndUpdate(100, 10, 100);
			 
			 }
			//System.out.println("Inside If ********************************");
//			System.out.println(SGD.getData().getBoolean("genBool"));
			ForgeModContainer.defaultHasSpawnFuzz=false;
		
			world.setSpawnLocation(0, 4, 0);
			//switch(world.provider.dimensionId){


			this.generateOverWorld(random, chunkX *16, chunkZ *16, world);
			
		
			SGD.getData().setBoolean("genBool", true);
			tag.setBoolean("genBool", true);
			SGD.writeToNBT(tag);
			SGD.markDirty();
//			System.out.println(SGD.getData().getBoolean("genbool"));
			//System.out.println(SGD.markDirty());
		}
	}

	private void randomBlockGenerator(Random random, int x, int z, World world) {
		for (int i = 0; i < 25; i++) { // means set i = 0, then if its less than 25 keep adding one to i
			int chunkX = x + random.nextInt(16);
			int chunkY = random.nextInt(230); // height of the stone // value blocks genereate upto
			int chunkZ = z + random.nextInt(16);
			callingBlocks(world, random, chunkX, chunkY, chunkZ);

		}
	}

	public void callingBlocks(World world, Random random, int chunkX, int chunkY, int chunkZ){
		for (int i = 0; i<AllBlocks.blockArray.length;i++){
			new WorldGenMinable(AllBlocks.blockArray[i], 100).generate(world, random, chunkX, chunkY, chunkZ);
		}
	}

	private void generateOverWorld(Random random, int x, int z, World world) {		
		/*BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x, z);

		if ((biome == BiomeGenBase.plains)){
			for (int a=0; a<1; a++){
				int i = x + random.nextInt(256);
				int k = z + random.nextInt(256);
				int j = world.getHeightValue(i, k);
				System.out.println(" = "+i+" = "+k+" = "+j);*/
				
			  int X = world.getSpawnPoint().posX;
			  int Y = world.getTopSolidOrLiquidBlock(x, z);
			  int Z= world.getSpawnPoint().posZ;
			 // System.out.println("X = "+X+" Y = "+Y+ " Z = "+Z);
			  
				new StartingHouse().generate(world, random, X, Y, Z);
//			}
//		}
		randomBlockGenerator(random, x, z, world);

		//world.setSpawnLocation(p_72950_1_, p_72950_2_, p_72950_3_)
		/*System.out.println("***************************");
		System.out.println(world.getSpawnPoint().posX);
		System.out.println(world.getSpawnPoint().posY);
		System.out.println(world.getSpawnPoint().posZ);
		System.out.println("***************************");
		 */
	}

}
