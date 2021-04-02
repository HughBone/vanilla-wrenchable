package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.WrenchableUtilities;
import com.zundrel.wrenchable.block.PropertyListener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RailShapePropertyListener extends PropertyListener {

    public RailShapePropertyListener() {
        super(Properties.RAIL_SHAPE);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        WrenchableUtilities.rotateState(world, player, state, pos);

    }
}
