// FILE: test.kt
fun box() {
    val x = value()
    when (x) {
        x0() -> nop()
        x1() -> nop()
        x2() -> nop()
        x3() -> nop()
        else -> nop()
    }
}

fun value(): Int = 2
inline fun x0(): Int = 0
inline fun x1(): Int = 1
inline fun x2(): Int = 2
inline fun x3(): Int = 3

fun nop() {}

// JVM_IR generates an additional line number for the end of the condition, which is necessary for the correct "step over" behavior.

// LINENUMBERS
// test.kt:3 box
// test.kt:13 value
// test.kt:3 box
// test.kt:4 box
// test.kt:5 box
// test.kt:14 box
// LINENUMBERS JVM_IR
// test.kt:5 box
// LINENUMBERS
// test.kt:6 box
// test.kt:15 box
// LINENUMBERS JVM_IR
// test.kt:6 box
// LINENUMBERS
// test.kt:7 box
// test.kt:16 box
// test.kt:7 box
// test.kt:19 nop
// test.kt:7 box
// test.kt:11 box
