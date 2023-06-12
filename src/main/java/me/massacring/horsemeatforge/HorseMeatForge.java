package me.massacring.horsemeatforge;

import com.mojang.logging.LogUtils;
import me.massacring.horsemeatforge.item.custom.ModFoods;
import me.massacring.horsemeatforge.item.custom.ModCreativeModeTabs;
import me.massacring.horsemeatforge.loot.ModLootModifiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HorseMeatForge.MOD_ID)
public class HorseMeatForge {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "horsemeatforge";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public HorseMeatForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModFoods.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ModCreativeModeTabs.HORSEMEAT_TAB) {
            event.accept(ModFoods.RAW_HORSE_MEAT);
            event.accept(ModFoods.COOKED_HORSE_MEAT);
            event.accept(ModFoods.RAW_WOLF_MEAT);
            event.accept(ModFoods.COOKED_WOLF_MEAT);
            event.accept(ModFoods.RAW_CAT_MEAT);
            event.accept(ModFoods.COOKED_CAT_MEAT);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
