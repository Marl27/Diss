package Diss.main;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import Diss.blocks.AllBlocks;
import Diss.items.AllItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class ManageCraftingRecipes {

	public static void registerToMain(){
	addCraftingRecipies();
	}
	
	public static void addCraftingRecipies(){
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[0]), new Object[]{"W","","",'W',Blocks.planks});//basic crafting recipe
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[1]), new Object[]{"WW","","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[2]), new Object[]{"WWW","","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[3]), new Object[]{"WWW","W","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[4]), new Object[]{"WWW","WW","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[5]), new Object[]{"WWW","WWW","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[6]), new Object[]{"WWW","WWW","W",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[7]), new Object[]{"WWW","WWW","WW ",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[8]), new Object[]{"WWW","WWW","WWW",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[9]), new Object[]{" W "," W "," W ",'W',Blocks.planks});
		
		GameRegistry.addSmelting(AllItems.itemCheese, new ItemStack(Blocks.diamond_block, 5), 20.0F);

	}

	

}
