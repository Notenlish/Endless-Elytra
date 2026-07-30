package com.endlesselytra.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "hurtAndBreak", cancellable = true)
	private void preventElytraDamage(
			int damage,
			ServerLevel level,
			ServerPlayer player,
			Consumer<Item> onBreak,
			CallbackInfo ci
	) {
		if (((ItemStack) (Object) this).is(Items.ELYTRA)) {
			ci.cancel();
		}
	}
}