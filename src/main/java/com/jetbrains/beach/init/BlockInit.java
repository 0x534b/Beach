package com.jetbrains.beach.init;

import com.jetbrains.beach.Beach;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Beach.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> PORTABLE_TABLE_BLOCK = BLOCKS.register("portable_table",
            () -> new Block(AbstractBlock.Properties.create(Material.GLASS).notSolid().setOpaque((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false)));
//            .setRenderLayer();
                    //.setBlocksVision().setLightLevel(s -> 0).notSolid().setOpaque((bs, br, bp) -> false)));

    // Advanced Blocks
    public static final RegistryObject<BeachTowel> BEACH_TOWEL = BLOCKS.register("beach_towel",
            () -> new BeachTowel(AbstractBlock.Properties.create(Material.CARPET, MaterialColor.WOOL).zeroHardnessAndResistance().notSolid()));

    public static class BeachTowel extends BedBlock {
        public BeachTowel(AbstractBlock.Properties properties) {
            super(DyeColor.WHITE, properties);
        }

        public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                                 PlayerEntity player, Hand handIn) {
            if(!worldIn.isRemote()) {
                if(handIn == Hand.MAIN_HAND) {
                    player.startSleeping(pos);
                }
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.PASS;
        }

    }


    // auto generate the item for the blocks
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
            final Item.Properties properties = new Item.Properties().group(ItemInit.ModCreativeTab.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }
}


