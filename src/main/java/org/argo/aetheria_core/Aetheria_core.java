package org.argo.aetheria_core;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.argo.aetheria_core.attachment.ModAttachments;
import org.slf4j.Logger;

@Mod(Aetheria_core.MODID)
public class Aetheria_core {
    public static final String MODID = "aetheria_core";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Aetheria_core(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModAttachments.register(modEventBus);
    }
}
