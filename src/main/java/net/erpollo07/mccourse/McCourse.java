package net.erpollo07.mccourse;

import net.erpollo07.mccourse.item.ModItemGroups;
import net.erpollo07.mccourse.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public class McCourse implements ModInitializer {
  public static final String MOD_ID = "mccourse";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    ModItems.registerModItems();

    ModItemGroups.registerItemGroup();
  }
}
