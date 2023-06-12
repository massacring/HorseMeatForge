package me.massacring.horsemeatforge.item.custom;

import me.massacring.horsemeatforge.HorseMeatForge;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFoods {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HorseMeatForge.MOD_ID);

    public static final RegistryObject<Item> RAW_HORSE_MEAT = ITEMS.register("horse_meat",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COOKED_HORSE_MEAT = ITEMS.register("cooked_horse_meat",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_WOLF_MEAT = ITEMS.register("wolf_meat",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COOKED_WOLF_MEAT = ITEMS.register("cooked_wolf_meat",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_CAT_MEAT = ITEMS.register("cat_meat",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COOKED_CAT_MEAT = ITEMS.register("cooked_cat_meat",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
