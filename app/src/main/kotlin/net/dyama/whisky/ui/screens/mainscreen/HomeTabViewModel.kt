package net.dyama.whisky.ui.screens.mainscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import net.dyama.whisky.lib.misskey.entity.Note
import net.dyama.whisky.ui.data.CurrentAccountRepository
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel @Inject constructor(
  private val currentAccountRepository: CurrentAccountRepository,
) : ViewModel() {
  val refreshing = mutableStateOf(false)
  val clientFlow = currentAccountRepository.currentClientFlow.filterNotNull()

  private val _timeline = MutableStateFlow<List<Note>>(emptyList())
  val timeline = _timeline.asStateFlow()

  init {
    _timeline.value = listOf() // todo: cache
    refresh()
  }

  fun refresh() {
    viewModelScope.launch {
      refreshing.value = true
      _timeline.value = clientFlow.first().timeline()
      refreshing.value = false
    }
  }
}
