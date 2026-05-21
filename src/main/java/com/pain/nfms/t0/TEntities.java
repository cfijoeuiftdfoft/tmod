package com.pain.nfms.t0;

// import net.neoforged.bus.api.SubscribeEvent;
// import net.neoforged.fml.common.EventBusSubscriber;
// import net.neoforged.jarjar.nio.util.LambdaExceptionUtils.Supplier_WithExceptions;
// import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import net.minecraft.core.registries.BuiltInRegistries;
// import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
// import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
// import net.neoforged.bus.api.SubscribeEvent;
// import net.neoforged.fml.common.EventBusSubscriber;

public class TEntities {
    @SuppressWarnings("null")
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, tmod.MODID);

    public static final Supplier<EntityType<Rebro>> REBRO = ENTITIES.register("rebro",
        () -> EntityType.Builder.of(Rebro::new, MobCategory.MONSTER)
        .sized(0.9f, 1.5f)
        .build("rebro")
    );
}
