package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.BlockListener;
import com.zundrel.wrenchable.block.PropertyListener;
import com.zundrel.wrenchable.wrench.Wrench;
import com.zundrel.wrenchable.block.BlockWrenchable;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class WrenchableEvents {
    public static void init() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            ItemStack heldStack = playerEntity.getStackInHand(hand);
            BlockPos pos = blockHitResult.getBlockPos();
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (!heldStack.isEmpty() && WrenchableUtilities.isWrench(heldStack.getItem())) {
                // Checks if it has the name "wrench"
                if (heldStack.getName().toString().toLowerCase().contains("wrench")) {
                    Wrench wrench = WrenchableUtilities.getWrench(heldStack.getItem());

                    if (world.getBlockState(pos).getBlock() instanceof BlockWrenchable) {
                        wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
                        ((BlockWrenchable) world.getBlockState(pos).getBlock()).onWrenched(world, playerEntity, blockHitResult);

                        if (blockEntity instanceof BlockWrenchable) {
                            ((BlockWrenchable) blockEntity).onWrenched(world, playerEntity, blockHitResult);
                            wrench.onBlockEntityWrenched(world, heldStack, playerEntity, hand, blockEntity, blockHitResult);
                        }

                        return ActionResult.SUCCESS;
                    } else if (blockEntity instanceof BlockWrenchable) {
                        wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
                        wrench.onBlockEntityWrenched(world, heldStack, playerEntity, hand, blockEntity, blockHitResult);
                        ((BlockWrenchable) blockEntity).onWrenched(world, playerEntity, blockHitResult);

                        return ActionResult.SUCCESS;
                    } else if (WrenchableRegistry.isBlockWrenchable(world.getBlockState(pos).getBlock())) {
                        BlockListener wrenchable = WrenchableRegistry.getBlockWrenchable(world.getBlockState(pos).getBlock());

                        wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
                        wrenchable.onWrenched(world, playerEntity, blockHitResult);

                        return ActionResult.SUCCESS;
                    } if (WrenchableRegistry.isBlockInstanceWrenchable(world.getBlockState(pos).getBlock())) {
                        wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
                        WrenchableRegistry.getBlockInstanceWrenchable(world.getBlockState(pos).getBlock()).onWrenched(world, playerEntity, blockHitResult);

                        return ActionResult.SUCCESS;
                    } else {
                        for (PropertyListener wrenchable : WrenchableRegistry.PROPERTY_LISTENERS) {
                            if (world.getBlockState(pos).contains(wrenchable.getProperty())) {
                                wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
                                wrenchable.onWrenched(world, playerEntity, blockHitResult);

                                return ActionResult.SUCCESS;
                            }
                        }
                    }
                }

            }

            return ActionResult.PASS;
        });

    }
}
