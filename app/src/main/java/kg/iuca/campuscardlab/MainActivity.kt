package kg.iuca.campuscardlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.iuca.campuscardlab.ui.theme.CampusCardLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusCardLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CampusCardScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CampusCardScreen(modifier: Modifier = Modifier) {
    // Exercise 2
    var useSpaceBetween by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top, //Arrangement.SpaceBetween, Exercise 6
        horizontalAlignment = Alignment.Start
    ) {

        // Exercise 1
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Campus Card", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Ada Lovelace")
            Text(text = "Student ID: 2026001")
        }

        // Exercise 5
        ProfileHeader(
            name = "Ada Lovelace",
            status = "In class · Building B",
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "Today",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Exercise 5
        Column(modifier = Modifier.fillMaxWidth()) {
            ScheduleRow(
                time = "09:00",
                title = "CS101 Intro",
                room = "Lab 3",
                timeStyle = MaterialTheme.typography.headlineMedium, // Exercise 3
                verticalAlignment = Alignment.CenterVertically       // Exercise 3: Top
            )
            ScheduleRow(time = "11:00", title = "MATH210", room = "A-204")
            ScheduleRow(
                time = "14:00",
                title = "Soft Skills and Professional Communication Seminar", // Exercise 4
                room = "Hall 1"
            )
        }

        // Exercise 2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (useSpaceBetween) {
                Arrangement.SpaceBetween
            } else {
                Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            }
        ) {
            Button(onClick = { useSpaceBetween = !useSpaceBetween }) {
                Text("Map")
            }
            OutlinedButton(onClick = { }) {
                Text("Show QR")
            }
        }
    }
}

// Exercise 5
@Composable
fun ProfileHeader(name: String, status: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Column {
            Text(name, style = MaterialTheme.typography.titleMedium)
            Text(status)
        }
    }
}

// Exercise 5
@Composable
fun ScheduleRow(
    time: String,
    title: String,
    room: String,
    modifier: Modifier = Modifier,
    timeStyle: TextStyle = LocalTextStyle.current,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = verticalAlignment
    ) {
        Text(time, style = timeStyle)
        Text(
            title,
            modifier = Modifier.weight(1f), // Exercise 4
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(room)
    }
}

@Preview(showBackground = true)
@Composable
fun CampusCardScreenPreview() {
    CampusCardLabTheme {
        CampusCardScreen()
    }
}