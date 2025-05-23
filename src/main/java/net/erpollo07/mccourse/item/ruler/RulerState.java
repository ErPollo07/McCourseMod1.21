package net.erpollo07.mccourse.item.ruler;

import net.minecraft.util.math.BlockPos;

public class RulerState {
    private static BlockPos firstPos = null;

    public static void setFirstPos(BlockPos pos) {
        firstPos = pos;
    }

    public static BlockPos getFirstPos() {
        return firstPos;
    }

    public static void reset() {
        firstPos = null;
    }

    public static boolean hasFirstPos() {
        return firstPos != null;
    }
}
