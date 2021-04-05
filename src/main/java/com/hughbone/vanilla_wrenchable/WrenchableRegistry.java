package com.hughbone.vanilla_wrenchable;

import com.mojang.serialization.Lifecycle;
import com.hughbone.vanilla_wrenchable.block.InstanceListener;
import com.hughbone.vanilla_wrenchable.block.BlockListener;
import com.hughbone.vanilla_wrenchable.block.PropertyListener;
import com.hughbone.vanilla_wrenchable.wrench.Wrench;
import com.hughbone.vanilla_wrenchable.wrench.WrenchListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.*;

public class WrenchableRegistry {
    public static final SimpleRegistry<BlockListener> BLOCK_LISTENERS = register("block_wrenchable");
    public static final SimpleRegistry<InstanceListener> BLOCK_INSTANCE_LISTENERS = register("block_instance_wrenchable");
    public static final SimpleRegistry<PropertyListener> PROPERTY_LISTENERS = register("property_wrenchable");

    public static final SimpleRegistry<WrenchListener> WRENCH_LISTENERS = register("wrench");

    private static <T> SimpleRegistry<T> register(String name) {
        Identifier identifier = new Identifier(WrenchableMain.MODID, name);
        return new SimpleRegistry<>(RegistryKey.ofRegistry(identifier), Lifecycle.stable());
    }

    public static BlockListener getBlockWrenchable(Block block) {
        return BLOCK_LISTENERS.stream().filter(it -> it.getBlock().equals(block)).findAny().orElse(null);
    }

    public static boolean isBlockWrenchable(Block block) {
        return getBlockWrenchable(block) != null;
    }

    public static InstanceListener getBlockInstanceWrenchable(Block block) {
        return BLOCK_INSTANCE_LISTENERS.stream().filter(it -> it.getBlock().isInstance(block)).findAny().orElse(null);
    }

    public static boolean isBlockInstanceWrenchable(Block block) {
        return getBlockInstanceWrenchable(block) != null;
    }

    public static Wrench getWrench(Item item) {
        return WRENCH_LISTENERS.stream().filter(it -> it.getItem().equals(item)).findAny().orElse(null);
    }

    public static boolean isWrench(Item item) {
        return getWrench(item) != null;
    }
}
