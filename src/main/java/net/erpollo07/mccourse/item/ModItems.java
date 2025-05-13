package net.erpollo07.mccourse.item;

import net.erpollo07.mccourse.McCourse;
import net.erpollo07.mccourse.item.custom.RulerItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
  public static final Item RULER = register("ruler",
          new RulerItem(new Item.Settings()));

  private static Item register(String name, Item item) {
    return Registry.register(Registries.ITEM, Identifier.of(McCourse.MOD_ID, name), item);
  }

  private static void customIngredients(FabricItemGroupEntries entries) {
    entries.add(RULER);
  }

  public static void registerModItems() {
    McCourse.LOGGER.info("Registering Mod items for " + McCourse.MOD_ID);
  }
}
