package fr.danersharifi.marslens.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fr.danersharifi.marslens.R
import fr.danersharifi.marslens.data.model.Mission
import fr.danersharifi.marslens.ui.components.MissionCard
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Button

@Composable
fun MissionSearchScreen(
    onSearch: (Mission, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Preserve selected mission across recompositions and activity recreation
    var selectedMission by rememberSaveable {
        mutableStateOf(value = Mission.CURIOSITY)
    }

    var solInput by rememberSaveable {
        mutableStateOf(value = "1000")
    }

    val sol = solInput.toIntOrNull()
    // Bug : numeric keyboard still lets invalid text through when pasting
    // So reject non-digits and values outside the selected mission's sol range
    val isSolInvalid = sol == null
            || sol !in 0..selectedMission.maxSol
            || solInput.any { it !in '0'..'9' }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(state = rememberScrollState())
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.spacedBy(space = 24.dp),
    ) {
        Text(
            text = stringResource(id = R.string.search_title),
            style = MaterialTheme.typography.headlineLarge,
        )

        Text(
            text = stringResource(id = R.string.search_description),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Text(
            text = stringResource(id = R.string.mission_label),
            style = MaterialTheme.typography.titleMedium,
        )

        Column(
            modifier = Modifier.selectableGroup(),
            verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        ) {
            Mission.entries.forEach { mission ->
                val isCuriosity = mission == Mission.CURIOSITY

                MissionCard(
                    title = stringResource(
                        id = if (isCuriosity) {
                            R.string.curiosity
                        } else {
                            R.string.insight
                        },
                    ),
                    description = stringResource(
                        id = if (isCuriosity) {
                            R.string.curiosity_description
                        } else {
                            R.string.insight_description
                        },
                    ),
                    selected = selectedMission == mission,
                    onClick = { selectedMission = mission },
                )
            }
        }

        OutlinedTextField(
            value = solInput,
            onValueChange = { solInput = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(id = R.string.sol_label),
                )
            },
            supportingText = {
                Text(
                    text = stringResource(
                        id = if (isSolInvalid) {
                            R.string.sol_error
                        } else {
                            R.string.sol_help
                        },
                        formatArgs = arrayOf(selectedMission.maxSol),
                    ),
                )
            },
            isError = isSolInvalid,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        )

        Button(
            onClick = {
                // send only validated search criteria
                if (sol != null && !isSolInvalid) {
                    onSearch(selectedMission, sol)
                }
            },
            enabled = !isSolInvalid,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.search_photos),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MissionSearchScreenPreview() {
    MaterialTheme {
        MissionSearchScreen(
            onSearch = { _, _ -> },
        )
    }
}