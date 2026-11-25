package org.argo.aetheria_core.attachment;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.argo.aetheria_core.Aetheria_core;

import java.util.function.Supplier;

public class ModAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(
            NeoForgeRegistries.ATTACHMENT_TYPES, Aetheria_core.MODID);

    public static final Supplier<AttachmentType<Integer>> MANA = ATTACHMENT_TYPES.register(
            "mana", () -> AttachmentType.builder(() -> 0)
                    .serialize(Codec.INT.fieldOf("mana").codec()).build());

    public static final Supplier<AttachmentType<Integer>> CORRUPTION = ATTACHMENT_TYPES.register(
            "", () -> AttachmentType.builder(() -> 0)
                    .serialize(Codec.INT.fieldOf("corruption").codec()).build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
