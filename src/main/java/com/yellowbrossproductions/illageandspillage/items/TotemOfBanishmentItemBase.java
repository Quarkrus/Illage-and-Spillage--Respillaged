package com.yellowbrossproductions.illageandspillage.items;

import com.yellowbrossproductions.illageandspillage.util.IllageAndSpillageSoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Iterator;
import java.util.List;

public class TotemOfBanishmentItemBase extends Item {
    public TotemOfBanishmentItemBase() {
        super(new Item.Properties());
    }

    public InteractionResultHolder<ItemStack> use(Level p_77659_1_, Player p_77659_2_, InteractionHand p_77659_3_) {
        List<Vex> list = p_77659_2_.level().getEntitiesOfClass(Vex.class, p_77659_2_.getBoundingBox().inflate(20.0));
        if (!list.isEmpty()) {
            Vex vex;
            for (Iterator<Vex> var5 = list.iterator(); var5.hasNext(); vex.deathTime = 19) {
                vex = var5.next();
                vex.kill();
            }

            p_77659_2_.playSound(IllageAndSpillageSoundEvents.TOTEM_BANISHMENT.get(), 1.0F, 1.0F);
            p_77659_2_.getCooldowns().addCooldown(this, 300);
        }

        return super.use(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
