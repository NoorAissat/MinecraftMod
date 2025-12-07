package com.noor.stitchcraft.item.custom;



import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.noor.stitchcraft.client.ShadowScytheRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;

import java.util.function.Consumer;

public class ShadowScytheItem extends Item implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);


    public ShadowScytheItem(Properties pProperties) {
        super(pProperties);
    }

    private int getFinisherUseCount(ItemStack stack) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        if (data == CustomData.EMPTY) return 0;
        return data.copyTag().getInt("shadow_finisher_uses");
    }

    private void setFinisherUseCount(ItemStack stack, int value) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = (data == CustomData.EMPTY) ? new CompoundTag() : data.copyTag();
        tag.putInt("shadow_finisher_uses", value);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }




    // ------------------------------------------------------------
    // RIGHT CLICK → FINISHER LOGIC
    // ------------------------------------------------------------
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            LivingEntity target = getLookTarget(player, 7.0D);
            if (target != null && target.isAlive()) {

                performFinisher(player, target, stack);

                int uses = getFinisherUseCount(stack) + 1;
                setFinisherUseCount(stack, uses);

                if (uses >= 3) {
                    player.getCooldowns().addCooldown(this, 60);
                    setFinisherUseCount(stack, 0);
                }
            }
        }


        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }




    // ------------------------------------------------------------
    // TELEPORT / DAMAGE / ANIMATION TRIGGER
    // ------------------------------------------------------------
    private void performFinisher(Player player, LivingEntity target, ItemStack stack) {

        // Calculate direction behind target
        Vec3 dir = player.position().subtract(target.position()).normalize();
        Vec3 behind = target.position().subtract(dir.scale(1.5));

        for (int i = 0; i < 20; i++) {
            double ox = (player.level().random.nextDouble() - 0.5) * 0.5;
            double oy = player.level().random.nextDouble() * 1.5;
            double oz = (player.level().random.nextDouble() - 0.5) * 0.5;

            player.level().addParticle(
                    ParticleTypes.SMOKE,
                    player.getX() + ox,
                    player.getY() + oy,
                    player.getZ() + oz,
                    0, 0, 0
            );
        }


        // Teleport player
        player.teleportTo(behind.x, target.getY(), behind.z);

        player.level().scheduleTick(player.blockPosition(), player.level().getBlockState(player.blockPosition()).getBlock(), 1);

        player.level().getGameRules().getRule(GameRules.RULE_RANDOMTICKING).set(0, player.level().getServer());


        // Make player face target
        player.lookAt(EntityAnchorArgument.Anchor.EYES, target.position());

        // Deal strong damage
        target.invulnerableTime = 0;
        target.hurt(player.damageSources().playerAttack(player), 20.0f);

        player.level().addParticle(ParticleTypes.CRIT,
                target.getX(), target.getY() + 1.0, target.getZ(),
                0.1, 0.2, 0.1);

        player.level().addParticle(ParticleTypes.SWEEP_ATTACK,
                target.getX(), target.getY() + 1.2, target.getZ(),
                0, 0, 0);



        // Trigger animation
        markFinisher(stack);
    }


    // ------------------------------------------------------------
    // RAYCAST TARGET
    // ------------------------------------------------------------
    private LivingEntity getLookTarget(Player player, double distance) {
        Level level = player.level();
        Vec3 eye = player.getEyePosition();
        Vec3 reach = eye.add(player.getLookAngle().scale(distance));

        AABB box = player.getBoundingBox()
                .expandTowards(player.getLookAngle().scale(distance))
                .inflate(1.0);

        EntityHitResult hit = ProjectileUtil.getEntityHitResult(
                level, player, eye, reach, box,
                e -> e instanceof LivingEntity && e != player && e.isPickable()
        );

        return hit != null ? (LivingEntity) hit.getEntity() : null;
    }


    // ------------------------------------------------------------
    // CUSTOM DATA COMPONENT (NBT replacement)
    // ------------------------------------------------------------
    private void markFinisher(ItemStack stack) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, (CompoundTag tag) -> {
            tag.putInt("finisher_ticks", 60);
        });
    }

    private int getFinisherTicks(ItemStack stack) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        return data.copyTag().getInt("finisher_ticks");
    }

    private void tickFinisher(ItemStack stack) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, (CompoundTag tag) -> {
            int t = tag.getInt("finisher_ticks");
            if (t > 0) tag.putInt("finisher_ticks", t - 1);
        });
    }

    // Countdown finisher every tick if selected
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        // FINISHER COUNTER (SERVER)
        if (!level.isClientSide && selected) {
            tickFinisher(stack);
        }


    }




    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private final ShadowScytheRenderer renderer = new ShadowScytheRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }


        });
    }






    // ------------------------------------------------------------
    // GECKOLIB SETUP
    // ------------------------------------------------------------
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

        controllers.add(
                new AnimationController<>(
                        this,
                        "controller",
                        0,
                        state -> {

                            ItemStack stack = state.getData(DataTickets.ITEMSTACK);
                            int t = getFinisherTicks(stack);

                            if (t > 0) {
                                state.setAnimation(
                                        RawAnimation.begin().then("finisher", Animation.LoopType.PLAY_ONCE)
                                );
                                return PlayState.CONTINUE;
                            }

                            return PlayState.STOP;
                        }
                )
        );
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
