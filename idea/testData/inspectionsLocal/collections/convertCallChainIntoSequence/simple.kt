// WITH_RUNTIME

fun test(): List<Int> {
    return listOf(1, 2, 3).<caret>filter { it > 1 }.map { it * 2 }
}