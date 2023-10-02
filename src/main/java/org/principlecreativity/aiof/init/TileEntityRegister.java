package org.principlecreativity.aiof.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.principlecreativity.aiof.Main;
import org.principlecreativity.aiof.tileentity.ShelvesTileEntity;

public class TileEntityRegister {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);

    public static final RegistryObject<TileEntityType<ShelvesTileEntity>> SHELVES_TILE_ENTITY;

    static {
        SHELVES_TILE_ENTITY = TILE_ENTITIES.register("shelves_tile_entity", () -> TileEntityType.Builder.create(ShelvesTileEntity::new, BlockRegister.SHELVES.get()).build(null));
    }
}
