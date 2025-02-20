package com.gildedgames.aether.common.event.listeners;

import com.gildedgames.aether.client.registry.AetherSoundEvents;
import com.gildedgames.aether.common.advancement.MountTrigger;
import com.gildedgames.aether.common.entity.passive.MountableEntity;
import com.gildedgames.aether.common.entity.passive.FlyingCowEntity;
import com.gildedgames.aether.common.registry.AetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityListener
{
    @SubscribeEvent
    public static void onMountEntity(EntityMountEvent event) {
        Entity rider = event.getEntityMounting();
        Entity mount = event.getEntityBeingMounted();
        if (mount != null && rider instanceof ServerPlayerEntity) {
            MountTrigger.INSTANCE.trigger((ServerPlayerEntity) rider, mount);
        }
        if (event.isDismounting() && rider.isShiftKeyDown() && mount instanceof MountableEntity && !mount.isOnGround()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onInteractWithEntity(PlayerInteractEvent.EntityInteractSpecific event) {
        Entity target = event.getTarget();
        if ((target instanceof CowEntity || target instanceof FlyingCowEntity) && !((AnimalEntity) target).isBaby()) {
            PlayerEntity player = event.getPlayer();
            Hand hand = event.getHand();
            ItemStack heldStack = player.getItemInHand(hand);
            if (heldStack.getItem() == AetherItems.SKYROOT_BUCKET.get()) {
                if (target instanceof FlyingCowEntity) {
                    player.playSound(AetherSoundEvents.ENTITY_FLYING_COW_MILK.get(), 1.0F, 1.0F);
                } else  {
                    player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
                }
                ItemStack filledBucket = DrinkHelper.createFilledResult(heldStack, player, AetherItems.SKYROOT_MILK_BUCKET.get().getDefaultInstance());
                player.swing(hand);
                player.setItemInHand(hand, filledBucket);
            }
        }
    }
}
