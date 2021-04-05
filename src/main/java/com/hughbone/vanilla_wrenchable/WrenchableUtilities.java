package com.hughbone.vanilla_wrenchable;

import com.hughbone.vanilla_wrenchable.wrench.Wrench;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WrenchableUtilities {
    /**
     * This method causes the default Properties.FACING behavior to run.
     * @param world An instance of the world where this block was wrenched.
     * @param player The player who wrenched this block.
     * @param result Information about the block that was wrenched.
     * @author Zundrel
     */
    public static void doFacingBehavior(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        Direction direction = state.get(Properties.FACING);

        if (player.isSneaking()) {
            if (state.get(Properties.FACING) != Direction.DOWN && state.get(Properties.FACING) != Direction.UP) {
                if (result.getSide() == Direction.DOWN || result.getSide() == Direction.UP) {
                    world.setBlockState(pos, state.with(Properties.FACING, result.getSide()));
                    world.updateNeighbor(pos, block, pos);
                    return;
                }

                world.setBlockState(pos, state.with(Properties.FACING, Direction.UP));
                world.updateNeighbor(pos, block, pos);
                return;
            }
            else {
                if (result.getSide() != Direction.DOWN && result.getSide() != Direction.UP) {
                    world.setBlockState(pos, state.with(Properties.FACING, result.getSide()));
                    world.updateNeighbor(pos, block, pos);
                    return;
                }

                world.setBlockState(pos, state.with(Properties.FACING, Direction.NORTH));
                world.updateNeighbor(pos, block, pos);
                return;
            }
        }

        if (direction == Direction.UP || direction == Direction.DOWN) {
            world.setBlockState(pos, state.with(Properties.FACING, direction.getOpposite()));
            world.updateNeighbor(pos, block, pos);
            return;
        }

        rotateState(world, player, state, pos);
    }

    /**
     * This method causes the default Properties.HORIZONTAL_FACING behavior to run.
     * @param world An instance of the world where this block was wrenched.
     * @param player The player who wrenched this block.
     * @param result Information about the block that was wrenched.
     * @author Zundrel
     */
    public static void doHorizontalFacingBehavior(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        // (stairs / buttons / grindstone / ...)
        if (player.isSneaking()) {
            try {
                if (state.get(Properties.BLOCK_HALF) == BlockHalf.TOP) {
                    world.setBlockState(pos, state.with(Properties.BLOCK_HALF, BlockHalf.BOTTOM));
                    world.updateNeighbor(pos, block, pos);
                    return;
                }
                else {
                    world.setBlockState(pos, state.with(Properties.BLOCK_HALF, BlockHalf.TOP));
                    world.updateNeighbor(pos, block, pos);
                    return;
                }
            } catch (Exception e){}

            // Player directional facing
            if (!state.with(Properties.HORIZONTAL_FACING, player.getHorizontalFacing().getOpposite()).canPlaceAt(world, pos))
                return;

            world.setBlockState(pos, state.with(Properties.HORIZONTAL_FACING, player.getHorizontalFacing().getOpposite()));
            world.updateNeighbor(pos, block, pos);
            return;
        }

        rotateState(world, player, state, pos);
    }

    /**
     * This method causes the default Properties.HOPPER_FACING behavior to run.
     * @param world An instance of the world where this block was wrenched.
     * @param player The player who wrenched this block.
     * @param result Information about the block that was wrenched.
     * @author Zundrel
     */
    public static void doHopperFacingBehavior(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        Direction direction = state.get(Properties.HOPPER_FACING);

        if (player.isSneaking()) {
            if (state.get(Properties.HOPPER_FACING) == Direction.DOWN && result.getSide() != Direction.UP) {
                world.setBlockState(pos, state.with(Properties.HOPPER_FACING, result.getSide()));
                world.updateNeighbor(pos, block, pos);
                return;
            }

            world.setBlockState(pos, state.with(Properties.HOPPER_FACING, Direction.DOWN));
            world.updateNeighbor(pos, block, pos);
            return;
        }
        if (state.get(Properties.HOPPER_FACING) == Direction.DOWN) {
            world.setBlockState(pos, state.with(Properties.HOPPER_FACING, Direction.NORTH));
            world.updateNeighbor(pos, block, pos);
            return;
        }

        rotateState(world, player, state, pos);
    }

    /**
     * This method causes basic rotation behavior for a BlockState to occur.
     * I don't recommend using this, instead use doFacingBehavior or doHorizontalFacingBehavior.
     * @param world An instance of the world where this block was wrenched.
     * @param player The player who wrenched this block.
     * @param state The BlockState that was wrenched.
     * @param pos The BlockPos that was wrenched.
     * @author Zundrel
     */
    public static void rotateState(World world, PlayerEntity player, BlockState state, BlockPos pos) {
        Block block = state.getBlock();

        if (!state.rotate(BlockRotation.CLOCKWISE_90).canPlaceAt(world, pos))
            return;

        world.setBlockState(pos, state.rotate(BlockRotation.CLOCKWISE_90));
        world.updateNeighbor(pos, block, pos);
    }

    public static void doRotationBehavior(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (player.isSneaking()) {
            if (block instanceof SkullBlock)
                world.setBlockState(pos, state.with(Properties.ROTATION, MathHelper.floor((double)((player.yaw) * 16.0F / 360.0F) + 0.5D) & 15));
            return;
        } else {
            if (state.get(Properties.ROTATION) < 15) {
                world.setBlockState(pos, state.with(Properties.ROTATION, state.get(Properties.ROTATION) + 1));
            } else {
                world.setBlockState(pos, state.with(Properties.ROTATION, 0));
            }

            world.updateNeighbor(pos, block, pos);
            return;
        }
    }

    public static Wrench getWrench(Item item) {
        if (item instanceof Wrench)
            return (Wrench) item;
        else if (WrenchableRegistry.isWrench(item))
            return WrenchableRegistry.getWrench(item);

        return null;
    }

    public static boolean isWrench(Item item) {
        return item instanceof Wrench || WrenchableRegistry.isWrench(item);
    }
}
