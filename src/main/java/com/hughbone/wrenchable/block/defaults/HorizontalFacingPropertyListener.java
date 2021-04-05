package com.hughbone.wrenchable.block.defaults;

import com.hughbone.wrenchable.block.PropertyListener;
import com.hughbone.wrenchable.WrenchableUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class HorizontalFacingPropertyListener extends PropertyListener {
    public HorizontalFacingPropertyListener() {
        super(Properties.HORIZONTAL_FACING);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        WrenchableUtilities.doHorizontalFacingBehavior(world, player, result);
    }
}
