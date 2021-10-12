// See KT-49076

@Deprecated("Use ...", ReplaceWith("bar", imports = arrayOf("my.package.bar")))
fun foo() {}
