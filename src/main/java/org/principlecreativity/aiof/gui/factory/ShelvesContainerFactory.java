package org.principlecreativity.aiof.gui.factory;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.IContainerFactory;
import org.principlecreativity.aiof.gui.ShelvesContainer;

public class ShelvesContainerFactory implements IContainerFactory<ShelvesContainer> {
    @Override
    public ShelvesContainer create(int windowId, PlayerInventory inv, PacketBuffer data) {
        return new ShelvesContainer(windowId, inv, data);
    }
}
