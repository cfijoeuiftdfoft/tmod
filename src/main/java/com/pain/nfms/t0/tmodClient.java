package com.pain.nfms.t0;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
// import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

// @Mod(value = tmod.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = "tmod", bus = Bus.MOD, value = Dist.CLIENT)
public class tmodClient {
    public tmodClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    @SuppressWarnings("null")
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TModelLayers.RebroModelLocation, RebroModel::createBodyLayer);
        event.registerLayerDefinition(TModelLayers.METLA_LAYER, MetlaModel::createLayer);
    }
    
    @SubscribeEvent
    @SuppressWarnings("null")
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TEntities.REBRO.get(), RebroRenderer::new);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            private BlockEntityWithoutLevelRenderer renderer;
            
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    var modelPart = Minecraft.getInstance().getEntityModels()
                        .bakeLayer(TModelLayers.METLA_LAYER);
                    renderer = new MetlaRenderer(new MetlaModel(modelPart));
                }
                return renderer;
            }
        }, TItems.METLA.get());
    }
}
