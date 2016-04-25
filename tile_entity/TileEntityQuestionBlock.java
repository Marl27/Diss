package Diss.tile_entity;

import java.io.IOException;

import Diss.blocks.AllBlocks;
import Diss.blocks.RomanNumberBlocks;
import Diss.inventory.ContainerQuestionBlock;
import Diss.questions.MathQuestions;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.EntityPlayer;


public class TileEntityQuestionBlock extends TileEntity implements ISidedInventory {

	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 1 };
	private static final int[] slotsSides = new int[] { 1 };
	private MathQuestions mq = new MathQuestions();
	public int currentQuestion;
	ContainerQuestionBlock CQB;
	//RomanNumberBlocks RNB;
	public ItemStack[] stackResult = new ItemStack[3];

	private String QuestionName;



	public TileEntityQuestionBlock() {
		//		super();
		/*try {

			QuestionBlock();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public void QuestionName(String string){
		this.QuestionName = string;
	}

	@Override
	public int getSizeInventory() {

		return this.stackResult.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		/*if (index < 0 || index >= this.getSizeInventory())
			return null;*/
		return this.stackResult[index];
	}

	/*@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					//Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}*/

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.stackResult[index] != null) {
			ItemStack itemstack;

			if (this.stackResult[index].stackSize <= count) {
				itemstack = this.stackResult[index];
				this.stackResult[index]= null;
				//this.markDirty();
				return itemstack;
			} else {
				itemstack = this.stackResult[index].splitStack(count);

				if (this.stackResult[index].stackSize <= 0) {
					this.stackResult[index] = null;
				}

				//this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}


	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		/*ItemStack stack = this.getStackInSlot(index);
		this.setInventorySlotContents(index, null);
		return stack;*/
		if (this.stackResult[index] != null) {
			ItemStack itemstack = this.stackResult[index];
			this.stackResult[index] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		/*if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0)
			stack = null;

		this.stackResult[index] = stack;

		this.markDirty();*/

		this.stackResult[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
		this.markDirty();
	}



	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.QuestionName : "Question block";
		//		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.QuestionName != null && this.QuestionName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		this.currentQuestion = tagCompound.getInteger("QuestionNumber") ;
		System.out.println("number that is saving"+tagCompound.getInteger("QuestionNumber"));

		NBTTagList tagList = tagCompound.getTagList("Items", 10);
		this.stackResult = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tabCompound1 = tagList.getCompoundTagAt(i);
			byte byte0 = tabCompound1.getByte("Slot");

			if (byte0 >= 0 && byte0 < this.stackResult.length) {
				this.stackResult[byte0] = ItemStack.loadItemStackFromNBT(tabCompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		//		tagCompound.setShort("BurnTime", (short) this.furnaceBurnTime);
		//		this.currentQuestion=2;
		tagCompound.setInteger("QuestionNumber", (int) this.currentQuestion);
		System.out.println("number that is saving"+tagCompound.getInteger("QuestionNumber"));
		//		tagCompound.setShort("CookTime", (short) this.furnaceBurnTime);
		NBTTagList tagList = new NBTTagList();

		for (int i = 0; i < this.stackResult.length; ++i) {
			if (this.stackResult[i] != null) {
				NBTTagCompound tagCompound1 = new NBTTagCompound();
				tagCompound1.setByte("Slot", (byte) i);
				this.stackResult[i].writeToNBT(tagCompound1);
				tagList.appendTag(tagCompound1);
			}
		}

		tagCompound.setTag("Items", tagList);

		if (this.hasCustomInventoryName()) {
			tagCompound.setInteger("QuestionNumber", this.currentQuestion); //.setString("CustomName", this.QuestionName);
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
		//return true;
	}

	@Override
	public void openInventory() {


	}

	@Override
	public void closeInventory() {


	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack itemstack) {
		System.out.println("isItemValidForSlot -- par1 -- "+par1);
		return par1 == 2 ? false : true;
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3) {
		return this.isItemValidForSlot(par1, itemstack);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/*@Override
	public void markDirty() {
		// TODO Auto-generated method stub

	}*/

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_,
			int p_102008_3_) {
		// TODO Auto-generated method stub
		return true;
	}

	public void doSomething() {
		//		System.out.println(temp.equals(Blocks.planks));
		ItemStack stack=new ItemStack(Blocks.diamond_block);
		/* System.out.println(" block data  "+getStackInSlot(0));
		 ItemStack stack2 = getStackInSlot(0);
		 Item temp = stack2.getItem();
		 Block itemBlock= Block.getBlockFromItem(temp);
		 if(itemBlock instanceof RomanNumberBlocks){
			 System.out.println("ItemBlock - "+((RomanNumberBlocks) itemBlock).getValue());
		 }*/
		//ItemStack stack1=new ItemStack(AllBlocks.I);
		//System.out.println("AllBlocks.I - "+stack1);
		System.out.println(stack);
		System.out.println(stack.getItem());
		this.stackResult[2]=stack.copy();
		stackResult[0]=null;	
		stackResult[1]=null;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();

	}

	public boolean checkAnswer(){
		boolean correct= false;
		int valueInSlot0 = 0, valueInSlot1 = 0;
		int answer = mq.getCurrentAnswer(currentQuestion);
		//check the first 2 slots and find if answer is correct
		ItemStack slot0 = getStackInSlot(0);
		ItemStack slot1 = getStackInSlot(1);
		Item itemInSlot0 = slot0.getItem();
		Item itemInSlot1 = slot1.getItem();
		Block blockInSlot0= Block.getBlockFromItem(itemInSlot0);
		Block blockInSlot1= Block.getBlockFromItem(itemInSlot1);

		if(blockInSlot0 instanceof RomanNumberBlocks){
			//System.out.println("ItemBlock - "+((RomanNumberBlocks) blockInSlot0).getValue());
			valueInSlot0 = ((RomanNumberBlocks) blockInSlot0).getValue();
		}
		if(blockInSlot1 instanceof RomanNumberBlocks){
			valueInSlot1 = ((RomanNumberBlocks) blockInSlot1).getValue();
		}
		/*if(valueInSlot0 == 0 || valueInSlot1 == 0){
			return false;
		}*/
		String sign = mq.getCurrentSign(currentQuestion);
	
		if (sign.equals("+") && (valueInSlot0 + valueInSlot1) == answer){
			System.out.println(sign);
			return true;
		}
		if (sign.equals("-") && (valueInSlot0 - valueInSlot1) == answer){
			System.out.println(sign);
			return true;
		}

		if (sign.equals("/") && (valueInSlot0 / valueInSlot1) == answer){
			System.out.println(sign);
			return true;		
		}

		if (sign.equals("*") &&(valueInSlot0 * valueInSlot1) == answer){
			System.out.println(sign);
			return true;
		}
		//return false;
		currentQuestion = mq.getRandomQuestion();
		//might need to update the gui to show new question
		return correct;

	}

	public String getQuestion(){
		//		System.out.println("getting current question "+this.currentQuestion);
		return mq.getQuestion(this.currentQuestion);
	}

	public int getRandomQuestion() {
		return mq.getRandomQuestion();
	}


}
