package com.jetbrains.beach.init;

import com.jetbrains.beach.Beach;
import io.netty.bootstrap.AbstractBootstrap;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.ExplosionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.spongepowered.asm.service.IPropertyKey;

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
            () -> new BeachTowel(AbstractBlock.Properties.from(Blocks.WHITE_CARPET).zeroHardnessAndResistance().notSolid()));

    public static class BeachTowel extends BedBlock {
        public static final DirectionProperty FACING = DirectionProperty.create("facing");

        public BeachTowel(AbstractBlock.Properties properties) {
            super(DyeColor.WHITE, properties);
        }

        public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
            return Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
        }

        public BlockRenderType getRenderType(BlockState state) {
            return BlockRenderType.MODEL;
        }

//        protected BlockState createBlockState(){
//            return new BlockState(this, BeachTowel.FACING);
//        }
    }



    // Fucking boat
//    public static final RegistryObject<BeachTowel> BEACH_PRESERVER = BLOCKS.register("beach_preserver",
//            () -> new BoatItem(AbstractBlock.Properties.from(Blocks.WHITE_CARPET).zeroHardnessAndResistance().notSolid()));
    // Beach Umbrella **ADVANCED** Block
//    public static final RegistryObject<Block> BEACH_PRESERVER = BLOCKS.register("beach_preserver",
//            () -> new BoatItem(Block);



    // Beach Umbrella **ADVANCED** Block
    public static final RegistryObject<Block> BEACH_UMBRELLA = BLOCKS.register("beach_umbrella",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL).notSolid().doesNotBlockMovement()));


    // Beach Tent Block
    public static final RegistryObject<Block> BEACH_TENT = BLOCKS.register("beach_tent",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL).notSolid().doesNotBlockMovement()));



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


