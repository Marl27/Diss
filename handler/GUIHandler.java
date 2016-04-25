package Diss.handler;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import Diss.gui.GuiCustomFurnace;
import Diss.gui.GuiQuestionBlock;
import Diss.inventory.ContainerCustomFurnace;
import Diss.inventory.ContainerQuestionBlock;
import Diss.tile_entity.TileEntityQuestionBlock;
import Diss.tile_entity.TileEntityCustomFurnace;

public class GUIHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		//TileEntity tileEntity = world.getTileEntity(x, y, z);
		//	InventoryQuestionBlock IQB = (InventoryQuestionBlock) world.getTileEntity(x, y, z);
		if(ID == 0){
			// code gets here
			TileEntityCustomFurnace tileEntityFurnace = (TileEntityCustomFurnace) world.getTileEntity(x, y, z);
			return new ContainerCustomFurnace(player.inventory, tileEntityFurnace);

			//			  return new containerQuestionBlock(player.inventory, IQB);
		}
		if(ID == 1){System.out.println(" container tile entity");
			TileEntityQuestionBlock IQB = (TileEntityQuestionBlock) world.getTileEntity(x, y, z);
			return new ContainerQuestionBlock(player.inventory, IQB);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		//TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(ID == 0){
			// code gets here
			TileEntityCustomFurnace tileEntityTestContainer = (TileEntityCustomFurnace) world.getTileEntity(x, y, z);
			return new GuiCustomFurnace(player.inventory, tileEntityTestContainer);


		}
		if(ID == 1){
//			TileEntityCustomFurnace tileEntityTestContainer = (TileEntityCustomFurnace) world.getTileEntity(x, y, z);
//			return new GuiCustomFurnace(player.inventory, tileEntityTestContainer);
			System.out.println(" gui tile entity");
			TileEntityQuestionBlock IQB = (TileEntityQuestionBlock)world.getTileEntity(x, y, z);
			return new GuiQuestionBlock(player.inventory, IQB);
			//			return new GuiQuestionBlock(player.inventory, world, I18n.format("tile.QuestionBlock.name"), x, y, z);
		}
		return null;
	}


}
