package collin.interactive.lib;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.*;
import net.minecraft.container.ContainerType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleType;
import net.minecraft.potion.Potion;
import net.minecraft.sound.SoundEvent;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.BiomeSourceConfig;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.level.LevelProperties;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.function.BiFunction;
import java.util.function.Function;

public class InteractiveTasks {
    public static void registerItem(String name, Item item, String MODID) {
        Registry.register(Registry.ITEM, new Identifier(MODID, name), item);
    }

    public static void registerBlock(String name, Block block, ItemGroup group, String MODID) {
        Registry.register(Registry.BLOCK, new Identifier(MODID, name), block);
        registerItem(name, new BlockItem(block, new Item.Settings().group(group)), MODID);
    }

    public static void registerBlock(String name, Block block, String MODID) {
        Registry.register(Registry.BLOCK, new Identifier(MODID, name), block);
    }

    public static void registerTree(String name, String MODID, ItemGroup group) {
        LeavesBlock temp_leaves = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).ticksRandomly().build());
        LogBlock temp_log = new LogBlock(MaterialColor.BROWN, FabricBlockSettings.copy(Blocks.OAK_LOG).build());
        Block temp_planks = new Block(FabricBlockSettings.copy(Blocks.OAK_WOOD).build());
        StairsBlock temp_stairs = new StairsBlock(temp_planks.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_WOOD).dropsNothing().build()) {
        };
        SlabBlock temp_slab = new SlabBlock(FabricBlockSettings.copy(temp_planks).build());
        registerBlock((name + "_leaves"), temp_leaves, group, MODID);
        registerBlock((name + "_log"), temp_log, group, MODID);
        registerBlock((name + "_planks"), temp_planks, group, MODID);
        registerBlock((name + "_stairs"), temp_stairs, group, MODID);
        registerBlock((name + "_slab"), temp_slab, group, MODID);
    }

    public static void registerDimensionType(FabricDimensionType dimtype, boolean skylight, BiFunction<World, DimensionType, ? extends Dimension> dimension, EntityPlacer placer, String MODID, String name) {
        dimtype = FabricDimensionType.builder().skyLight(skylight).factory(dimension).defaultPlacer(placer).buildAndRegister(new Identifier(MODID, name));
    }
    public static <C extends BiomeSourceConfig, T extends BiomeSource> BiomeSourceType registerBiomeSourceType(BiomeSourceType sourceType, String MODID, String NAME, Function<C, T> biomeSource, Function<LevelProperties, C> function) {
        return sourceType = Registry.register(Registry.BIOME_SOURCE_TYPE, new Identifier(MODID, NAME), new BiomeSourceType<>(biomeSource, function));
    }

    public static void registerBiome(String name, Biome biome, String MODID) {
        Registry.register(Registry.BIOME, new Identifier(MODID, name), biome);
    }

    public static void registerFeature(String name, Feature feature, String MODID) {
        Registry.register(Registry.FEATURE, new Identifier(MODID, name), feature);
    }

    public static void registerFoliagePlacer(String name, FoliagePlacerType foliagePlacerType, String MODID) {
        Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(MODID, name), foliagePlacerType);
    }

    public static void registerSoundEvent(String name, SoundEvent soundEvent, String MODID) {
        Registry.register(Registry.SOUND_EVENT, new Identifier(MODID, name), soundEvent);
    }

    public static void registerFluid(String name, Fluid fluid, String MODID) {
        Registry.register(Registry.FLUID, new Identifier(MODID, name), fluid);
    }

    public static void registerCarver(String name, Carver carver, String MODID) {
        Registry.register(Registry.CARVER, new Identifier(MODID, name), carver);
    }

    public static void registerActivity(String name, Activity activity, String MODID) {
        Registry.register(Registry.ACTIVITY, new Identifier(MODID, name), activity);
    }

    public static void registerContainerType(String name, ContainerType containerType, String MODID) {
        Registry.register(Registry.CONTAINER, new Identifier(MODID, name), containerType);
    }

    public static void registerEntityType(String name, EntityType entityType, String MODID) {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(MODID, name), entityType);
    }

    public static void registerParticleType(String name, ParticleType particleType, String MODID) {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MODID, name), particleType);
    }

    public static void registerDecorator(String name, Decorator decorator, String MODID) {
        Registry.register(Registry.DECORATOR, new Identifier(MODID, name), decorator);
    }

    public static void registerMemoryModuleType(String name, MemoryModuleType memoryModuleType, String MODID) {
        Registry.register(Registry.MEMORY_MODULE_TYPE, new Identifier(MODID, name), memoryModuleType);
    }

    public static void registerMemoryModuleType(String name, PointOfInterestType pointOfInterest, String MODID) {
        Registry.register(Registry.POINT_OF_INTEREST_TYPE, new Identifier(MODID, name), pointOfInterest);
    }

    public static void registerVillagerType(String name, VillagerType villagerType, String MODID) {
        Registry.register(Registry.VILLAGER_TYPE, new Identifier(MODID, name), villagerType);
    }

    public static void registerStructureFeature(String name, StructureFeature structureFeature, String MODID) {
        Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(MODID, name), structureFeature);
    }

    public static void registerStructurePieceType(String name, StructurePieceType structurePieceType, String MODID) {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(MODID, name), structurePieceType);
    }

    public static void registerPotion(String name, Potion potion, String MODID) {
        Registry.register(Registry.POTION, new Identifier(MODID, name), potion);
    }
}
