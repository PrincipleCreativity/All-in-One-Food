package org.principlecreativity.aiof.object.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import org.principlecreativity.aiof.Main;
import org.principlecreativity.aiof.init.ItemRegister;

public class CannedFood extends Item {
    public CannedFood(int nutrition, float saturationMod, boolean isMeat) {
        super(new Properties().group(Main.TAB).maxStackSize(48).food(stew(nutrition, saturationMod, isMeat)));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            if(!player.isCreative()) {
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ItemRegister.EMPTY_CAN.get()));
            }
        }
        return super.onItemUseFinish(stack, world, entity);
    }

    private static Food stew(int nutrition, float saturationMod, boolean isMeat) {
        if (isMeat) {
            return (new Food.Builder()).hunger(nutrition).saturation(saturationMod).meat().build();
        }
        return (new Food.Builder()).hunger(nutrition).saturation(saturationMod).build();
    }
}
