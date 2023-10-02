package org.principlecreativity.aiof;

import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.principlecreativity.aiof.gui.ShelvesContainer;
import org.principlecreativity.aiof.gui.ShelvesScreen;
import org.principlecreativity.aiof.init.BlockRegister;
import org.principlecreativity.aiof.init.ContainerRegister;
import org.principlecreativity.aiof.init.ItemRegister;
import org.principlecreativity.aiof.init.TileEntityRegister;

import java.util.stream.Collectors;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "aiof";
    public static final ItemGroup TAB = new Tab();
    private static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::processIMC);
        bus.addListener(this::doClientStuff);

        TileEntityRegister.TILE_ENTITIES.register(bus);
        ContainerRegister.CONTAINERS.register(bus);
        ItemRegister.ITEMS.register(bus);
        BlockRegister.BLOCKS.register(bus);


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        ShelvesContainer.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    public static class Tab extends ItemGroup{

        public Tab() {
            super("aiof_tab");
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegister.CANNED_CHICKEN.get());
        }
    }
}
