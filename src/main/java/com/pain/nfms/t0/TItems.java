package com.pain.nfms.t0;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.food.FoodProperties;

public class TItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(tmod.MODID);

    public static final DeferredItem<Item> I0 = ITEMS.register(
        "i0",
        () -> new Item(new Item.Properties()
        .stacksTo(2147483647)
        .rarity(Rarity.RARE)
        .food(new FoodProperties.Builder()
        .alwaysEdible()
        .nutrition(1)
        .saturationModifier(2f)
        .build())
    ));
}
