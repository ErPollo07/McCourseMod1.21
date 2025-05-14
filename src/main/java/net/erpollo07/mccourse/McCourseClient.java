package net.erpollo07.mccourse;

import net.erpollo07.mccourse.item.ruler.RulerItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

public class McCourseClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    WorldRenderEvents.AFTER_ENTITIES.register(context -> {
      MinecraftClient client = MinecraftClient.getInstance();

      if (client.player != null && client.world != null) {
        ItemStack mainHand = client.player.getMainHandStack();

        if (mainHand.getItem() instanceof RulerItem ruler) {
          ruler.renderOverlay(context.matrixStack(), context.camera());
        }
      }
    });
  }
}
