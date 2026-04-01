package org.samo_lego.fabrictailor.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.PropertyMap;
import org.samo_lego.fabrictailor.casts.TailoredGameProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = GameProfile.class, remap = false)
public class MixinGameProfile implements TailoredGameProfile {
    @Unique
    PropertyMap overrideProperties = null;

    @Override
    public void tailor$overrideProperties(PropertyMap map) {
        overrideProperties = map;
    }

    @ModifyReturnValue(method = "properties", at = @At("RETURN"))
    private PropertyMap overrideProperties(PropertyMap original) {
        return overrideProperties == null ? original : overrideProperties;
    }
}
