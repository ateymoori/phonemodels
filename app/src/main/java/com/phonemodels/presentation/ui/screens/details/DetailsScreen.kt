package com.phonemodels.presentation.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.phonemodels.presentation.ui.components.LoadingBarView
import com.phonemodels.presentation.ui.components.PhoneDetailsView
import com.phonemodels.presentation.ui.components.defaultRate
import com.phonemodels.presentation.ui.theme.Gold
import com.phonemodels.presentation.ui.theme.PhoneModelsAppTheme
import com.phonemodels.presentation.utils.LAUNCH_LISTEN_FOR_EFFECTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun DetailsScreen(navController: NavHostController) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    DetailsView(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        navController = navController
    )
}

@Composable
fun DetailsView(
    state: DetailsContract.State,
    effectFlow: Flow<DetailsContract.Effect>?,
    onEventSent: (event: DetailsContract.Event) -> Unit,
    navController: NavHostController?
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is DetailsContract.Effect.DataWasLoaded -> {}
                DetailsContract.Effect.GotError -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = state.error ?: "error",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(onClick = {
                        onEventSent(DetailsContract.Event.SwitchFavorite(state.phone?.id))
                    }) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            tint = (if (state.phone?.isFavorite == true) Gold else Color.Gray),
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController?.popBackStack()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                },
                title = {
                    Text(state.phone?.name ?: "Phone Details")
                },
                backgroundColor = MaterialTheme.colors.background,
            )

        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            PhoneDetailsView(state.phone)
            if (state.isLoading)
                LoadingBarView()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhoneModelsAppTheme {
        DetailsView(DetailsContract.State(), null, { }, null)
    }
}

