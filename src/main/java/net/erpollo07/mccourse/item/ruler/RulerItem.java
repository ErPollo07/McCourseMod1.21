package net.erpollo07.mccourse.item.ruler;

import net.fabricmc.loader.impl.lib.sat4j.specs.IConstr;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
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
import net.minecraft.world.World;

import java.util.ArrayList;

public class RulerItem extends Item {
  public RulerItem(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    World world = context.getWorld();

    if (!world.isClient) {
      return ActionResult.SUCCESS;
    }

    BlockPos pos = context.getBlockPos();

    if (RulerState.hasFirstPos()) {
      MinecraftClient client = MinecraftClient.getInstance();
      HitResult hit = client.crosshairTarget;
      if (hit != null && hit.getType() == HitResult.Type.BLOCK) {
        BlockHitResult blockHitResult = (BlockHitResult) hit;
        BlockPos crosshairPos = blockHitResult.getBlockPos();

        context.getPlayer().sendMessage(Text.of("Area: " + calculateArea(crosshairPos)));
      }

      RulerState.reset();
    }
    else {
      RulerState.setFirstPos(pos);
    }

    return ActionResult.SUCCESS;
  }

  private int calculateArea(BlockPos secondPos) {
    int deltaX = Math.abs(RulerState.getFirstPos().getX() - secondPos.getX()) + 1;
    int deltaZ = Math.abs(RulerState.getFirstPos().getZ() - secondPos.getZ()) + 1;
    int deltaY = Math.abs(RulerState.getFirstPos().getY() - secondPos.getY()) + 1;

    return deltaX * deltaZ * deltaY;
  }

  public void renderOverlay(MatrixStack matrices, Camera camera) {
    if (RulerState.hasFirstPos()) return;

    MinecraftClient client = MinecraftClient.getInstance();
    HitResult hit = client.crosshairTarget;

    if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

    Vec3d camPos = camera.getPos();
    BlockPos pos2 = ((BlockHitResult) hit).getBlockPos();

    RulerSelectionHandler.drawBox(RulerState.getFirstPos(), pos2, camPos, matrices);
  }
}
