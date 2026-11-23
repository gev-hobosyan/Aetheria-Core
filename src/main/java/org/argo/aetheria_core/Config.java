package org.argo.aetheria_core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = Aetheria_core.MODID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.ConfigValue<List<? extends String>> BLOCKED_ITEMS = BUILDER.comment("A list of disabled items.").defineListAllowEmpty("items", List.of(), Config::validateItemName);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static Set<Item> blocked_items;

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        blocked_items = BLOCKED_ITEMS.get().stream().map(itemName -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName))).collect(Collectors.toSet());
    }

    public static boolean isDisabled(ItemStack itemStack) {
        return isDisabled(itemStack.getItem());
    }

    public static boolean isDisabled(Item item) {
        return blocked_items.contains(item);
    }
}
