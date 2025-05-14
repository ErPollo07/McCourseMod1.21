package net.erpollo07.mccourse.item.ruler;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class RulerSelectionHandler {
  public static void drawBox(BlockPos pos1, BlockPos pos2, Vec3d camPos, MatrixStack matrices) {
    MinecraftClient client = MinecraftClient.getInstance();

    double minX = Math.min(pos1.getX(), pos2.getX());
    double minY = Math.min(pos1.getY(), pos2.getY());
    double minZ = Math.min(pos1.getZ(), pos2.getZ());

    double maxX = Math.max(pos1.getX(), pos2.getX()) + 1;
    double maxY = Math.max(pos1.getY(), pos2.getY()) + 1;
    double maxZ = Math.max(pos1.getZ(), pos2.getZ()) + 1;

    // Create the box
    Box box = new Box(minX, minY, minZ, maxX, maxY, maxZ)
            .offset(-camPos.x, -camPos.y, -camPos.z);

    // Render the box
    WorldRenderer.drawBox(
            matrices,
            client.getBufferBuilders().getEntityVertexConsumers().getBuffer(RenderLayer.getLines()),
            box,
            1f, 0f, 0f, 1f // rosso
    );
  }
}
