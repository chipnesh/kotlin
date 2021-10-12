// FILE: first/KtNodeTypes.java

package first;

public interface KtNodeTypes {
    String SOME = "Some";
}

// FILE: SomeEnum.kt

package second

enum class SomeEnum {
    SOME;
}

// FILE: test.kt

import first.KtNodeTypes.*
import second.SomeEnum.*

fun println(s: String) {}

fun test(arg: String): Boolean {
    val x = SOME
    println(x)
    return when (arg) {
        SOME -> true
        else -> false
    }
}
