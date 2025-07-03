package net.dyama.misskey

// misskey-dev/misskey/locales/ja-JP.yml を参考に……

object MisskeyPermissions {
  // pre 94f8a145eca
  val ancient = listOf(
    "read:account",
    "write:account",
    "read:drive",
    "write:drive",
  )

  val v12 = ancient + listOf(
    "read:blocks",
    "write:blocks",
    "read:favorites",
    "write:favorites",
    "read:following",
    "write:following",
    "read:messaging",
    "write:messaging",
    "read:mutes",
    "write:mutes",
    "write:notes",
    "read:notifications",
    "write:notifications",
    "read:reactions",
    "write:reactions",
    "write:votes",
    "read:pages",
    "write:pages",
    "read:page-likes",
    "write:page-likes",
    "read:user-groups",
    "write:user-groups",
    "read:channels",
    "write:channels",
    "read:gallery",
    "write:gallery",
    "read:gallery-likes",
    "write:gallery-likes",
  )

  val v13 = v12 + listOf(
    "read:flash",
    "write:flash",
    "read:flash-likes",
    "write:flash-likes",
  )

  // c96bc36fedc
  val v2023 = v13 + listOf(
    "read:admin:abuse-user-reports",
    "write:admin:delete-account",
    "write:admin:delete-all-files-of-a-user",
    "read:admin:index-stats",
    "read:admin:table-stats",
    "read:admin:user-ips",
    "read:admin:meta",
    "write:admin:reset-password",
    "write:admin:resolve-abuse-user-report",
    "write:admin:send-email",
    "read:admin:server-info",
    "read:admin:show-moderation-log",
    "read:admin:show-user",
    "read:admin:show-users",
    "write:admin:suspend-user",
    "write:admin:unset-user-avatar",
    "write:admin:unset-user-banner",
    "write:admin:unsuspend-user",
    "write:admin:meta",
    "write:admin:user-note",
    "write:admin:roles",
    "read:admin:roles",
    "write:admin:relays",
    "read:admin:relays",
    "write:admin:invite-codes",
    "read:admin:invite-codes",
    "write:admin:announcements",
    "read:admin:announcements",
    "write:admin:avatar-decorations",
    "read:admin:avatar-decorations",
    "write:admin:federation",
    "write:admin:account",
    "read:admin:account",
    "write:admin:emoji",
    "read:admin:emoji",
    "write:admin:queue",
    "read:admin:queue",
    "write:admin:promo",
    "write:admin:drive",
    "read:admin:drive",
    "read:admin:stream",
    "write:admin:ad",
    "read:admin:ad",
    "write:invite-codes",
    "read:invite-codes",
    "write:clip-favorite",
    "read:clip-favorite",
    "read:federation",
    "write:report-abuse",
  )

  // f1f24e39d2d
  val v2025 = v2023 - listOf(
    "read:messaging",
    "write:messaging",
  ) + listOf(
    "write:chat",
    "read:chat",
  )

  val latest = v2025
}
