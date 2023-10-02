package org.principlecreativity.aiof.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.principlecreativity.aiof.Main;
import org.principlecreativity.aiof.gui.ShelvesContainer;
import org.principlecreativity.aiof.gui.factory.ShelvesContainerFactory;

public class ContainerRegister {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MOD_ID);

    public static final RegistryObject<ContainerType<ShelvesContainer>> SHELVES_CONTAINER;

    static {
        SHELVES_CONTAINER = CONTAINERS.register("shelves_container", () -> IForgeContainerType.create(new ShelvesContainerFactory()));
    }
}
