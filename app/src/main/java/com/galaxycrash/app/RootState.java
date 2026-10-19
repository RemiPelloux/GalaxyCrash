package com.galaxycrash.app;

import java.io.File;

/**
 * One definition of "this device is rooted", shared by the activity and the
 * boot receiver.
 *
 * <p>Upstream used {@code new File("/dev/df").exists()} everywhere. That marker
 * is created by the kernel module at the very start of the bootstrap handoff,
 * and nothing removes it when bootstrap dies before ksud starts - so a failed
 * run left it on disk and the app rendered "Rooted" for a device with no {@code
 * su}, while KernelSU itself said "Non installé" (finding F-002).
 *
 * <p>{@code /dev/df} is therefore necessary but not sufficient. The run is only
 * a success once bootstrap reached the point where it started the SU daemon,
 * which it records by touching {@code /dev/dfm6} (see {@code markers[]} in
 * {@code exp.c}). Both nodes are created by the same {@code touch()} with mode
 * 0666, so both are stat-able from the app's own {@code untrusted_app} context -
 * unlike {@code /proc/modules}, which SELinux hides from this domain.
 */
final class RootState {

    /** Marker touched by bootstrap right after ksud late-load returns 0. */
    private static final String KSUD_STARTED = "/dev/dfm6";

    private RootState() {}

    /** @return true only when the hook marker and the "ksud started" marker are both present. */
    static boolean isRooted() {
        return new File("/dev/df").exists() && new File(KSUD_STARTED).exists();
    }
}
