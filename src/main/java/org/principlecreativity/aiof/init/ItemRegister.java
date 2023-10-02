package org.principlecreativity.aiof.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.principlecreativity.aiof.Main;
import org.principlecreativity.aiof.object.item.CannedFood;
import org.principlecreativity.aiof.object.item.ContainerItem;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<Item> EMPTY_CAN;
    public static final RegistryObject<Item> CANNED_CHICKEN;
    public static final RegistryObject<Item> CANNED_COD;
    public static final RegistryObject<Item> CANNED_MUTTON;
    public static final RegistryObject<Item> CANNED_PORKCHOP;
    public static final RegistryObject<Item> CANNED_RABBIT;
    public static final RegistryObject<Item> CANNED_SALMON;
    public static final RegistryObject<Item> CANNED_BEEF;

    static {
        EMPTY_CAN = ITEMS.register("empty_can", ContainerItem::new);
        CANNED_CHICKEN = ITEMS.register("canned_chicken", () -> new CannedFood(8, 0.6F, true));
        CANNED_COD = ITEMS.register("canned_cod", () -> new CannedFood(7, 0.6F, true));
        CANNED_MUTTON = ITEMS.register("canned_mutton", () -> new CannedFood(8, 0.8F, true));
        CANNED_PORKCHOP = ITEMS.register("canned_porkchop", () -> new CannedFood(10, 0.8F, true));
        CANNED_RABBIT = ITEMS.register("canned_rabbit", () -> new CannedFood(7, 0.6F, true));
        CANNED_SALMON = ITEMS.register("canned_salmon", () -> new CannedFood(8, 0.8F, true));
        CANNED_BEEF = ITEMS.register("canned_beef", () -> new CannedFood(10, 0.8F, true));
    }
}
