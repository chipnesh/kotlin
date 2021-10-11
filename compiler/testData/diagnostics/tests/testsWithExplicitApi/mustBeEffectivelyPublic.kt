// FIR_IDENTICAL
// SKIP_TXT

private class Foo {
    fun method() {}
}

public interface I {
    public fun bar()
}

public fun <!NO_EXPLICIT_RETURN_TYPE_IN_API_MODE!>f<!>() {
    val i = object : I {
        internal var foo = 0
        override fun bar() {}
    }
    i.foo = 1

    class LocalClass {
        internal var foo = 0
    }
    LocalClass().foo = 1
}
