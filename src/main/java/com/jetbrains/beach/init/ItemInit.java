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

    // items?
    public static final RegistryObject<Item> WINE_BAG = ITEMS.register("wine_bag",
            () -> new Item(new Item.Properties().group(ModCreativeTab.instance)));


    public static class ModCreativeTab extends ItemGroup {

        public static final ModCreativeTab instance = new ModCreativeTab(ItemGroup.GROUPS.length, "beach");

        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(WINE_BAG.get());
        }
    }

//    public enum ModArmorMaterial implements IArmorMaterial {
//        PINK(FirstModMain.BEACH + "Beach_Drip", 20, new int[]{4, 7, 9, 4}, 50,
//                SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0F, 0.1F, () -> {
//            return Ingredient.of(ItemInit.SMILE.get());
//        });
//
//        private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
//        private final String "Beach: Beach_Drip";
//        private final int durabilityMultiplier;
//        private final int[] slotProtections;
//        private final int enchantmentValue;
//        private final SoundEvent sound;
//        private final float toughness;
//        private final float knockbackResistance;
//        private final LazyValue<Ingredient> repairIngredient;
//
//        ModArmorMaterial(String name, int durability, int[] protection, int enchantability, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
//            this.name = name;
//            this.durabilityMultiplier = durability;
//            this.slotProtections = protection;
//            this.enchantmentValue = enchantability;
//            this.sound = sound;
//            this.toughness = toughness;
//            this.knockbackResistance = knockbackResistance;
//            this.repairIngredient = new LazyValue<>(repairIngredient);
//        }
//
//        public int getDurabilityForSlot(EquipmentSlotType slot) {
//            return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
//        }
//
//        public int getDefenseForSlot(EquipmentSlotType slot) {
//            return this.slotProtections[slot.getIndex()];
//        }
//
//        public int getEnchantmentValue() {
//            return this.enchantmentValue;
//        }
//
//        public SoundEvent getEquipSound() {
//            return this.sound;
//        }
//
//        public Ingredient getRepairIngredient() {
//            return this.repairIngredient.get();
//        }
//
//        @OnlyIn(Dist.CLIENT)
//        public String getName() {
//            return this.Beach: Beach_Drip;
//        }
//
//        public float getToughness() {
//            return this.toughness;
//        }
//
//        public float getKnockbackResistance() {
//            return this.knockbackResistance;
//        }
//    }





}

