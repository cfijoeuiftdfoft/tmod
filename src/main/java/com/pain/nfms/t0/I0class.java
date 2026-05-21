package com.pain.nfms.t0;

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

public class I0class extends Item {
    private final EntityType<?> entityType;
    public I0class(Properties properties, EntityType<?> entityType) {
        super(properties);
        this.entityType = entityType;
    }

    @Override
    @SuppressWarnings("null")
    public InteractionResult useOn(UseOnContext context) {
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
}
