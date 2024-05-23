package org.embeddedt.embeddium.mixin.core.render.frustum;

import org.embeddedt.embeddium.render.viewport.frustum.SimpleFrustum;
import net.minecraft.client.renderer.culling.Frustum;
import org.embeddedt.embeddium.render.viewport.Viewport;
import org.embeddedt.embeddium.render.viewport.ViewportProvider;
import org.joml.FrustumIntersection;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Frustum.class)
public class FrustumMixin implements ViewportProvider {
    @Shadow
    private double camX;

    @Shadow
    private double camY;

    @Shadow
    private double camZ;

    @Shadow
    @Final
    private FrustumIntersection intersection;

    @Override
    public Viewport sodium$createViewport() {
        return new Viewport(new SimpleFrustum(this.intersection), new Vector3d(this.camX, this.camY, this.camZ));
    }
}
