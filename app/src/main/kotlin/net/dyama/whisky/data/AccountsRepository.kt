package net.dyama.whisky.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import com.google.protobuf.InvalidProtocolBufferException
import dagger.hilt.android.qualifiers.ApplicationContext
import net.dyama.whisky.proto.AccountsOuterClass.Accounts
import net.dyama.whisky.ui.MainScreenTabs
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

private object AccountsSerializer : Serializer<Accounts> {
  override val defaultValue: Accounts = Accounts.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): Accounts {
    try {
      return Accounts.parseFrom(input)
    } catch (exception: InvalidProtocolBufferException) {
      throw CorruptionException("Cannot read proto.", exception)
    }
  }

  override suspend fun writeTo(
    t: Accounts,
    output: OutputStream,
  ) = t.writeTo(output)
}

@Singleton
class AccountsRepository @Inject constructor(
  @ApplicationContext private val context: Context,
) {
  private companion object {
    val Context.dataStore by dataStore("accounts", AccountsSerializer)
  }

  val flow = context.dataStore.data

  suspend fun saveMainScreenTab(tab: MainScreenTabs) =
    context.dataStore.edit { preferences -> preferences[MAIN_SCREEN_TAB] = tab.name }
}
