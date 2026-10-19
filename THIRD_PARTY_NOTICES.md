# Third-party notices

This repository builds on work by other people. Nothing here claims ownership of
it. Where a project declares a license, it is listed; where it does not, that is
stated plainly rather than assumed.

## Upstream projects

| Project | Role in this repository | License status (checked 2026-10-19) |
|---|---|---|
| [diabl0w/DFRoot](https://github.com/diabl0w/DFRoot) | Original exploit chain, JNI sources, kernel module, app | **No license file, no README license statement.** GitHub `license: null`. All rights reserved by default. |
| [mitschud/DirtyFrag](https://github.com/mitschud/DirtyFrag) | Direct parent of this fork; Samsung/RKP hardening, UI, build | **No license file.** GitHub `license: null`. All rights reserved by default. |
| [lsposed/lspromise](https://github.com/lsposed/lspromise) | Original PoC and various code | No license declared upstream; see project. |
| [polygraphene/DFReroot](https://github.com/polygraphene/DFReroot) | SELinux permissive kernel modules and various code | No license declared upstream; see project. |
| [combeng6th/DirtyInit](https://github.com/combeng6th/DirtyInit) | Unprivileged XFRM socket method | No license declared upstream; see project. |

"All rights reserved by default" is a statement of fact about the absence of a
grant, not an accusation. The authors simply never published terms. See
[LICENSING.md](LICENSING.md) for what that means for this fork.

## SU managers

GalaxyCrash does not bundle an SU manager. It expects one that ships `libksud.so`,
and it launches that manager's daemon. Those are separate projects under their
own terms:

- [diabl0w/KernelSU](https://github.com/diabl0w/KernelSU) — Samsung builds. Required on Samsung devices.
- [tiann/KernelSU](https://github.com/tiann/KernelSU) — upstream KernelSU, GPL-2.0.

## The kernel modules

`lkm/dfroot.c` and the prebuilt `app/src/main/jni/ko/*.ko` declare
`MODULE_LICENSE("GPL")`. Treat them as GPL-2.0-only. The `ko/` binaries are built
from `lkm/dfroot.c` by this repository's `Build` workflow and are committed so a
plain clone builds without a DDK toolchain.

## Android and Samsung

Android, One UI, Samsung, Knox, Odin and KernelSU are trademarks of their
respective owners. This project is not affiliated with, endorsed by, or
supported by Google, Samsung, or the KernelSU maintainers.

## Bundled fonts and libraries

| Component | License |
|---|---|
| Inter (UI font, `app/src/main/res/font/`) | SIL Open Font License 1.1 |
| AndroidX AppCompat, Material Components, ConstraintLayout | Apache-2.0 |
| Gradle wrapper | Apache-2.0 |

## CVE

CVE-2026-43284 is assigned by the CVE Program. The identifier is public; the
underlying vulnerability belongs to the affected kernel, not to this project.
