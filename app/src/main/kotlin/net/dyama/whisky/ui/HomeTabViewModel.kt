package net.dyama.whisky.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel @Inject constructor(
  private val currentAccountRepository: CurrentAccountRepository,
) : ViewModel() {
  val clientFlow = currentAccountRepository.currentClientFlow

  init {
    viewModelScope.launch {
      clientFlow.collect {
        println(it?.timeline())
      }
    }
  }
}
