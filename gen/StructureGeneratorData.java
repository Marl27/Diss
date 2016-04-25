package Diss.gen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class StructureGeneratorData extends WorldSavedData{
	final static String key = "Himalya";
	
	 public static StructureGeneratorData forWorld(World world) {
	      MapStorage storage = world.perWorldStorage;
	      StructureGeneratorData result = (StructureGeneratorData)storage.loadData(StructureGeneratorData.class, key);
	      if (result == null) {
	         result = new StructureGeneratorData(key);
	         storage.setData(key, result);
	      }
	      return result;
	   }
	   
	
	private NBTTagCompound data = new NBTTagCompound();
	
	public StructureGeneratorData(String tagName) {
		super(tagName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		
		 data = compound.getCompoundTag(key);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		
		compound.setTag(key, data);
	}
	
	public NBTTagCompound getData() {
        return data;
    }

}
