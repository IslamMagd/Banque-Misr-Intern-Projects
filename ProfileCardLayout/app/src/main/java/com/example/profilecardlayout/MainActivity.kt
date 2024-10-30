package com.example.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilecardlayout.ui.theme.Green
import com.example.profilecardlayout.ui.theme.ProfileCardLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardLayoutTheme {
                ProfileCardLayout(
                    name = stringResource(R.string.name),
                    job = stringResource(R.string.job),
                    gmail = "752001.magdy54@gmail.com",
                    phone = "+01206805626"
                )
            }
        }
    }
}

@Composable
fun ProfileCardLayout(
    name: String,
    job: String,
    gmail: String,
    phone: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.profile_bg),
                contentScale = ContentScale.Crop,
                alpha = 0.4f
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "profile pecture",
            modifier = Modifier
                .size(150.dp)
                .border(
                    border = BorderStroke(4.dp, color = Color.Black),
                    CircleShape
                )
                .background(color = Color.DarkGray, shape = CircleShape)
                .padding(4.dp)
                .clip(CircleShape)
        )
        Text(text = name, fontSize = 50.sp)
        Text(text = job, fontSize = 32.sp, textAlign = TextAlign.Center)
        Row(modifier.padding(top = 16.dp)) {
            Text(
                text = gmail,
                modifier.padding(end = 16.dp),
                textDecoration = TextDecoration.Underline
            )
            Text(text = phone, textDecoration = TextDecoration.Underline)
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
fun ProfileCardLayoutPreview() {
    ProfileCardLayout(
        name = stringResource(R.string.name),
        job = stringResource(R.string.job),
        gmail = "752001.magdy54@gmail.com",
        phone = "+01206805626"
    )
}






