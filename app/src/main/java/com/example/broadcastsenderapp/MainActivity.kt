package com.example.broadcastsenderapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.broadcastsenderapp.ui.theme.BroadcastSenderAppTheme

// Explicit Broadcast Sender: This app sends an explicit broadcast to a specific app (com.example.broadcastreceiverapp) when the button is clicked.
// Interaction between 2 apps with the help of Dynamic Broadcast Receiver.
// Note: The target app must be installed on the device for the broadcast to be received.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BroadcastSenderAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        onSendBroadcast = {
                            // Send broadcast when button clicked
                            val intent = Intent("com.example.broadcastreceiverapp.TEST_ACTION")
                            intent.setPackage("com.example.broadcastreceiverapp")  // Explicit Broadcast: Because we are explicitly specifying the target package, so other apps will not be able to receive this broadcast/event.
                            sendBroadcast(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onSendBroadcast: () -> Unit = {}) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Sender $name!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onSendBroadcast() }) {
            Text("Send Broadcast")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BroadcastSenderAppTheme {
        Greeting("Android App")
    }
}
