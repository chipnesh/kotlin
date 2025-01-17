// IGNORE_BACKEND: WASM
// WASM_MUTE_REASON: IGNORED_IN_JS
// IGNORE_BACKEND: JS_IR
// IGNORE_BACKEND: JS_IR_ES6
// IGNORE_BACKEND: NATIVE
var result = ""

enum class E(a: String) {
    X("x"),
    Y("y");

    init {
        result += "E.init($a);"
    }

    companion object {
        init {
            result += "E.companion.init;"
            val value = E.values()[0].name
            result += "$value;"
        }
    }
}

enum class F(a: String) {
    X("x"),
    Y("y");

    init {
        result += "F.init($a);"
    }

    companion object {
        init {
            result += "F.companion.init;"
        }

        fun foo() {
            result += "F.foo();$X;"
        }
    }
}

fun box(): String {
    val y = E.Y
    result += "${y.name};"
    F.foo()
    if (result != "E.init(x);E.init(y);E.companion.init;X;Y;F.init(x);F.init(y);F.companion.init;F.foo();X;") return "fail: $result"

    return "OK"
}