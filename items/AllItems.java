package Diss.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import Diss.creativeTabs.CreativeTab;
import Diss.items.weapons.ItemWeapon;;

public class AllItems {
	
	public static Item itemCheese;
	public static Item romanSword;
	public static final Item.ToolMaterial anvil = EnumHelper.addToolMaterial("anvil", 4, 2000, 20.0F, 5.0F, 30);

	public static void registerToMain(){
		initialiseAndRegisterItem();
	}

	public static void initialiseAndRegisterItem(){
		itemCheese = new ItemFood(8, 0.5F, false).setUnlocalizedName("ItemCheese");
		romanSword = new ItemWeapon(anvil).setUnlocalizedName("RomanSword").setTextureName("fm:romanSword").setCreativeTab(CreativeTab.weapon);
		GameRegistry.registerItem(itemCheese, itemCheese.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(romanSword, romanSword.getUnlocalizedName().substring(5));

	}
}
