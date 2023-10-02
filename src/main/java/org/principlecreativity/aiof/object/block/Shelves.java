package org.principlecreativity.aiof.object.block;

import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import org.principlecreativity.aiof.Main;
import org.principlecreativity.aiof.gui.ShelvesContainer;
import org.principlecreativity.aiof.tileentity.ShelvesTileEntity;
import org.principlecreativity.aiof.util.TextComponents;

import java.util.Collections;
import java.util.List;

public class Shelves extends BaseBlock {
    public Shelves() {
        super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).harvestLevel(2)
                .harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid().setOpaque((bs, br, bp) -> false), new Item.Properties().group(Main.TAB), "shelves");
    }

    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, Hand hand,
                                             BlockRayTraceResult hit) {
        super.onBlockActivated(blockstate, world, pos, entity, hand, hit);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (entity instanceof ServerPlayerEntity) {
            NetworkHooks.openGui((ServerPlayerEntity) entity, new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return TextComponents.SHELVES_NAME;
                }

                @Override
                public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
                    return new ShelvesContainer(id, inventory,
                            new PacketBuffer(Unpooled.buffer()).writeBlockPos(new BlockPos(x, y, z)));
                }
            }, new BlockPos(x, y, z));
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        return tileEntity instanceof INamedContainerProvider ? (INamedContainerProvider) tileEntity : null;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ShelvesTileEntity();
    }

    @Override
    public boolean eventReceived(BlockState state, World world, BlockPos pos, int eventID, int eventParam) {
        super.eventReceived(state, world, pos, eventID, eventParam);
        TileEntity tileentity = world.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof ShelvesTileEntity) {
                InventoryHelper.dropInventoryItems(world, pos, (ShelvesTileEntity) tileentity);
                world.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World world, BlockPos pos) {
        TileEntity tileentity = world.getTileEntity(pos);
        if (tileentity instanceof ShelvesTileEntity)
            return Container.calcRedstoneFromInventory((ShelvesTileEntity) tileentity);
        else
            return 0;
    }
}
