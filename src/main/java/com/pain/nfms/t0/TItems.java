package com.pain.nfms.t0;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.AttributeModifierIdFix;
// import net.minecraft.world.entity.EquipmentSlot;
// import net.minecraft.world.entity.ai.attributes.AttributeModifier;
// import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;

public class TItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(tmod.MODID);

    @SuppressWarnings("null")
    public static final DeferredItem<Item> METLA = ITEMS.register(
        "metla",
        () ->  new Metla(new Item.Properties()
        .stacksTo(67)
        .rarity(Rarity.RARE)
        .attributes(ItemAttributeModifiers.builder()
            .add(Attributes.ATTACK_DAMAGE,
                 new AttributeModifier(
                     ResourceLocation.fromNamespaceAndPath(tmod.MODID, "metla_damage"),
                     5.0,
                     AttributeModifier.Operation.ADD_VALUE
                 ),
                 EquipmentSlotGroup.MAINHAND)
            .build())
        .food(new FoodProperties.Builder()
        .alwaysEdible()
        .nutrition(1)
        .saturationModifier(2f)
        .build()), TEntities.REBRO.get()
    ));
}
