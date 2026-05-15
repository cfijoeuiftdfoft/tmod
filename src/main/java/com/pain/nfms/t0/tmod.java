package com.pain.nfms.t0;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
// import net.minecraft.core.registries.BuiltInRegistries;
// import net.minecraft.core.registries.Registries;
// import net.minecraft.network.chat.Component;
// import net.minecraft.world.food.FoodProperties;
// import net.minecraft.world.item.BlockItem;
// import net.minecraft.world.item.CreativeModeTab;
// import net.minecraft.world.item.CreativeModeTabs;
// import net.minecraft.world.item.Item;
// import net.minecraft.world.item.Rarity;
// import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
// import net.minecraft.world.level.block.state.BlockBehaviour;
// import net.minecraft.world.level.material.MapColor;
// import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
// import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
// import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
// import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
// import net.neoforged.neoforge.common.NeoForge;
// import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
// import net.neoforged.neoforge.event.server.ServerStartingEvent;
// import net.neoforged.neoforge.registries.DeferredBlock;
// import net.neoforged.neoforge.registries.DeferredHolder;
// import net.neoforged.neoforge.registries.DeferredItem;
// import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(tmod.MODID)
public class tmod {
    public static final String MODID = "tmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public tmod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        TBlocks.BLOCKS.register(modEventBus);
        TBlockItems.ITEMS.register(modEventBus);
        TItems.ITEMS.register(modEventBus);
        TCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        TEntities.ENTITIES.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SuppressWarnings("null")
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(TItems.I0);
        }
    }
    
    @SuppressWarnings("null")
    private void commonSetup(FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}
