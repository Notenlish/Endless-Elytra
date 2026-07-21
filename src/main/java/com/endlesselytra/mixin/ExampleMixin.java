package com.endlesselytra.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "applyDamage", cancellable = true)
	private void preventElytraDamage(int newDamage,
	                                 @Nullable ServerPlayer player,
	                                 Consumer<Item> onBreak,
	                                 CallbackInfo ci) {
		if (((ItemStack) (Object) this).is(Items.ELYTRA)) {
			ci.cancel();
		}
	}
}