package minmul.kwpass.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import minmul.kwpass.ui.theme.KWPassTheme

@Composable
fun TipBox(
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: ImageVector? = null,
    text: String
) {
    Card(
        modifier = modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                    )

                }
                if (title != null) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
            if (icon == null && title == null) {
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)

            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}


@Preview
@Composable
fun TipBoxPreview() {
    KWPassTheme {
        TipBox(
            title = "Sample Title",
            icon = Icons.Default.Info,
            text = "Gemini gives you a personalized experience using your past chats. You can also give it instructions to customize its responses. "
        )
    }
}

@Preview
@Composable
fun TipBoxNoTitlePreview() {
    KWPassTheme {
        TipBox(
            text = "Gemini gives you a personalized experience using your past chats. You can also give it instructions to customize its responses. "
        )
    }
}