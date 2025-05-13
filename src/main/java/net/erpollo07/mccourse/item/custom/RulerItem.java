package net.erpollo07.mccourse.item.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.ArrayList;

public class RulerItem extends Item {
  private ArrayList<BlockPos> positions = new ArrayList<>();

  public RulerItem(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    World world = context.getWorld();

    if (!world.isClient) {
      return ActionResult.SUCCESS;
    }

    // Output the coordinates of the block that has been right-clicked
    BlockPos pos = context.getBlockPos();

    context.getPlayer().sendMessage(Text.of("{X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ() + "}"));

    // add position in the array list
    positions.add(pos);

    // check if the positions array is full
    if (positions.size() == 2) {
      // if it is then show a message
      context.getPlayer().sendMessage(Text.of("Area: " + calculateArea()));
      // clear the list
      positions.clear();
    }

    return ActionResult.SUCCESS;
  }

  private int calculateArea() {
    int deltaX = Math.abs(positions.get(0).getX() - positions.get(1).getX()) + 1;
    int deltaZ = Math.abs(positions.get(0).getZ() - positions.get(1).getZ()) + 1;
    int deltaY = Math.abs(positions.get(0).getY() - positions.get(1).getY()) + 1;

    return deltaX * deltaZ * deltaY;
  }

  public void renderOverlay(MatrixStack matrices, Camera camera, World world) {
    if (positions.isEmpty()) return;

    MinecraftClient client = MinecraftClient.getInstance();
    HitResult hit = client.crosshairTarget;

    if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

    Vec3d camPos = camera.getPos();

    BlockPos pos1 = positions.getFirst();
    BlockPos pos2 = ((BlockHitResult) hit).getBlockPos();

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

  private ArrayList<BlockPos> getPositions() {
    return positions;
  }
}
