package org.argo.aetheria_core.mixin;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.argo.aetheria_core.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Allows to completely disable any item from a config
@Mixin(value = Slot.class)
public class SlotMixin {
    @Inject(method = "mayPlace", at = @At("HEAD"), cancellable = true)
    private void blockDisabledItems(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (Config.isDisabled(stack)) {
            stack.setCount(0);
            cir.setReturnValue(false);
        }
    }
}
