package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.InstanceListener;
import com.zundrel.wrenchable.WrenchableUtilities;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PistonInstanceListener extends InstanceListener {
    public PistonInstanceListener() {
        super(PistonBlock.class);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        Direction direction = state.get(Properties.FACING);

        if (!state.get(Properties.EXTENDED)) {
            WrenchableUtilities.doFacingBehavior(world, player, result);
        }
    }
}
