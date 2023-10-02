package org.principlecreativity.aiof.pool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import org.principlecreativity.aiof.init.ItemRegister;
import org.principlecreativity.aiof.init.TileEntityRegister;
import org.principlecreativity.aiof.object.item.CannedFood;
import org.principlecreativity.aiof.tileentity.ShelvesTileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShelvesPool {
    private static List<Item> shelvesPool () {
        List<Item> shelvesPool = new ArrayList<>();
        for (RegistryObject<Item> item : ItemRegister.ITEMS.getEntries()){
            if(item.get() instanceof CannedFood) {
                shelvesPool.add(item.get());
            }
        }
        return shelvesPool;
    }

    public static Item extract () {
        Random r = new Random();
        int range = shelvesPool().size();
        int index = r.nextInt(range);
        return shelvesPool().get(index);
    }

}
