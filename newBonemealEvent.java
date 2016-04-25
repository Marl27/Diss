package Diss;

import Diss.main.FirstMod;
import Diss.questions.MathQuestions;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class newBonemealEvent {
	
	@SubscribeEvent
	public void whenIBonemealStuff(BonemealEvent e){
		e.world.setBlock(e.x, e.y+1, e.z, Blocks.fire);
		
		e.entityPlayer.addStat(FirstMod.achievementBonemeal, 1);
		Minecraft.getMinecraft().thePlayer.sendChatMessage("hello");// prints msg in the game
		
	}
	

}
