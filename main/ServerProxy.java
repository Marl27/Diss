package Diss.main;


//import Diss.handler.GUIHandler;
//import Diss.tile_entity.TileEntityCustomFurnace;

import net.minecraft.client.Minecraft;
import Diss.gui.GuiQuestionBlock;
import Diss.handler.GUIHandler;
import Diss.tile_entity.TileEntityQuestionBlock;
import Diss.tile_entity.TileEntityCustomFurnace;

import com.google.common.base.Strings;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ServerProxy {

	public void registerRenderThings() {

	}

	/*public int addArmor(String armor) {
		return 0;
	}*/
	public int addArmor(String armor) {
		return 0;
	}
	
	
	public void registerNetworkStuff(){
		NetworkRegistry.INSTANCE.registerGuiHandler(FirstMod.Modinstance, new GUIHandler());
	}

	public void registerTileEntities(){
		
		GameRegistry.registerTileEntity(TileEntityQuestionBlock.class, "InventoryQuestionBlock");
		GameRegistry.registerTileEntity(TileEntityCustomFurnace.class, "TileEntityTutFurnace");
		
	}

	public void openMyGui() {
		// TODO Auto-generated method stub
//		Minecraft.getMinecraft().displayGuiScreen(new GuiQuestionBlock());
		
	}
}