package org.principlecreativity.aiof.init;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.principlecreativity.aiof.Main;
import org.principlecreativity.aiof.object.block.Shelves;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);

    public static final RegistryObject<Block> SHELVES;

    static {
        SHELVES = BLOCKS.register("shelves", Shelves::new);
    }
}
