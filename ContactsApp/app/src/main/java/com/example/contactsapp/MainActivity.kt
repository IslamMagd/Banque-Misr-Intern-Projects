package com.example.contactsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.contactsapp.data.DataSource
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        val context = LocalContext.current
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.app_name))},
                            actions = {
                                Icon(
                                    imageVector = Icons.Default.Home
                                    , contentDescription = stringResource(id = R.string.home),
                                    modifier = Modifier.clickable {
                                        val intent = Intent(Intent.ACTION_DIAL)
                                        intent.data = Uri.parse("tel:+4050305030")
                                        context.startActivity(intent)
                                    }
                                )
                            }
                        )
                    }
                )
                { innerPadding ->
                    ContactList(
                        contacts = DataSource().getContactsData(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactListItem(contact: Contact, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .width(100.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${contact.phoneNumber}")
                context.startActivity(intent)
            }

    ) {
        Image(
            painter = painterResource(id = contact.picture), contentDescription = stringResource(
                id = contact.name
            )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(Color.LightGray)
                .fillMaxWidth()

        ) {
            Text(
                text = stringResource(id = contact.name),
                fontSize = 20.sp,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            SelectionContainer {
                Text(text = contact.phoneNumber)
            }
        }
    }

}

@Composable
fun ContactList(contacts: List<Contact>,modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier
        ) {
        items(contacts){
            ContactListItem(contact = it)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ListItemContactPreview() {

}