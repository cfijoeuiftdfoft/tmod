package com.pain.nfms.t0;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(testneoforgemod0.MODID);

    public static final DeferredBlock<Block> MY_BETTER_BLOCK = BLOCKS.register(
    "my_better_block", 
        registryName -> new Block(BlockBehaviour.Properties.of()
            // .setId(ResourceKey.create(Registries.BLOCK, registryName))
            .destroyTime(2.0f)
            .explosionResistance(10.0f)
            .lightLevel(state -> 7)
    ));
}
