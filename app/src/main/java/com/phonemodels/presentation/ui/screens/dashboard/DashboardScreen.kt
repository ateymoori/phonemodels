package com.phonemodels.presentation.ui.screens.dashboard

import DrawerListItemView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.phonemodels.R
import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.presentation.ui.components.LoadingBarView
import com.phonemodels.presentation.ui.components.PhonesListItemView
import com.phonemodels.presentation.ui.components.SearchBarView
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
        navController = navController,
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
    navController: NavHostController?,
    onNavigationRequested: (navigationEffect: DashboardContract.Effect.Navigation) -> Unit
) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var inSearchMode by remember { mutableStateOf(false) }
    // Listen for side effects from the VM
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is DashboardContract.Effect.DataWasLoaded -> {}

                is DashboardContract.Effect.Navigation.ToPhoneDetails -> onNavigationRequested(
                    effect
                )
                DashboardContract.Effect.GotError -> {

                }
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerList(
                listOf(
                    DrawerItems("Home", Icons.Filled.Home),
                    DrawerItems("Settings", Icons.Filled.Settings),
                    DrawerItems("Help", Icons.Filled.Email)
                )
            )
        },
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(onClick = {
                        inSearchMode = !inSearchMode
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = null, modifier = Modifier.testTag("searchButton"))
                    }
                },
                navigationIcon = {
                    if (inSearchMode) {
                        IconButton(
                            onClick = {
                                inSearchMode = false
                            }
                        ) {
                            Icon(Icons.Filled.ArrowBack, "")
                        }
                    } else {
                        IconButton(
                            modifier = Modifier.testTag("drawerIcon"),
                            onClick = {
                                scope.launch { scaffoldState.drawerState.open() }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, "")
                        }
                    }
                },

                title = {
                    if (!inSearchMode) {
                        Text(modifier = Modifier.testTag("pageTitle") , text = stringResource(R.string.app_name))
                    } else {
                        SearchBarView(placeholderText = "Search a Phone", searchText =
                        state.searchValue ?: "",
                            onSearchTextChanged = {
                                onEventSent(DashboardContract.Event.SearchValueChanged(it))
                            })
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
            )

        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            PhonesList(phones = state.phones) { itemId ->
                onEventSent(DashboardContract.Event.PhoneSelected(itemId))
            }
            if (state.isLoading)
                LoadingBarView()
        }
    }

    //handle onBackPressed
    BackHandler {
        if (inSearchMode) {
            inSearchMode = false
            onEventSent(DashboardContract.Event.SearchValueChanged(""))
        } else {
            navController?.popBackStack()
        }
    }
}


@Composable
fun PhonesList(
    phones: List<PhoneEntity>?,
    onItemClicked: (id: Int?) -> Unit = { }
) {
    LazyColumn(
        modifier = Modifier.testTag("phonesList"),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(phones ?: listOf()) { item ->
            PhoneItemRow(item = item, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun DrawerList(
    items: List<DrawerItems>,
    onItemClicked: (id: Int?) -> Unit = { }
) {
    LazyColumn(Modifier.testTag("drawerList")) {
        items(items) { item ->
            DrawerListItemView(item = item, onItemClicked = onItemClicked)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhoneModelsAppTheme {
        DashboardView(DashboardContract.State(), null, { }, null, {})
    }
}

data class DrawerItems(
    val title: String,
    val icon: ImageVector
)
