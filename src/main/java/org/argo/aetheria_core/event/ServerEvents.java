package org.argo.aetheria_core.event;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import org.argo.aetheria_core.Aetheria_core;
import org.argo.aetheria_core.Config;

@EventBusSubscriber(modid = Aetheria_core.MODID)
public class ServerEvents {
    // Disable aggressive mob spawn, that are not from custom mods
    @SubscribeEvent
    public static void onMobSpawn(FinalizeSpawnEvent event) {
        ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType());

        if (!id.getNamespace().contains("aetheria")
                && event.getEntity().getType().getCategory() == MobCategory.MONSTER) {
            event.setSpawnCancelled(true);
        }
    }

    // Stops disabled items from being picked up
    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Pre event) {
        if (Config.isDisabled(event.getItemEntity().getItem())) {
            event.getItemEntity().getItem().setCount(0);
        }
    }
}