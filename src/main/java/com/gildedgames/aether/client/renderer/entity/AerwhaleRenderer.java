package com.gildedgames.aether.client.renderer.entity;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.client.renderer.entity.model.AerwhaleModel;
import com.gildedgames.aether.client.renderer.entity.model.BaseAerwhaleModel;
import com.gildedgames.aether.client.renderer.entity.model.OldAerwhaleModel;
import com.gildedgames.aether.common.entity.passive.AerwhaleEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AerwhaleRenderer extends MobRenderer<AerwhaleEntity, BaseAerwhaleModel>
{
    private static final ResourceLocation AERWHALE_TEXTURE = new ResourceLocation(Aether.MODID, "textures/entity/mobs/aerwhale/aerwhale.png");
    private static final ResourceLocation OLD_AERWHALE_TEXTURE = new ResourceLocation(Aether.MODID, "textures/entity/mobs/aerwhale/old_aerwhale.png");

    private final AerwhaleModel regularModel;
    private final OldAerwhaleModel oldModel;
    
    public AerwhaleRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AerwhaleModel(), 0.5F);
        this.regularModel = (AerwhaleModel) this.model;
        this.oldModel = new OldAerwhaleModel();
    }
    
    private Object _data;
    private static Object _staticData;

    @Override
    protected void scale(AerwhaleEntity aerwhale, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.translate(0, 1.2, 0);
//        if (_staticData == null) {
//        	_staticData = new float[] {aerwhale.rotationYaw, aerwhale.rotationPitch};
//        }
//        float[] prevRotations = (float[]) _staticData;
//        float prevRotationYaw = prevRotations[0];
//        float prevRotationPitch = prevRotations[1];
        
        Vector3d look = aerwhale.getDeltaMovement().normalize();//getLook(partialTickTime);
        
        float yaw = (float)(MathHelper.atan2(look.z, look.x) * 180.0 / Math.PI);
        float pitch = -(float)(Math.atan(look.y) * 73.0);
//        yaw = MathHelper.lerp(partialTickTime, aerwhale.prevRotationYaw, yaw);
//        float yaw = MathHelper.lerp(partialTickTime, aerwhale.prevRotationYaw, aerwhale.rotationYaw);
//        float pitch = aerwhale.rotationPitch;
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(yaw + 0.0F));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(pitch));
//        matrixStackIn.rotate(new Quaternion(Vector3f.ZP, 90.0F, true));
        matrixStackIn.scale(2.0F, 2.0F, 2.0F);
        
//        if (yaw != prevRotationYaw || pitch != prevRotationPitch) {
////        	System.out.printf("Aerwhale world = %s\n", aerwhale.world);
//        	float motionYaw = MathHelper.wrapDegrees((float)Math.atan2(aerwhale.getMotion().z, aerwhale.getMotion().x) * (180.0F / (float)Math.PI) - 90.0F); 
//        	System.out.printf("Get aerwhale (pitch, yaw) =  %+7.2f, %+7.2f  [(x, z):                                       Δ (%+6.2f, %+6.2f)] %+7.2f\n", pitch, MathHelper.wrapDegrees(yaw), aerwhale.getMotion().x, aerwhale.getMotion().z, motionYaw);
//        	prevRotations[0] = yaw;
//        	prevRotations[1] = pitch;
//        }
    }

    @Override
    public void render(AerwhaleEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
    	super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
    
    @Override	//TODO: Configurable old aerwhale model
    public BaseAerwhaleModel getModel() {
    	return regularModel;
    }

    @Override   //TODO: Configurable old aerwhale texture
    public ResourceLocation getTextureLocation(AerwhaleEntity entity) {
        return AERWHALE_TEXTURE;
    }
}

