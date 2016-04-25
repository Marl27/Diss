package Diss.main;

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Commons;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Commons;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import Diss.Player;
import Diss.newBonemealEvent;
import Diss.blocks.AllBlocks;
import Diss.blocks.CastleBlock;
//import Diss.blocks.CustomFurnace;
import Diss.blocks.RomanNumberBlocks;
import Diss.creativeTabs.CreativeTab;
import Diss.gen.OreGen;
import Diss.items.AllItems;
import Diss.questions.MathQuestions;
//import Diss.gui.BasicGUI;
import Diss.teleport.TeleportEnglish;
import Diss.teleport.TeleportHome;
import Diss.teleport.TeleportMaths;
//import Diss.weapon.Itemitem;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.registry.GameRegistry;

//@Mod(modid = "fm", name = "First Mod", version = "1.0")
@Mod(modid = ModInfo.MODID, name = ModInfo.name, version = ModInfo.version)

public class FirstMod {
	
	
	
	
	@SidedProxy(clientSide="Diss.main.ClientProxy", serverSide="Diss.main.ServerProxy")

//	public static ClientProxy Cproxy;
	public static ServerProxy proxy;
	@Instance(ModInfo.MODID)
	public static FirstMod Modinstance;
	
	//public static Item itemCheese;
	//public static Item romanSword;
	
	//public static Block teleportMaths, teleportEnglish, teleportHome, castleBlock, customBlock, customFurnace, customFurnaceActive;
	//public static Block I, II, III, IV, V, VI, VII, VIII, IX, X;
	
	//********************************************
	public static Block blockInstantStructure;
	//********************************************
	
	public static Achievement achievementBonemeal;
	
	
	
	private Player player;
	
	//public static final Item.ToolMaterial anvil = EnumHelper.addToolMaterial("anvil", 4, 2000, 20.0F, 5.0F, 30);
	//(name, harvestLevel, maxUses, efficiency, damage, enchantability)
	
	//public static Achievement achievementStartMagicBeans = new Achievement("achievement.startmagicbeans", "startmagicbeans", 0, 0, MagicBeans.magicBeans, (Achievement)null);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
	
		
	// GUI Attempt 
	//	proxy.registerKeyBindings();
	// NetworkRegistry.INSTANCE.registerGuiHandler(Modinstance, proxy);

		ForgeModContainer.defaultHasSpawnFuzz=false;
		CreativeTab.registerToMain();
		AllItems.registerToMain();
		AllBlocks.registerToMain();
		
		proxy.registerTileEntities();
		//proxy.openMyGui();
//		Cproxy.registerRenderThings();
		// Adding Items*********************************************
		
		/*itemCheese = new ItemFood(8, 0.5F, false).setUnlocalizedName("ItemCheese");
		romanSword = new Itemitem(anvil).setUnlocalizedName("RomanSword").setTextureName("fm:romanSword").setCreativeTab(weapon);
		GameRegistry.registerItem(itemCheese, itemCheese.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(romanSword, romanSword.getUnlocalizedName().substring(5));
*/
		// Adding Blocks********************************************
		
		
		//CraftingRecipies
		
		
		//this.addingRomanBlocks();
		/*teleportMaths = new TeleportMaths(Material.rock).setBlockName("TeleportMathsBlock").setBlockTextureName("fm:teleport_maths").setCreativeTab(blocks);
		teleportEnglish = new TeleportEnglish(Material.rock).setBlockName("TeleportEnglishBlock").setBlockTextureName("fm:teleport_english").setCreativeTab(blocks);
		teleportHome = new TeleportHome(Material.rock).setBlockName("TeleportHomeBlock").setBlockTextureName("fm:teleport_home").setCreativeTab(blocks);
		castleBlock = new CastleBlock(Material.cloth).setBlockName("castleBlock").setBlockTextureName("fm:castle_block").setCreativeTab(blocks);
		
		GameRegistry.registerBlock(teleportMaths, teleportMaths.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(teleportEnglish, teleportEnglish.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(teleportHome, teleportHome.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(castleBlock, castleBlock.getUnlocalizedName().substring(5));
		*/
		//I = new RomanNumberBlocks(Material.cloth).setBlockName("I").setBlockTextureName("fm:i");
		//GameRegistry.registerBlock(I,I.getUnlocalizedName().substring(5));
		
		//*********** Custom Furnace **************************** 
		
		//customFurnace= new CustomFurnace(false).setBlockName("TutFurnace").setCreativeTab(blocks);
		//customFurnaceActive= new CustomFurnace(true).setBlockName("TutFurnaceActive");
		//GameRegistry.registerBlock(customFurnace, customFurnace.getUnlocalizedName());
		//GameRegistry.registerBlock(customFurnaceActive, customFurnaceActive.getUnlocalizedName());
		
		
		
		
//		player = new Player(World, Entity);
		//Event.PlayerRespawnEvent();
		
		
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		

		//calling Ore Genereration class
		OreGen OG=new OreGen();
		GameRegistry.registerWorldGenerator(OG, 5);
		
		//achievements***********************************************************
		achievementBonemeal = new Achievement("achievement.bonemeal", "bonemeal", 0, 0, new ItemStack(Items.dye, 15), AchievementList.buildFurnace).registerStat();
				
		//achievements page
		AchievementPage.registerAchievementPage( new AchievementPage("Awesome Achievements", new Achievement[]{achievementBonemeal}));
		ManageCraftingRecipes.registerToMain();	
		proxy.registerNetworkStuff();
		/*GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[0]), new Object[]{"W","","",'W',Blocks.planks});//basic crafting recipe
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[1]), new Object[]{"WW","","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[2]), new Object[]{"WWW","","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[3]), new Object[]{"WWW","W","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[4]), new Object[]{"WWW","WW","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[5]), new Object[]{"WWW","WWW","",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[6]), new Object[]{"WWW","WWW","W",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[7]), new Object[]{"WWW","WWW","WW ",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[8]), new Object[]{"WWW","WWW","WWW",'W',Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(AllBlocks.blockArray[9]), new Object[]{" W "," W "," W ",'W',Blocks.planks});
*/
		MinecraftForge.EVENT_BUS.register(new newBonemealEvent());
//		MinecraftForge.EVENT_BUS.register(new CastleBlock());
		//MinecraftForge.EVENT_BUS.post(new CastleBlockEvent(null , null, RNB, 0, 0, 0));
//		MinecraftForge.EVENT_BUS.register(new Player(World,0 , 0, 0, null));

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){

		
	}
	
	
	
	/*public static CreativeTabs weapon = new CreativeTabs("weapon"){
		@Override
		public Item getTabIconItem(){
			return new ItemStack(romanSword).getItem();
		}
	};
	
	public static CreativeTabs blocks = new CreativeTabs("blocks"){
		@Override
		public Item getTabIconItem(){
			return new ItemStack(teleportMaths).getItem();
		}
	};
	*/

	
	
}
