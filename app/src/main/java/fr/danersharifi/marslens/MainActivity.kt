package fr.danersharifi.marslens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import fr.danersharifi.marslens.ui.navigation.MarsNavGraph

/**
 * MarsLens lets users search NASA photos by mission and Martian day (sol),
 * browse the results, and view a photo with its associated information.
 *
 * Android Development assessment - Semester 7, IMT Atlantique.
 *
 * @author Daner SHARIFI
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                MarsNavGraph()
            }
        }
    }
}
