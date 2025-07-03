package net.dyama.lib

typealias Semver = List<Int>

val Semver.major get() = this[0]
val Semver.minor get() = this[1]

fun Semver.ge(majorX: Int, minorX: Int = 0) = major > majorX || major == majorX && minor >= minorX
fun Semver.lt(majorX: Int, minorX: Int = 0) = !ge(majorX, minorX)

fun String.toSemver(): Semver {
  return this.split('.', limit = 3).take(2).map(String::toInt)
}
