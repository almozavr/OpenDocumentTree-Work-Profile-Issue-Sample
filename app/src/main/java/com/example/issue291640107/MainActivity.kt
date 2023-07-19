package com.example.issue291640107

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.issue291640107.ui.theme.Issue291640107Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Issue291640107Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    content = { MainScreen() },
                )
            }
        }
    }

}

@Composable
private fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val folderPicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenDocumentTree(),
                onResult = { uri -> /* TODO */ }
            )
            Text(text = "if app is launched under work profile, you won't be able to select personal storage folder")
            Button(onClick = { folderPicker.launch(null) }) {
                Text("Pick Document Tree")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "But you're still allowed to select personal storage file")
            val singleDocPicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenDocument(),
                onResult = { uri -> /* TODO */ }
            )
            Button(onClick = { singleDocPicker.launch(arrayOf("*/*")) }) {
                Text("Pick Single Document")
            }
            val multipleDocPicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenMultipleDocuments(),
                onResult = { uri -> /* TODO */ }
            )
            Button(onClick = { multipleDocPicker.launch(arrayOf("*/*")) }) {
                Text("Pick Multiple Documents")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Issue291640107Theme {
        MainScreen()
    }
}