package com.surepay.auth_presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.surepay.core_ui.LocalSpacing
import com.surepay.core_ui.components.ActionButton
import com.surepay.core.R
import com.surepay.core.util.UiEvent
import com.surepay.core_ui.components.UnitTextField

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    navigateToCardsScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
){
    val spacing = LocalSpacing.current
    val state = loginViewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        loginViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is UiEvent.Success -> {
                    navigateToCardsScreen()
                }
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitTextField(
            value = loginViewModel.email,
            onValueChange = loginViewModel::onEmailEnter,
            unit = stringResource(id = R.string.email_hint)

        )
        Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))
        UnitTextField(
            value = loginViewModel.password,
            onValueChange = loginViewModel::onPasswordEnter,
            unit = stringResource(id = R.string.password_hint)
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))

        ActionButton(
            text = stringResource(id = R.string.login),
            onClick = { loginViewModel.onEvent(LoginEvent.OnLoginClick("","")) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()

        }
    }

}