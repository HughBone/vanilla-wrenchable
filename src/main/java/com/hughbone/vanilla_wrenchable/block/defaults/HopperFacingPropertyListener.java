package com.hughbone.vanilla_wrenchable.block.defaults;

import com.hughbone.vanilla_wrenchable.WrenchableUtilities;
import com.hughbone.vanilla_wrenchable.block.PropertyListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class HopperFacingPropertyListener extends PropertyListener {
    public HopperFacingPropertyListener() {
        super(Properties.HOPPER_FACING);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        WrenchableUtilities.doHopperFacingBehavior(world, player, result);
    }
}
