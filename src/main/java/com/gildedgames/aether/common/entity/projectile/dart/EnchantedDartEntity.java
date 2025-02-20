package com.gildedgames.aether.common.entity.projectile.dart;

import com.gildedgames.aether.common.registry.AetherEntityTypes;
import com.gildedgames.aether.common.registry.AetherItems;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnchantedDartEntity extends AbstractDartEntity
{
    public EnchantedDartEntity(EntityType<? extends EnchantedDartEntity> type, World worldIn) {
        super(type, worldIn);
        this.setBaseDamage(6.0D);
    }

    public EnchantedDartEntity(World worldIn) {
        super(AetherEntityTypes.ENCHANTED_DART.get(), worldIn);
        this.setBaseDamage(6.0D);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(AetherItems.ENCHANTED_DART.get());
    }
}
