package soSimpleInlineIfCondition

fun main(args: Array<String>) {
    //Breakpoint!
    if (foo {
        test(2)
    }) {
        bar()
    }

    bar()
}

inline fun foo(f: () -> Boolean): Boolean = f()

fun test(i: Int): Boolean = true

fun bar() {}

// STEP_OVER: 5
// Note: JVM_IR has an additional line number 7 after the inline function call, denoting the end of the condition.
