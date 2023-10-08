package org.principlecreativity.aiof.tileentity;

import io.netty.buffer.Unpooled;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.principlecreativity.aiof.gui.ShelvesContainer;
import org.principlecreativity.aiof.init.TileEntityRegister;
import org.principlecreativity.aiof.pool.ShelvesPool;
import org.principlecreativity.aiof.util.TextComponents;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

public class ShelvesTileEntity  extends LockableLootTileEntity implements ISidedInventory{
    public NonNullList<ItemStack> stacks = NonNullList.withSize(18, ItemStack.EMPTY);

    public ShelvesTileEntity() {
        super(TileEntityRegister.SHELVES_TILE_ENTITY.get());
        flushed();
    }

    @Override
    public void read(BlockState blockState, CompoundNBT compound) {
        super.read(blockState, compound);
        if (!this.checkLootAndRead(compound)) {
            stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        }
        ItemStackHelper.loadAllItems(compound, stacks);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, stacks);
        }
        return compound;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public int getSizeInventory() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : stacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    @Override
    public ITextComponent getDefaultName() {
        return TextComponents.SHELVES_NAME;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public Container createMenu(int id, PlayerInventory player) {
        return new ShelvesContainer(id, player, new PacketBuffer(Unpooled.buffer()).writeBlockPos(this.getPos()));
    }

    @Override
    public ITextComponent getDisplayName() {
        return TextComponents.SHELVES_NAME;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return IntStream.range(0, this.getSizeInventory()).toArray();
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, @Nullable Direction direction) {
        return this.isItemValidForSlot(index, stack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return true;
    }

    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.removed && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return handlers[facing.ordinal()].cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public void remove() {
        super.remove();
        for (LazyOptional<? extends IItemHandler> handler : handlers)
            handler.invalidate();
    }

    public void flushed(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Random r = new Random();
                int index =r.nextInt(18);
                stacks.set(index, new ItemStack(ShelvesPool.extract()));
            }
        }, 600000, 600000);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Random r = new Random();
                int index =r.nextInt(18);
                stacks.set(index, new ItemStack(ShelvesPool.extract()));
            }
        }, 600000, 600000);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Random r = new Random();
                int index =r.nextInt(18);
                stacks.set(index, new ItemStack(ShelvesPool.extract()));
            }
        }, 600000, 600000);
    }

}
