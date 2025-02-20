package com.gildedgames.aether.common.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class FallingRandomWalkingGoal extends RandomWalkingGoal
{
    protected final float probability;

    public FallingRandomWalkingGoal(CreatureEntity creatureEntity, double speed) {
        this(creatureEntity, speed, 120, 0.001F);
    }

    public FallingRandomWalkingGoal(CreatureEntity creatureEntity, double speed, int interval) {
        this(creatureEntity, speed, interval, 0.001F);
    }

    public FallingRandomWalkingGoal(CreatureEntity creatureEntity, double speed, int interval, float probability) {
        super(creatureEntity, speed, interval);
        this.probability = probability;
    }

    @Nullable
    protected Vector3d getPosition() {
        if (this.mob.isInWaterOrBubble()) {
            Vector3d vector3d = RandomPositionGenerator.getLandPos(this.mob, 15, this.mob.getMaxFallDistance());
            return vector3d == null ? super.getPosition() : vector3d;
        } else if (!this.mob.isOnGround()) {
            Vector3d vector3d = RandomPositionGenerator.getLandPos(this.mob, 12, this.mob.getMaxFallDistance());
            return vector3d != null ? vector3d : super.getPosition();
        } else {
            return this.mob.getRandom().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.mob, 10, this.mob.getMaxFallDistance()) : super.getPosition();
        }
    }
}
