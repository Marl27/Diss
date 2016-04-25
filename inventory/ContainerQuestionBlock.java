package Diss.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Diss.main.FirstMod;
import Diss.slot.RewardSlot;
import Diss.tile_entity.TileEntityQuestionBlock;
import Diss.tile_entity.TileEntityCustomFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
//import net.minecraft.tileentity.TileEntity;

public class ContainerQuestionBlock extends Container {

	public static enum State
	{
		ERROR, READY
	}

	public InventoryCrafting inputInventory = new InventoryCrafting(this, 1, 2);
	


	public TileEntityQuestionBlock outputInventory;//tile entity class
	//private InventoryQuestionBlock tileEntity;
	//	private  World worldObj;
	public InventoryPlayer playerInventory;
	public String resultString = "Questioning.result.ready";
	public State QuestingState = State.READY;
	//	public int x = 0;
	//	public int y = 0;
	//	public int z = 0;



	public ContainerQuestionBlock(InventoryPlayer player,TileEntityQuestionBlock IQB){ // World parWorld, int parX, int parY, int parZ){

		//		x = parX;
		//		y = parY;
		//		z = parZ;
		//		worldObj = parWorld;

		this.outputInventory = IQB;
		this.addSlotToContainer(new Slot(IQB, 0, 21, 53));
		this.addSlotToContainer(new Slot(IQB, 1, 56, 53));
		this.addSlotToContainer(new RewardSlot(player.player, IQB, 2, 130, 53));

		/*inputSlotNumber = this.addSlotToContainer(new Slot(inputInventory, 0, 56, 17)).slotNumber;//0, 56, 17
		this.addSlotToContainer(new Slot(inputInventory, 1, 56, 53));//1, 56, 53
		this.addSlotToContainer(new Slot(outputInventory, 2, 30 + 15, 35));
		//this.addSlotToContainer(new Slot(tileEntityFurnace, 2, 56, 53));
		//		this.addSlotToContainer(new SlotFurnace(player.player, tileEntityFurnace, 2, 116, 35));
		 */

		for(int playerSlotIndexY = 0; playerSlotIndexY < 3; ++playerSlotIndexY)
		{
			for(int playerSlotIndexX = 0; playerSlotIndexX < 9; ++playerSlotIndexX)
			{
				int x = (8 + playerSlotIndexX * 18);
				int y = (84 + playerSlotIndexY * 18);
				int index = playerSlotIndexX + (playerSlotIndexY * 9) + 9;
				//System.out.println("slot index = "+index);
				this.addSlotToContainer(new Slot(player, index, x, y));
				//addSlotToContainer(new Slot(player, playerSlotIndexX + playerSlotIndexY * 9 + 9, 8 + playerSlotIndexX * 18, 84 + playerSlotIndexY * 18));
			}
		}

		for(int hotbarSlotIndex = 0; hotbarSlotIndex < 9; ++hotbarSlotIndex)
		{
			int index = hotbarSlotIndex;
			//System.out.println("hot bar slot index = "+index);
			addSlotToContainer(new Slot(player, index, (8 + hotbarSlotIndex * 18) , 142));
			//8 + hotbarSlotIndex * 18, 142));
		}     

		playerInventory = player;
	}
	@Override
	public void addCraftingToCrafters(ICrafting craft){
		super.addCraftingToCrafters(craft);
		craft.sendProgressBarUpdate(this, 0, this.outputInventory.currentQuestion);
	}
	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); ++i){
			ICrafting craft = (ICrafting) this.crafters.get(i);
			if(this.outputInventory.currentQuestion == 0){
				System.out.println("Detect and saving "+this.outputInventory.currentQuestion);
				this.outputInventory.currentQuestion = this.outputInventory.getRandomQuestion();
				craft.sendProgressBarUpdate(this, 0, this.outputInventory.currentQuestion);
			}

		}
	}


	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2){
		if(par1 == 0){
			
			System.out.println("Param 2 "+par2);
			this.outputInventory.currentQuestion = par2;
			System.out.println("Current Question is now "+this.outputInventory.currentQuestion);
		}
	}


	@Override
	public Slot getSlot(int p_75139_1_)
	{	
		if(p_75139_1_==2){
			//			System.out.println("get slot function slotnum = "+p_75139_1_);
		}
		return super.getSlot(p_75139_1_);
	}

	@Override
	public ItemStack slotClick(int parSlotId, int parMouseButtonId, int parClickMode, EntityPlayer parPlayer)
	{
		
		return super.slotClick(parSlotId, parMouseButtonId, parClickMode, parPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		//null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			//merges the item into player inventory since its in the tileEntity
			if (slot < outputInventory.getSizeInventory()) {
				if (!this.mergeItemStack(stackInSlot, outputInventory.getSizeInventory(), 36+outputInventory.getSizeInventory(), true)) {
					return null;
				}
			}
			//places it into the tileEntity is possible since its in the player inventory
			else if (!this.mergeItemStack(stackInSlot, 0, outputInventory.getSizeInventory(), false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.outputInventory.isUseableByPlayer(player);
	}


	@Override
	public boolean enchantItem(EntityPlayer player, int action){
		System.out.println("SHITS Gone Down");
		if(outputInventory.checkAnswer()){
			outputInventory.updateEntity();
			ItemStack stack=new ItemStack(Blocks.diamond_block);
//			System.out.println("slot information "+stack);
			this.putStackInSlot(2, stack);
		}
		else{
			ItemStack stack=new ItemStack(Blocks.coal_block);
			this.putStackInSlot(2, stack);
		}
		outputInventory.currentQuestion = 0;
		this.putStackInSlot(1, (ItemStack)null);
		this.putStackInSlot(0,(ItemStack) null);

		detectAndSendChanges();

		outputInventory.updateEntity();
		return true;

	}

	/**
	 * Called when a player shift-clicks on a slot.
	 * @return 
	 *//*
    @Override
    public ItemStack transferStackInSlot(EntityPlayer parPlayer, int parSlotIndex)
    {
    	ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(parSlotIndex);
        // If there is something in the stack to pick up
        if (slot != null && slot.getHasStack())
        {
        	ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

            // If the slot is one of the custom slots
            if (slot.inventory.equals(inputInventory) || slot.inventory
                   .equals(outputInventory))
            {
                // try to move to player inventory
                if (!playerInventory.addItemStackToInventory(slot.getStack()))
                {
                    return null;
                }
                if(itemstack1.stackSize == 0){
    				slot.putStack((ItemStack)null);
    			}else{
    				slot.onSlotChanged();
    			}
            }
            // if the slot is a player inventory slot
            else if(slot.inventory.equals(playerInventory))
            {
                // DEBUG
                System.out.println("Shift-clicked on player inventory slot");
                // Try to transfer to input slot
                if (!((Slot)inventorySlots.get(inputSlotNumber)).getHasStack())
                {
                    ((Slot)inventorySlots.get(inputSlotNumber)).putStack(slot.getStack());
                    slot.putStack(null);
                    slot.onSlotChanged();
                }
                else
                {
                    // DEBUG
                    System.out.println("There is already something in the input slot");
                }
            }
        }
        return null;
    }*/


	// moving certain amount to and from the furnace used for items that can stack
	//  needs changing to remove smelting
	// (non-Javadoc)
	// @see net.minecraft.inventory.Container#transferStackInSlot(net.minecraft.entity.player.EntityPlayer, int)




	/*  
    @Override
 	public ItemStack transferStackInSlot(EntityPlayer player, int par2){
 		ItemStack itemstack = null;
 		Slot slot = (Slot) this.inventorySlots.get(par2);
 		System.out.println("Before If");
 		if(slot != null && slot.getHasStack()){
 			System.out.println("in If");
 			ItemStack itemstack1 = slot.getStack();
 			itemstack = itemstack1.copy();
 			System.out.println(itemstack);
 			if(par2 == 2){
 				if(!this.mergeItemStack(itemstack1, 3, 39, true)){
 					System.out.println("if par2 = 2, this is itemstack1"+ itemstack1);
 					return null;
 				}
 				slot.onSlotChange(itemstack1, itemstack);
 			}else if(par2 != 1 && par2 != 0){
 				System.out.println("Something");
// 				if(FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null){
// 					if(!this.mergeItemStack(itemstack1, 0, 1, false)){
// 						return null;
// 					}
// 				}else if(TileEntityCustomFurnace.isItemFuel(itemstack1)){
 					if(!this.mergeItemStack(itemstack1, 1, 2, false)){
 						return null;
 					}
// 				}else 
 					if(par2 >=3 && par2 < 30){
 					if(!this.mergeItemStack(itemstack1, 30, 39, false)){
 						return null;
 					}
 				}else if(par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)){
 					return null;
 				}
 			}else if(!this.mergeItemStack(itemstack1, 3, 39, false)){
 				return null;
 			}
 			if(itemstack1.stackSize == 0){
 				slot.putStack((ItemStack)null);
 			}else{
 				slot.onSlotChanged();
 			}
 			if(itemstack1.stackSize == itemstack.stackSize){
 				return null;
 			}
 			slot.onPickupFromSlot(player, itemstack1);
 			System.out.println("onPickupFromSlot " + player +"  "+ itemstack1);
 		}
 		return itemstack;
 	}
	 */

	/*@Override
    public boolean canMergeSlot(ItemStack parItemStack, Slot parSlot)
    {
        return !parSlot.inventory.equals(outputInventory);
    }*/

	/* @Override
    public Slot getSlot(int parSlotIndex)
    {
        if(parSlotIndex >= inventorySlots.size())
            parSlotIndex = inventorySlots.size() - 1;
        return super.getSlot(parSlotIndex);
    }*/





}
