package com.example.datetimenotificationapp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.datetimenotificationapp.ui.theme.DatetimeNotificationAppTheme
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatetimeNotificationAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    createNotificationChannel(LocalContext.current)
                    DateTime(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTime(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        var timeButton by remember { mutableStateOf("Choose a time") }
        var isTimePickerShown by remember { mutableStateOf(false) }
        var dateButton by remember { mutableStateOf("Choose a date") }
        var isDatePickerShown by remember { mutableStateOf(false) }
        var hour by remember { mutableIntStateOf(0) }
        var minute by remember { mutableIntStateOf(0) }
        var timeInMillis by remember { mutableLongStateOf(0) }
        val context = LocalContext.current

        if (isDatePickerShown) {
            DatePickerChooser(
                onConfirm = { dateState ->
                    val calender = Calendar.getInstance()
                    calender.timeInMillis = dateState.selectedDateMillis ?: 0
                    timeInMillis = dateState.selectedDateMillis ?: 0
                    val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                    dateButton = dateFormatter.format(calender.time)
                    isDatePickerShown = false
                }, onDismiss = { isDatePickerShown = false }
            )
        }

        if (isTimePickerShown) {
            TimePickerChooser(
                onConfirm = { timePickerState ->
                    hour = timePickerState.hour
                    minute = timePickerState.minute
                    timeButton = "$hour:$minute"
                    isTimePickerShown = false

                    timeInMillis += (timePickerState.hour * 3600 * 1000) + (timePickerState.minute * 60 * 1000) - (3 * 3600 * 1000)
                    Log.d("trace", "$timeInMillis")

                },
                onDismiss = { isTimePickerShown = false }
            )
        }

        OutlinedButton(onClick = { isTimePickerShown = true }) {
            Text(text = timeButton)
        }
        OutlinedButton(onClick = { isDatePickerShown = true }) {
            Text(text = dateButton)
        }
        OutlinedButton(onClick = {
            sendNotification(
                "Notification Scheduled",
                "Your notification is scheduled on $dateButton $timeButton",
                context
            )
            scheduleNotification(context, timeInMillis)
        }) {
            Text(text = "Send a notification")
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerChooser(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState(is24Hour = true)

    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(timePickerState) }) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text(text = "Cancel") }
        },
        text = { TimePicker(state = timePickerState) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerChooser(onConfirm: (DatePickerState) -> Unit, onDismiss: () -> Unit) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(datePickerState) }) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text(text = "Cancel") }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

private fun createNotificationChannel(context: Context) {
    val name = "Datetime"
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel("1", name, importance)
    channel.description = "Datetime Scheduled Notification"
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)

}

fun sendNotification(title: String, text: String, context: Context) {
    val builder = NotificationCompat.Builder(context, "1")
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle(title)
        .setContentText(text)
        .setAutoCancel(true)

    NotificationManagerCompat.from(context).notify(99, builder.build())
}

fun scheduleNotification(context: Context, timeInMillis: Long) {
    Log.d("trace", "scedule")
    val intent = Intent(context, NotificationReciever::class.java)
    intent.putExtra("title", "New nottification")
    intent.putExtra("text", "Your notification has arrived successfully")

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        200,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    Log.d("trace", "recived time in millies$timeInMillis")

    try {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            pendingIntent
        )
    } catch (e: SecurityException) {
        Log.d("trace", "Error: ${e.message}")
    }


}

@Preview
@Composable
fun DateTimePreview() {
    DateTime()
}