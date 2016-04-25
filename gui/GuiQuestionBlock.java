package Diss.gui;


import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Diss.inventory.ContainerCustomFurnace;
import Diss.inventory.ContainerQuestionBlock;
import Diss.main.FirstMod;
import Diss.main.ModInfo;
import Diss.questions.MathQuestions;
import Diss.tile_entity.TileEntityQuestionBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
//import net.minecraft.world.World;
@SideOnly(Side.CLIENT)
public class GuiQuestionBlock extends GuiContainer{  //GuiScreen {

	private GuiButton submitButton;
//	public containerQuestionBlock container;
	private String Question;
	private static final ResourceLocation QuestionGuiTextures = new ResourceLocation("fm:textures/gui/container/block_template.png");
	private TileEntityQuestionBlock tileEntity;
	

	public GuiQuestionBlock(InventoryPlayer invPlayer , TileEntityQuestionBlock IBQ) //World parWorld, String parBlockName, int parX, int parY, int parZ)
	{
		super(new ContainerQuestionBlock(invPlayer,IBQ));

		this.tileEntity = IBQ;		
//		this.xSize=256;
//		this.ySize=256;
//		try {
//		tileEntity.
//		} catch (IOException e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui() 
	
	{
		//Must keep at all times
		super.initGui();
		
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		int offsetFromScreenLeft = width/2;
		int x = (this.width - this.xSize ) / 2 ; //205
		int y = (this.height - this.ySize) / 2;	//90
		//xSize - 176ySize - 166
		//guiLeft - 125guiTop - 37
		//guiLeft - 253guiTop - 93
		System.out.println("*******X and Y -" + x + " "+y);
		//submitButton = new GuiButton(0, offsetFromScreenLeft + 120, 4 ,   98, 20, I18n.format("Submit", new Object[0]));
		//submitButton = new GuiButton(0, 205, 90 , 40, 17, I18n.format("Submit", new Object[0])); // (ID, XPos, YPos , Width, Height, String on Button)
		//submitButton = new GuiButton(0, ySize+29, ySize/2 +7 , 40, 17, I18n.format("Submit", new Object[0])); // (ID, XPos, YPos , Width, Height, String on Button)
		submitButton = new GuiButton(0, 205, 90 , 40, 17, "Submit"); // (ID, XPos, YPos , Width, Height, String on Button)

		submitButton.visible = true;
		buttonList.add(submitButton);
		//xSize - 176ySize - 166
		//guiLeft - 125guiTop - 37
		//guiLeft - 253guiTop - 93
	}

	/**
	 * Called when a mouse button is pressed and the mouse is moved around. 
	 * Parameters are : mouseX, mouseY, lastButtonClicked & 
	 * timeSinceMouseClick.
	 *//*
	@Override
	protected void mouseClickMove(int parMouseX, int parMouseY, 
			int parLastButtonClicked, long parTimeSinceMouseClick) 
	{

	}*/

	@Override
	protected void actionPerformed(GuiButton parButton) { //boolean isSet = true;
		super.actionPerformed(parButton);
		
		if (parButton == submitButton) {	
			Minecraft.getMinecraft().thePlayer.sendChatMessage("actionPerformed hello");

			ItemStack temp = this.tileEntity.getStackInSlot(0);
			ItemStack temp1 = this.tileEntity.getStackInSlot(1);
			ItemStack temp2 = this.tileEntity.getStackInSlot(2);
			System.out.println("slot0 = "+temp+" slot1 = "+temp1+" slot2 = "+temp2);

			//this.tileEntity.doSomething();//setInventorySlotContents(2, stack);
			System.out.println("slot0 = "+temp+" slot1 = "+temp1+" slot2 = "+this.tileEntity.getStackInSlot(2));
			if(temp != null && temp1 != null){
//				this.mc.playerController.sendEnchantPacket(1, 1);
				System.out.println("Sending packet information");
				//Massive error
				int id = this.inventorySlots.windowId;
				System.out.println("container window id = " +id);
				Minecraft.getMinecraft().playerController.sendEnchantPacket(id, 1);
			}
		}
	}

	
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String question= tileEntity.getQuestion();
		GL11.glPushMatrix();
		GL11.glScalef(0.8f, 0.8f, 0.8f);
		fontRendererObj.drawString(question, xSize / 2 - 85 , 25 , 4210752); //(String, x, y, something)
		GL11.glPopMatrix();
		fontRendererObj.drawString(I18n.format("container.inventory"), 6, ySize - 96 + 2, 4210752);
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(QuestionGuiTextures);
		int k = (this.width - this.xSize ) / 2 ;
		int l = (this.height - this.ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
