package net.dyama.lib

fun String.chomp(delim: String): Pair<String, String> {
  val list = this.split(delim, limit = 2)
  return list[0] to list[1]
}
