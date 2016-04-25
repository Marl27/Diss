package Diss.blocks;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Diss.main.FirstMod;
import Diss.questions.MathQuestions;
import Diss.tile_entity.TileEntityQuestionBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class QuestionBlock extends BlockContainer{
	
	public QuestionBlock(Material material) {
		super(material);
		//this.setBlockUnbreakable();
		//this.setResistance(2000.0F);
	}
	
	@Override
	public boolean onBlockActivated(World world,int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)/*(World parWorld, BlockPos parBlockPos, 
	      IBlockState parIBlockState, EntityPlayer parPlayer, EnumFacing parSide, 
	      float hitX, float hitY, float hitZ)*/
	{
		/*try {
			MathQuestions.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MathQuestions.answerFinder();*/
	    if (!world.isRemote)
	    {
	    	// debug works
	    	System.out.println("QuestionBlock onBlockActivated");	
//	        FirstMod.proxy.openMyGui(); 
	    	player.openGui(FirstMod.Modinstance, 1, world, x, y, z);
	    }
	        
	    return true;
		//player.openGui(FirstMod.Modinstance, 0, world, x, y, z);
		//return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		//Todo
		return new TileEntityQuestionBlock();
	}

	@SideOnly(Side.CLIENT)
	public void onBlockAdded(World world, int x, int y, int z){
		super.onBlockAdded(world, x, y, z);
		this.direction(world, x, y, z);
	}
	
	private void direction(World world, int x, int y, int z) {
		if(world.isRemote){
			Block direction = world.getBlock(x, y, z - 1);
			Block direction1 = world.getBlock(x, y, z + 1);
			Block direction2 = world.getBlock(x - 1, y, z);
			Block direction3 = world.getBlock(x + 1, y, z);
			byte byte0 = 3;
			//if the block is opaque
			if(direction.func_149730_j() && direction.func_149730_j()){
				byte0 = 3;
			}
			if(direction1.func_149730_j() && direction1.func_149730_j()){
				byte0 = 2;
			}
			if(direction2.func_149730_j() && direction2.func_149730_j()){
				byte0 = 5;
			}
			if(direction3.func_149730_j() && direction3.func_149730_j()){
				byte0 = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, byte0, 2);
		}
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
		// mathHelper method from blockFurnace class
		int direction = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (direction == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (direction == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (direction == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (direction == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

		if (itemstack.hasDisplayName()) {
			((TileEntityQuestionBlock) world.getTileEntity(x, y, z)).QuestionName(itemstack.getDisplayName());
		}
	}
	
	public static void updateBlockState(World world, int x, int y, int z) {
		int direction = world.getBlockMetadata(x, y, z);
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		world.setBlockMetadataWithNotify(x, y, z, direction, 2);

		if (tileEntity != null) {
			tileEntity.validate();
			world.setTileEntity(x, y, z, tileEntity);
		}
	}
	
	
}


