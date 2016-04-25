package Diss.blocks;

import scala.reflect.internal.Trees.This;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import Diss.creativeTabs.CreativeTab;
import Diss.teleport.TeleportEnglish;
import Diss.teleport.TeleportHome;
import Diss.teleport.TeleportMaths;
import Diss.blocks.RomanNumberBlocks;

public class AllBlocks {
	
	public static Block teleportMaths, teleportEnglish, teleportHome, castleBlock, 
	customBlock, customFurnace, customFurnaceActive, questionBlock;
	public static Block I, II, III, IV, V, VI, VII, VIII, IX, X;
	private RomanNumberBlocks RNB;
	
	public static Block[] blockArray = {I, II, III, IV, V, VI, VII, VIII, IX, X};
	public static String[] blockName = {"I","II","III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
	public static String[] blockTexture = {"fm:i","fm:ii","fm:iii","fm:iv","fm:v","fm:vi","fm:vii","fm:viii","fm:ix","fm:x"};
	//public static int[] blockValue = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

	
	public static void registerToMain(){
		initialiseAndRegisterBlock();
	}

	public static void initialiseAndRegisterBlock() {
		teleportMaths = new TeleportMaths(Material.rock).setBlockName("TeleportMathsBlock").setBlockTextureName("fm:teleport_maths").setCreativeTab(CreativeTab.blocks);
		teleportEnglish = new TeleportEnglish(Material.rock).setBlockName("TeleportEnglishBlock").setBlockTextureName("fm:teleport_english").setCreativeTab(CreativeTab.blocks);
		teleportHome = new TeleportHome(Material.rock).setBlockName("TeleportHomeBlock").setBlockTextureName("fm:teleport_home").setCreativeTab(CreativeTab.blocks);
		castleBlock = new CastleBlock(Material.cloth).setBlockName("castleBlock").setBlockTextureName("fm:castle_block").setCreativeTab(CreativeTab.blocks);
		questionBlock = new QuestionBlock(Material.rock).setBlockName("QuestionBlock").setCreativeTab(CreativeTab.blocks);
		customFurnace= new CustomFurnace(false).setBlockName("CustomFurnace").setCreativeTab(CreativeTab.blocks);
		customFurnaceActive= new CustomFurnace(true).setBlockName("CustomFurnaceActive");
		
		GameRegistry.registerBlock(customFurnace, customFurnace.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(customFurnaceActive, customFurnaceActive.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(questionBlock, questionBlock.getUnlocalizedName().substring(5));		
		GameRegistry.registerBlock(teleportMaths, teleportMaths.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(teleportEnglish, teleportEnglish.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(teleportHome, teleportHome.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(castleBlock, castleBlock.getUnlocalizedName().substring(5));
		
		addingRomanBlocks();
	}
	
	
	
	public static void addingRomanBlocks(){
		for (int i = 0; i<blockArray.length;i++){
			blockArray[i]  = new RomanNumberBlocks(Material.cloth).setBlockName(blockName[i]).setBlockTextureName(blockTexture[i]);
			((RomanNumberBlocks) blockArray[i]).setValue(i+1);
			GameRegistry.registerBlock(blockArray[i],blockArray[i].getUnlocalizedName().substring(5));
		}
	}
}
