import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phonemodels.presentation.ui.theme.PhoneModelsAppTheme

@Composable
fun DrawerListItemView(item: String,onItemClicked: (id: Int?) -> Unit = { }) {
    PhoneModelsAppTheme {
        Row(
            Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = {onItemClicked}
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.Search, contentDescription = ""
            )
            Text(
                "Settings",
                style = MaterialTheme.typography.h6
            )
        }
    }
}