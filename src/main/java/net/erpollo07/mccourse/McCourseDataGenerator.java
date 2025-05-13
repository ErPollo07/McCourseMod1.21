package net.erpollo07.mccourse;

import net.erpollo07.mccourse.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class McCourseDataGenerator implements DataGeneratorEntrypoint {
  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

    pack.addProvider(ModBlockTagProvider::new);
    pack.addProvider(ModItemTagProvider::new);
    pack.addProvider(ModLootTableGenerator::new);
    pack.addProvider(ModModelProvider::new);
    pack.addProvider(ModRecipeGenerator::new);
  }
}
