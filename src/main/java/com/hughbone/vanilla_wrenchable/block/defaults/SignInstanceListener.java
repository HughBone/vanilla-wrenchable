package com.hughbone.vanilla_wrenchable.block.defaults;

import com.hughbone.vanilla_wrenchable.block.InstanceListener;
import com.hughbone.vanilla_wrenchable.WrenchableUtilities;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SignInstanceListener extends InstanceListener {
    public SignInstanceListener() {
        super(AbstractSignBlock.class);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (block instanceof SignBlock) {
            WrenchableUtilities.doRotationBehavior(world, player, result);
        } else if (block instanceof WallSignBlock) {
            WrenchableUtilities.doHorizontalFacingBehavior(world, player, result);
        }
    }
}
