package com.hughbone.vanilla_wrenchable.block.defaults;

import com.hughbone.vanilla_wrenchable.block.PropertyListener;
import com.hughbone.vanilla_wrenchable.WrenchableUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class RotationPropertyListener extends PropertyListener {
    public RotationPropertyListener() {
        super(Properties.ROTATION);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        WrenchableUtilities.doRotationBehavior(world, player, result);
    }
}
