package net.dyama.lib

import java.security.SecureRandom

class Ulid {
  companion object {
    private const val ALPHABET_BASE32 = "0123456789ABCDEFGHJKMNPQRSTVWXYZ"
    private val random = SecureRandom()

    private fun encodeTimestamp(timestamp: Long): String {
      val buf = CharArray(10)
      for (i in 0..9) {
        val c = timestamp shr (5 * i) and 31
        buf[9 - i] = ALPHABET_BASE32[c.toInt()]
      }
      return String(buf)
    }

    private fun encodeRandom(): String {
      val buf = CharArray(16)
      for (i in 0..15) {
        buf[i] = ALPHABET_BASE32[random.nextInt(32)]
      }
      return String(buf)
    }

    fun generate(): String {
      val timestamp = System.currentTimeMillis()
      return encodeTimestamp(timestamp) + encodeRandom()
    }
  }
}
