package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.InstanceListener;
import com.zundrel.wrenchable.WrenchableUtilities;
import net.minecraft.block.*;
import net.minecraft.block.entity.SignBlockEntity;
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
        SignBlockEntity blockEntity = (SignBlockEntity) world.getBlockEntity(pos);

        // Kind of edit signs??
        /*
        if (player.isSneaking() && world.getBlockEntity(result.getBlockPos()) instanceof SignBlockEntity) {
            if (world.isClient()) {
                blockEntity.setEditor(player);
                blockEntity.setEditable(true);
                player.openEditSignScreen((SignBlockEntity) world.getBlockEntity(result.getBlockPos()));
                return;
            }
        }
         */

        if (block instanceof SignBlock) {
            WrenchableUtilities.doRotationBehavior(world, player, result);
        } else if (block instanceof WallSignBlock) {
            WrenchableUtilities.doHorizontalFacingBehavior(world, player, result);
        }
    }
}
