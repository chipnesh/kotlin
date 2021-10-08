// TARGET_BACKEND: JVM
// IGNORE_BACKEND: JVM JVM_IR
// WITH_RUNTIME
// FULL_JDK

fun box(): String {
    val x = Comparator.comparing { x: String ->
        1
    }

    if (x.compare("O", "K") != 0) return "fail"

    return "OK"
}
