# Licensing scope

**Short version:** the [MIT license](LICENSE) covers the changes Remi Pelloux
wrote in this fork. It does **not** cover the exploit chain inherited from
upstream, because upstream never granted a license for it. That is not something
a fork can fix by writing a new LICENSE file.

## What is covered by this fork's MIT grant

Only the original work added here, which is small and precisely delimited by
git history:

| File | Change |
|---|---|
| `app/src/main/java/df/root/RootState.java` | new file, whole file |
| `app/src/main/java/df/root/MainActivity.java` | the `isRooted()` helper and its 6 call sites, `failedRun` on failure |
| `app/src/main/java/df/root/BootReceiver.java` | root check only |
| `app/src/main/jni/bootstrap.c` | `disable_modules()` `ENOENT` handling only |
| `LICENSE`, `LICENSING.md`, `THIRD_PARTY_NOTICES.md` | new |
| `README.md`, `docs/` | rewritten text and SVG artwork authored here |

Everything else — the exploit itself — is inherited. Run
`git log --oneline` and `git diff <upstream-tag>` to see exactly this boundary.

## The inheritance problem

This repository is a fork of
[mitschud/DirtyFrag](https://github.com/mitschud/DirtyFrag), which is itself a
fork of [diabl0w/DFRoot](https://github.com/diabl0w/DFRoot). Verified on
2026-10-19:

- `diabl0w/DFRoot` has **no license file** and **no license or copyright
  statement in its README**. GitHub reports `license: null`.
- `mitschud/DirtyFrag` likewise has **no license file**. GitHub reports
  `license: null`.
- `lkm/dfroot.c` declares `MODULE_LICENSE("GPL")`. That string is the Linux
  kernel's module-ABI declaration; it describes the module's kernel-facing
  licensing, and it is a strong signal about the author's intent, but it is not
  a repository-level grant for the userspace code.

Under the Berne Convention, code with no license is **all rights reserved** by
default. Nobody downstream — including this fork — can grant rights they were
never given. A new LICENSE file cannot retroactively license upstream's work.

This matters in practice: a public fork published under a permissive license
implies downstream users have permission to copy the exploit. For the inherited
portion, they do not have that permission from us.

## What this fork therefore does not claim

- It does not claim copyright over the exploit chain, the JNI sources, the
  kernel module, or any inherited UI code.
- It does not offer a sublicense for that inherited code.
- It does not assert that redistribution of the inherited code is authorised.

## If you want a clean license

The only way to resolve this properly is upstream. Options, in order of
preference:

1. **Ask upstream to add a license.** Open an issue on `diabl0w/DFRoot`
   proposing MIT or GPL-2.0. If they adopt one, this fork can adopt it
   immediately and the ambiguity disappears.
2. **Keep this fork private** until upstream answers. A private fork has no
   redistribution question.
3. **Publish only the patch.** Ship `dirtyfrag-fixes.patch` plus the README,
   without redistributing upstream's source. Readers apply it to their own
   clone of upstream, which they obtained from upstream.

Option 3 is the conservative choice, and it is what this repository's
`notes/dirtyfrag-fixes.patch` is for.

## Kernel module

`app/src/main/jni/ko/*.ko` and `lkm/dfroot.c` are Linux kernel modules declaring
`MODULE_LICENSE("GPL")`. Distributing GPL-declared kernel modules alongside a
more restrictive license is a known friction point. Treat the modules as
GPL-2.0-only in practice, and do not describe this repository as a whole as
"MIT" without that qualifier.

## No warranty, and real risk

This is a privilege-escalation exploit for Android. Running it can:

- leave SELinux permissive if a run fails partway (this fork fixes one such path,
  see `notes/findings/F-001-*.md`),
- bootloop a device if a KernelSU module is broken and Autorun is enabled,
- trip Knox and void warranties on Samsung hardware.

It is provided with no warranty of any kind. You are responsible for the device
you run it on.
