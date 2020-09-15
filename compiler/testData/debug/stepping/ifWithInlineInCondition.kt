// FILE: test.kt
fun box() {
    if (inlineFun()) {
        nop()
    }
}

inline fun inlineFun(): Boolean {
    return true
}

fun nop() {}

// LINENUMBERS
// test.kt:3 box
// test.kt:9 box
// test.kt:3 box
// test.kt:4 box
// test.kt:12 nop
// test.kt:6 box
