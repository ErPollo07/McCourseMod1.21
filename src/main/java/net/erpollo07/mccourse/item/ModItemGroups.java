package net.erpollo07.mccourse.item;

import net.erpollo07.mccourse.McCourse;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
  public static final ItemGroup MOD_GROUP = Registry.register(Registries.ITEM_GROUP,
          Identifier.of(McCourse.MOD_ID, "mod_group"),
          FabricItemGroup.builder().displayName(Text.translatable("itemgroup.mod_group"))
                  .icon(() -> new ItemStack(ModItems.RULER)).entries((displayContext, entries) -> {

                    entries.add(ModItems.RULER);

                  }).build());


  public static void registerItemGroup() {
    McCourse.LOGGER.info("Registering mod items group of " + McCourse.MOD_ID);
  }
}
