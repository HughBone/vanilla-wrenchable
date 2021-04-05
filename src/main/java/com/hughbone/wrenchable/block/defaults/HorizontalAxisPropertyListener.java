package com.hughbone.wrenchable.block.defaults;

import com.hughbone.wrenchable.block.PropertyListener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class HorizontalAxisPropertyListener extends PropertyListener {
    public HorizontalAxisPropertyListener() {
        super(Properties.HORIZONTAL_AXIS);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        // hughtest
        if (player.isSneaking()) {
            Direction.Axis axis = Direction.Axis.X;

            if (player.getHorizontalFacing() == Direction.NORTH || player.getHorizontalFacing() == Direction.SOUTH)
                axis = Direction.Axis.Z;

            world.setBlockState(pos, state.with(Properties.AXIS, axis));
            world.updateNeighbor(pos, block, pos);
            return;
        }

        world.setBlockState(pos, state.cycle(Properties.HORIZONTAL_AXIS));
        world.updateNeighbor(pos, block, pos);
        return;
    }
}
