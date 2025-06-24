package net.dyama.whisky.ui.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.contentPaddingWithLabel
import androidx.compose.material3.TextFieldDefaults.contentPaddingWithoutLabel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation

// cf. androidx/compose/material3/material3/src/commonMain/androidx/compose/material3/TextFieldDefaults.kt
internal fun TextFieldColors.textColor(
  enabled: Boolean,
  isError: Boolean,
  focused: Boolean,
): Color =
  when {
    !enabled -> disabledTextColor
    isError -> errorTextColor
    focused -> focusedTextColor
    else -> unfocusedTextColor
  }

// ditto
internal fun TextFieldColors.cursorColor(isError: Boolean): Color =
  if (isError) errorCursorColor else cursorColor

// cf. androidx/compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/TextField.kt
@Composable
fun PlainTextField(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  textStyle: TextStyle = LocalTextStyle.current,
  label: @Composable (() -> Unit)? = null,
  placeholder: @Composable (() -> Unit)? = null,
  leadingIcon: @Composable (() -> Unit)? = null,
  trailingIcon: @Composable (() -> Unit)? = null,
  prefix: @Composable (() -> Unit)? = null,
  suffix: @Composable (() -> Unit)? = null,
  supportingText: @Composable (() -> Unit)? = null,
  isError: Boolean = false,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  singleLine: Boolean = false,
  maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
  minLines: Int = 1,
  interactionSource: MutableInteractionSource? = null,
  shape: Shape = TextFieldDefaults.shape,
  colors: TextFieldColors = TextFieldDefaults.colors(),
  contentPadding: PaddingValues =
    if (label == null) {
      contentPaddingWithoutLabel()
    } else {
      contentPaddingWithLabel()
    },
) {
  @Suppress("NAME_SHADOWING")
  val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
  // If color is not provided via the text style, use content color as a default
  val textColor =
    textStyle.color.takeOrElse {
      val focused = interactionSource.collectIsFocusedAsState().value
      colors.textColor(enabled, isError, focused)
    }
  val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

  CompositionLocalProvider(LocalTextSelectionColors provides colors.textSelectionColors) {
    BasicTextField(
      value = value,
      modifier =
        modifier
          // .defaultErrorSemantics(isError, getString(Strings.DefaultErrorMessage))
          .defaultMinSize(
            minWidth = TextFieldDefaults.MinWidth,
            minHeight = TextFieldDefaults.MinHeight
          ),
      onValueChange = onValueChange,
      enabled = enabled,
      readOnly = readOnly,
      textStyle = mergedTextStyle,
      cursorBrush = SolidColor(colors.cursorColor(isError)),
      visualTransformation = visualTransformation,
      keyboardOptions = keyboardOptions,
      keyboardActions = keyboardActions,
      interactionSource = interactionSource,
      singleLine = singleLine,
      maxLines = maxLines,
      minLines = minLines,
      decorationBox =
        @Composable { innerTextField ->
          // places leading icon, text field with label and placeholder, trailing icon
          TextFieldDefaults.DecorationBox(
            value = value,
            visualTransformation = visualTransformation,
            innerTextField = innerTextField,
            placeholder = placeholder,
            label = label,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            supportingText = supportingText,
            shape = shape,
            singleLine = singleLine,
            enabled = enabled,
            isError = isError,
            interactionSource = interactionSource,
            colors = colors,
            contentPadding = contentPadding,
          )
        }
    )
  }
}
