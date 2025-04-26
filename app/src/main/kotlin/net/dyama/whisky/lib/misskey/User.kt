package net.dyama.whisky.lib.misskey

data class Id(val id: String, val host: String?)

fun parseId(str: String): Id {
  val id = str.trim('@')
  if (!id.contains('@')) return Id(id, null)
  return id.split('@', limit = 2).let { Id(it[0], it[1]) }
}
