package collin.interactive.lib.chunk;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;

import java.util.function.Supplier;

public final class ChunkGeneratorType<C extends ChunkGeneratorConfig, T extends ChunkGenerator<C>> extends net.minecraft.world.gen.chunk.ChunkGeneratorType
{
    private ChunkGenFactory<C, T> factory;

    private ChunkGeneratorType(ChunkGenFactory<C, T> factory, boolean buffetScreenOption, Supplier<C> settingsSupplier)
    {
        super(null, buffetScreenOption, settingsSupplier);
        this.factory = factory;
    }

    public static <C extends ChunkGeneratorConfig, T extends ChunkGenerator<C>> ChunkGeneratorType<C, T> register(Identifier id, ChunkGenFactory<C, T> factory, Supplier<C> settingsSupplier) {
        return Registry.register(Registry.CHUNK_GENERATOR_TYPE, id, new ChunkGeneratorType<>(factory, false, settingsSupplier));
    }

    public T build(World world, BiomeSource biomeSource, C config) {
        return factory.create(world, biomeSource, config);
    }

    @FunctionalInterface
    public interface ChunkGenFactory<C extends ChunkGeneratorConfig, T extends ChunkGenerator<C>> {
        T create(World world, BiomeSource source, C config);
    }
}