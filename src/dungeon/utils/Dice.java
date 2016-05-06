package dungeon.utils;

import dungeon.engine.control.command.EmptyMethod;

import java.util.Random;

public final class Dice {

    /* ========== ATTRIBUTES========== */
    private static Random random = new Random();

    /* ========== CONSTRUCTORS ========== */
    private Dice() {
    }

    /* ========== SERVICES ========== */
    public static int k2() {
        return k(2);
    }

    public static int k4() {
        return k(4);
    }

    public static int k6() {
        return k(6);
    }

    public static int k8() {
        return k(8);
    }

    public static int k10() {
        return k(10);
    }

    public static int k12() {
        return k(12);
    }

    public static int k20() {
        return k(20);
    }

    public static int k100() {
        return k(100);
    }

    public static int k(int seed) {
        return random.nextInt(seed);
    }

    public static boolean test(int seed) {
        return k(seed) == 0;
    }

    public static void test(int seed, EmptyMethod method) {
        if (k(seed) == 0) method.action();
    }
}