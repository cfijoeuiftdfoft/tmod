package com.pain.nfms.t0;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
// import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
// import net.minecraft.world.level.block.Block;
// import net.minecraft.world.level.block.state.BlockState;

public class I0class extends Item {
    public I0class(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("null")
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        // BlockState oldState = level.getBlockState(pos);
        // Player player = context.getPlayer();

        if(level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        level.setBlock(pos, Blocks.NETHERITE_BLOCK.defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);

        return InteractionResult.SUCCESS;
    }
}
