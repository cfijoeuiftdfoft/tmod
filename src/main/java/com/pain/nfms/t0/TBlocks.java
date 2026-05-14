package com.pain.nfms.t0;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
// import net.minecraft.core.registries.BuiltInRegistries;
// import net.minecraft.core.registries.Registries;
// import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;

public class TBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(tmod.MODID);

    @SuppressWarnings("null")
    public static final DeferredBlock<Block> B0 = BLOCKS.register(
    "b0", 
        () -> new Block(BlockBehaviour.Properties.of()
            .destroyTime(2.0f)
            .sound(SoundType.GRAVEL)
            .explosionResistance(10.0f)
            .lightLevel(state -> 7)
    ));
}
