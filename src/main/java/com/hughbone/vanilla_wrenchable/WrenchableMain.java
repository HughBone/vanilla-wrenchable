package com.hughbone.vanilla_wrenchable;

import com.hughbone.vanilla_wrenchable.block.InstanceListener;
import com.hughbone.vanilla_wrenchable.block.PropertyListener;
import com.hughbone.vanilla_wrenchable.block.defaults.*;
import com.hughbone.vanilla_wrenchable.wrench.WrenchListener;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.BedBlock;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WrenchableMain implements ModInitializer {
    public static String MODID = "wrenchable";

    public static InstanceListener BED_LISTENER;
    public static InstanceListener DOOR_LISTENER;
    public static InstanceListener PISTON_LISTENER;
    public static InstanceListener SIGN_LISTENER;

    public static PropertyListener AXIS_LISTENER;
    public static PropertyListener FACING_LISTENER;
    public static PropertyListener HOPPER_FACING_LISTENER;
    public static PropertyListener HORIZONTAL_AXIS_LISTENER;
    public static PropertyListener HORIZONTAL_FACING_LISTENER;
    public static PropertyListener ROTATION_LISTENER;
    public static PropertyListener SLAB_TYPE_LISTENER;
    public static PropertyListener RAIL_SHAPE_LISTENER;

	@Override
	public void onInitialize() {
	    Registry.register(WrenchableRegistry.WRENCH_LISTENERS, new Identifier(MODID, "stick"), new WrenchListener(Items.CARROT_ON_A_STICK));

	    BED_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "bed"), new NoBehaviorInstanceListener(BedBlock.class));
	    DOOR_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "door"), new DoorInstanceListener());
	    PISTON_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "piston"), new PistonInstanceListener());
	    SIGN_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "sign"), new SignInstanceListener());
        AXIS_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "axis"), new AxisPropertyListener());
        FACING_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "facing"), new FacingPropertyListener());
        HOPPER_FACING_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "hopper_facing"), new HopperFacingPropertyListener());
        HORIZONTAL_AXIS_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "horizontal_axis"), new HorizontalAxisPropertyListener());
        HORIZONTAL_FACING_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "horizontal_facing"), new HorizontalFacingPropertyListener());
	    ROTATION_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "rotation"), new RotationPropertyListener());
	    SLAB_TYPE_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "slab_type"), new SlabTypePropertyListener());
	    RAIL_SHAPE_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "rail_shape"), new RailShapePropertyListener());

        WrenchableEvents.init();

	}
}
