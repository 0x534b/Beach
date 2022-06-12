package com.jetbrains.beach.init;

import com.jetbrains.beach.Beach;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.fonts.TexturedGlyph;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.swing.*;
import java.util.Properties;
import java.util.function.Supplier;


public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Beach.MOD_ID);

    // items?
    // wine bag !!!!!!!TheDa
    public static final RegistryObject<Item> WINE_BAG = ITEMS.register("wine_bag",
            () -> new Item(new Item.Properties().group(ModCreativeTab.instance)
                    .food(new Food.Builder().hunger(5).saturation(2)
                            .effect(() -> new EffectInstance(Effects.NAUSEA, 200, 0), 1.0F).setAlwaysEdible().build())));

    // Sunscreen advanced item
    public static final RegistryObject<Item> SUNSCREEN = ITEMS.register("sunscreen",
            () -> new SunScreen(new Item.Properties().group(ModCreativeTab.instance)));



    public static class SunScreen extends Item {
        public SunScreen(Properties properties) {
            super(properties);
        }
        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
            ITextComponent msg = new StringTextComponent(" You applied sunscreen ");
            Minecraft.getInstance().player.sendMessage(msg, Util.DUMMY_UUID);
            ItemStack stack = playerIn.getHeldItem(handIn);

            if (stack.getCount() > 1){
                stack.setCount(stack.getCount() - 1);
                return ActionResult.resultConsume(stack);
            } else {
//                playerIn.inventory.decrStackSize()
                playerIn.inventory.deleteStack(stack);
                stack = playerIn.getHeldItem(handIn);
                return ActionResult.resultConsume(stack);
            }
        }
    }

//    // Beach Ball (slightly) Advanced Item
    public static final RegistryObject<SnowballItem> BEACHBALL = ITEMS.register("beachball",
            () -> new SnowballItem(new Item.Properties().group(ModCreativeTab.instance)));




    // Mod item tab(group) in creative mode
    public static class ModCreativeTab extends ItemGroup {

        public static final ModCreativeTab instance = new ModCreativeTab(ItemGroup.GROUPS.length, "beach");

        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(BEACHBALL.get());
        }

//        public ItemStack createIcon() {
//            return new ItemStack(SUNSCREEN.get());
//        }
    }


    // Armour
    public enum ModArmorMaterial implements IArmorMaterial {
        beach_drip(Beach.MOD_ID + ":beach_drip", 20, new int[]{4, 7, 9, 4}, 50,
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F, 0.1F, () -> {
            return Ingredient.fromItems(ItemInit.WINE_BAG.get());
        });

        private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
        private final String name;
        private final int durabilityMultiplier;
        private final int[] slotProtections;
        private final int enchantmentValue;
        private final SoundEvent sound;
        private final float toughness;
        private final float knockbackResistance;
        private final LazyValue<Ingredient> repairIngredient;

        ModArmorMaterial(String name, int durability, int[] protection, int enchantability, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
            this.name = name;
            this.durabilityMultiplier = durability;
            this.slotProtections = protection;
            this.enchantmentValue = enchantability;
            this.sound = sound;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairIngredient = new LazyValue<>(repairIngredient);
        }

//        public int getDurabilityForSlot(EquipmentSlotType slot) {
        public int getDurability(EquipmentSlotType slot) {
            return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
        }

        public int getDamageReductionAmount(EquipmentSlotType slot) {
            return this.slotProtections[slot.getIndex()];
        }

        public int getEnchantability() {
            return this.enchantmentValue;
        }

        public SoundEvent getSoundEvent() {
            return this.sound;
        }

        public Ingredient getRepairMaterial() {
            return this.repairIngredient.getValue();
        }

        @OnlyIn(Dist.CLIENT)
        public String getName() {
            return this.name;
        }

        public float getToughness() {
            return this.toughness;
        }

        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }

    }


    // Armour init
    public static final RegistryObject<Item> BEACH_HAT = ITEMS.register("beach_helm",
            () -> new ArmorItem(ModArmorMaterial.beach_drip, EquipmentSlotType.HEAD, new Item.Properties().group(ModCreativeTab.instance)));

    public static final RegistryObject<Item> BEACH_CHEST = ITEMS.register("beach_chest",
            () -> new ArmorItem(ModArmorMaterial.beach_drip, EquipmentSlotType.CHEST, new Item.Properties().group(ModCreativeTab.instance)));

    public static final RegistryObject<Item> BEACH_PANTS = ITEMS.register("beach_pants",
            () -> new ArmorItem(ModArmorMaterial.beach_drip, EquipmentSlotType.LEGS, new Item.Properties().group(ModCreativeTab.instance)));

    public static final RegistryObject<Item> BEACH_TOES = ITEMS.register("beach_toes",
            () -> new ArmorItem(ModArmorMaterial.beach_drip, EquipmentSlotType.FEET, new Item.Properties().group(ModCreativeTab.instance)));

}

