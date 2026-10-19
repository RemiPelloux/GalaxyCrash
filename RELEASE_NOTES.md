# GalaxyCrash 1.17

Renamed from DirtyFrag. **Clean install required** — the package id changed from `df.root` to
`com.galaxycrash.app`, so uninstall the old app first.

## Install

1. Install an SU manager that ships `libksud.so`
   ([diabl0w's KernelSU](https://github.com/diabl0w/KernelSU/releases) on Samsung).
2. Install `GalaxyCrash-v1.17.apk`.
3. Open it, pick your manager on the **SU Manager** card, tap **Run** twice.

Root is ephemeral: reboot removes it, and rebooting is the recovery path.

## What changed

- **Renamed to GalaxyCrash**, new package `com.galaxycrash.app`, new label and toolbar title.
- **All eight kernel modules rebuilt** for the new device-protected storage path. Shipping the old
  `.ko` files would have launched a bootstrap that no longer exists.
- **Fixed: Modules toggle aborted the run.** With *KernelSU Modules → Disabled* on a fresh KernelSU
  install, a missing `/data/adb/modules` made the bootstrap abort **after** the kernel hook landed
  but **before** `ksud` started — no root, and **SELinux left permissive**. Missing directory is now
  success; real errors still fail. Same bug as upstream issue #42.
- **Fixed: failed runs displayed as `Rooted`.** Every check trusted `/dev/df`, which a failed run
  leaves behind, so a half-finished run showed `Rooted` / 100% / `Verified` while KernelSU said "not
  installed". Root now requires `/dev/df` **and** `/dev/dfm6`. The same stale check in
  `ExploitRunner` also blocked legitimate re-runs.
- **Fixed:** update check and Github link pointed at the parent repo, which would have offered an
  APK signed with a different key.
- **Docs:** new README with animated SVG artwork, plus `LICENSE`, `LICENSING.md` and
  `THIRD_PARTY_NOTICES.md`.

## Verified on

Samsung Galaxy S26 Ultra (SM-S948B) · Android 17 · kernel 6.12.69 · patch 2026-09-05 · bootloader
locked. Result: root, KernelSU LKM live, SELinux back to Enforcing, clean reboot.

Tested on one build only. The October patch — the one upstream issue #42 was filed against — was not
retested.

## Licensing

MIT for this fork's own changes only. Upstream (`diabl0w/DFRoot`, `mitschud/DirtyFrag`) publishes no
license, so this release does not claim to relicense the inherited exploit chain. The kernel modules
declare `MODULE_LICENSE("GPL")`. See `LICENSING.md`.
