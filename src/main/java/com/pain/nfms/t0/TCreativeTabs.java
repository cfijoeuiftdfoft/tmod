package com.pain.nfms.t0;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.network.chat.Component;

public class TCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, tmod.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_MODE_TAB_0 = CREATIVE_MODE_TABS.register(
        "ct0",
        () -> CreativeModeTab.builder()
        .title(Component.translatable("itemGroup." + tmod.MODID + ".example"))
        .icon(() -> new ItemStack(TItems.I0.get()))
        .displayItems((parameters, output) -> {
            output.accept(TItems.I0.get());
        })
        .build()
    );
}
