package fr.danersharifi.marslens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import fr.danersharifi.marslens.ui.screens.MissionSearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                Scaffold { innerPadding ->
                    // Edge-to-edge content can overlap the system bars (saw during class)
                    // so apply the padding provided by Scaffold
                    MissionSearchScreen(
                        modifier = Modifier.padding(
                            paddingValues = innerPadding,
                        ),
                    )
                }
            }
        }
    }
}