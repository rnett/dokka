package org.jetbrains.dokka.utilities

inline fun <T, K> Collection<T>.orderBy(
    order: List<K>,
    unspecified: (List<T>) -> List<T> = { it },
    crossinline key: (T) -> K
): List<T> {
    val (inOrder, notInOrder) = map { it to key(it) }.partition { it.second in order }

    return inOrder.sortedBy {
        order.indexOf(it.second)
    }.map { it.first } + notInOrder.map { it.first }.let(unspecified)
}