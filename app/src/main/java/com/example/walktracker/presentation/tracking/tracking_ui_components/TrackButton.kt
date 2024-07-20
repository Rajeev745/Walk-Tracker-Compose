import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TrackButton(
    onClick: () -> Unit,
    text: String,
    @DrawableRes iconResId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
        ),
        shape = CircleShape
    ) {
        Row {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
        }
    }
}
