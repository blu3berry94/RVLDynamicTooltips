package net.tokyosu.raritymod.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.tokyosu.raritymod.utils.RarityUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("ALL")
@Mixin(ItemStack.class)
public abstract class ItemRarityMixin {
    @Shadow public abstract Item getItem();
    @Shadow public abstract CompoundTag getTag();

    @Inject(method = "getRarity", at = @At("RETURN"), cancellable = true)
    private void changeRarity(CallbackInfoReturnable<Rarity> ci) {
        CompoundTag nbt = this.getTag();
        if (nbt != null) {
            Rarity rarity = RarityUtils.GetRarityFromNBT(nbt, ci.getReturnValue());
            if (rarity != null) {
                ci.setReturnValue(rarity);
            }
        }
    }
}