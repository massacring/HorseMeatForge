package me.massacring.horsemeatforge.item.custom;

import me.massacring.horsemeatforge.HorseMeatForge;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HorseMeatForge.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab HORSEMEAT_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        HORSEMEAT_TAB = event.registerCreativeModeTab(new ResourceLocation(HorseMeatForge.MOD_ID, "horsemeatforge_tab"),
                builder -> builder.icon(() -> new ItemStack(ModFoods.RAW_HORSE_MEAT.get()))
                        .title(Component.translatable("creativemodetab.horsemeatforge_tab")));
    }
}
