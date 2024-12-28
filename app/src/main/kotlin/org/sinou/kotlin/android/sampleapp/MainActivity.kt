package org.sinou.kotlin.android.sampleapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinContext {
                ComponentExamplePage()
            }
        }
    }
}

@Composable
private fun ComponentExamplePage() {
    val ctx = LocalContext.current
    val showDialog1 = remember { mutableStateOf(false) }
    val itemModifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = 8.dp,
            vertical = 8.dp
        )
        .wrapContentWidth(Alignment.CenterHorizontally)

    val nodeService = koinInject<NodeService>()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Simply test Cells API v2 at ${nodeService.getApiURL()}",
            modifier = itemModifier
        )
        Button(
            onClick = {
                    scope.launch {
                        val res = nodeService.pingServer()
                        val msg = if (res==200) {
                            "Server can be accessed"
                        }  else "cannot reach server - error $res"

                        Toast
                            .makeText(ctx, msg, Toast.LENGTH_LONG)
                            .show()
                    }
            },
            modifier = itemModifier
        ) {
            Text(text = "Ping Server")
        }
        Button(
            onClick = {
                scope.launch {
                    val res = nodeService.createDummyFile()
                    val msg = if (res==200) {
                        "Created a new dummy file"
                    }  else "cannot create file: error $res"
                    Toast
                        .makeText(ctx, msg, Toast.LENGTH_LONG)
                        .show()
                }
            },
            modifier = itemModifier
        ) {
            Text(text = "Create a dummy empty file")
        }
        Button(
            onClick = { showDialog1.value = true },
            modifier = itemModifier,
        )
        { Text(text = "Show Dialog") }
    }
    if (showDialog1.value) {
        Dialog1 { showDialog1.value = false }
    }
}

@Composable
fun Dialog1(dismiss: () -> Unit) {
    val ctx = LocalContext.current
    val textValue = remember {
        mutableStateOf("")
    }
    val updateValue: (String) -> Unit = { textValue.value = it }
    AlertDialog(
        title = { Text("Alert Dialog") },
        text = { DialogContent(textValue.value, updateValue) },
        confirmButton = {
            TextButton(
                onClick = {
                    Toast.makeText(ctx, "OK button clicked", Toast.LENGTH_LONG).show()
                    dismiss()
                }
            ) { Text("OK") }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    Toast.makeText(ctx, "Cancel button clicked!!", Toast.LENGTH_LONG).show()
                    dismiss()
                }
            ) { Text("Cancel") }
        },
        onDismissRequest = { dismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            securePolicy = SecureFlagPolicy.Inherit
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogContent(value: String, updateValue: (String) -> Unit) {
    Column {
        Text("Dialog Content.\n**WARNING**: you are about to click")
        TextField(value = value, onValueChange = { updateValue(it) })
    }
}
