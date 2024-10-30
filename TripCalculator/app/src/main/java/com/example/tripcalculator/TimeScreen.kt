package com.example.tripcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripcalculator.AppRoutes.TIME

@Composable
fun TimeScreen(navController: NavController, km: Float, modifier: Modifier = Modifier) {
    DataIntery(
        text = R.string.enter_the_total_time,
        fieldLable = R.string.time_in_minutes,
        modifier = modifier,
        isTimeInput = true
    ) {timeField ->
        val time = timeField.toInt()
        navController.navigate("${AppRoutes.TRAFFIC_OPTIONS}/$km/$time")
    }
}

@Preview(showBackground = true)
@Composable
fun TimeScreenPreview() {
    TimeScreen(rememberNavController(),70f)
}