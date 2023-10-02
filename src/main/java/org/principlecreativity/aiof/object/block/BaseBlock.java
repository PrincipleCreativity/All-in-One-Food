package org.principlecreativity.aiof.object.block;


import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.principlecreativity.aiof.init.ItemRegister;

public class BaseBlock extends Block {
    public BaseBlock(Properties properties, Item.Properties properties1, String name) {
        super(properties);
        ItemRegister.ITEMS.register(name, () -> new BlockItem(this, properties1));
    }
}
