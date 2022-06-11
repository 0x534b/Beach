package com.jetbrains.beach.init;

import com.jetbrains.beach.Beach;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Beach.MOD_ID);

    // item?
    public static final RegistryObject<Item> WINE_BAG = ITEMS.register("wine_bag",
            () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));


//    public static class ModCreativeTab extends ItemGroup {
//
//        public static final ModCreativeTab instance = new ModCreativeTab(ItemGroup.GROUPS.length, "beach");
//
//        private ModCreativeTab(int index, String label) {
//            super(index, label);
//        }
//
//        @Override
//        public ItemStack createIcon() {
//            return new ItemStack(WINE_BAG.get());
//        }
//    }
}

