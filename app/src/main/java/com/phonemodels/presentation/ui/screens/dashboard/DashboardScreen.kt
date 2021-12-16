package com.phonemodels.presentation.ui.screens.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.phonemodels.R
import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.presentation.ui.components.PhonesListItemView
import com.phonemodels.presentation.ui.screens.entry.EntryPointActivity
import com.phonemodels.presentation.ui.theme.PhoneModelsAppTheme
import com.phonemodels.presentation.utils.LAUNCH_LISTEN_FOR_EFFECTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(navController: NavHostController) {
    val viewModel: DashboardViewModel = hiltViewModel()
    val state = viewModel.viewState.value

    DashboardView(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is DashboardContract.Effect.Navigation.ToPhoneDetails) {
                navController.navigate("${EntryPointActivity.NavigationKeys.Route.PHONE_DETAILS}/${navigationEffect.phoneID}")
            }
        })
}

@Composable
fun DashboardView(
    state: DashboardContract.State,
    effectFlow: Flow<DashboardContract.Effect>?,
    onEventSent: (event: DashboardContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: DashboardContract.Effect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    // Listen for side effects from the VM
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is DashboardContract.Effect.DataWasLoaded ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Phones are loaded.",
                        duration = SnackbarDuration.Short
                    )
                is DashboardContract.Effect.Navigation.ToPhoneDetails -> onNavigationRequested(
                    effect
                )
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Text("Drawer content") },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                    ) {
                        Icon(Icons.Filled.Menu, "")
                    }
                },
                title = { Text(stringResource(R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.background
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    ) {
        Box {
            PhonesList(phones = state.phones) { itemId ->
                onEventSent(DashboardContract.Event.PhoneSelection(itemId))
            }
            if (state.isLoading)
                LoadingBar()
        }
    }
}


@Composable
fun PhonesList(
    phones: List<PhoneEntity>?,
    onItemClicked: (id: Int?) -> Unit = { }
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(phones ?: listOf()) { item ->
            PhoneItemRow(item = item , onItemClicked = onItemClicked)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PhoneItemRow(
    item: PhoneEntity,
    onItemClicked: (id: Int?) -> Unit = { }
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
            .clickable { onItemClicked(item.id) }
    ) {
        PhonesListItemView(item)
    }
}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhoneModelsAppTheme() {
        DashboardView(DashboardContract.State(), null, { }, { })
    }
}
