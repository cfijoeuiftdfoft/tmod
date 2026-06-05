package com.pain.nfms.t0;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
// import net.minecraft.world.level.block.Block;
// import net.minecraft.world.level.block.Blocks;
// import net.minecraft.world.level.block.Block;
// import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ClientHooks.ClientEvents;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class Metla extends Item {
    private final EntityType<?> entityType;
    public Metla(Properties properties, EntityType<?> entityType) {
        super(properties);
        this.entityType = entityType;
    }

    @Override
    @SuppressWarnings("null")
    public InteractionResult useOn(@Nonnull UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        // BlockState oldState = level.getBlockState(pos);
        Player player = context.getPlayer();
        BlockPos spawnPos = pos.relative(context.getClickedFace());
        if(level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if(level instanceof ServerLevel serverLevel) {
            entityType.spawn(serverLevel, null, player, spawnPos, MobSpawnType.SPAWN_EGG, true, false);
            serverLevel.levelEvent(2007, spawnPos, 0);
            if(player != null && !player.isCreative()) {
                context.getItemInHand().shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }

    // @Override
    // public void initializeClient(Consumer<IClientItemExtensions> consumer) {
    //     consumer.accept(new IClientItemExtensions() {
    //         private MetlaRenderer renderer;
            
    //         @Override
    //         public BlockEntityWithoutLevelRenderer getCustomRenderer() {
    //             if (renderer == null) {
    //                 var modelPart = Minecraft.getInstance().getEntityModels()
    //                     .bakeLayer(TModelLayers.METLA_LAYER);
    //                 renderer = new MetlaRenderer(new MetlaModel(modelPart));
    //             }
    //             return renderer;
    //         }
    //     });
    // }
}
