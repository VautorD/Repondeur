package com.example.repondeur2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.repondeur2.ui.theme.Repondeur2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Repondeur2Theme {
                val titles = listOf("Contacts", "Auto Responses", "Send")
                var currentPage by remember { mutableStateOf(0) }

                //La mise en page de l application
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Your App Name") },
                            backgroundColor = Color.Blue
                        )
                    },
                    //Se trouve le contenu principal
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            //Barre d onglets affichant les titres
                            TabRow(
                                selectedTabIndex = currentPage,
                                backgroundColor = Color.Blue,
                                divider = {
                                    Divider(color = Color.White)
                                }
                            ) {
                                //Un onglet pour chaque titre
                                titles.forEachIndexed { index, title ->
                                    Tab(
                                        text = { Text(title) },
                                        selected = currentPage == index,
                                        onClick = {
                                            currentPage = index
                                        }
                                    )
                                }
                            }
                            //Onglet actif
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                //On affiche le contenu en fonction de l onglet actif
                                when (currentPage) {
                                    0 -> ContactsTab()
                                    1 -> AutoResponsesTab()
                                    2 -> SendTab()
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

// Creation d une classe pour les contacts
data class Contact(val name: String, val phoneNumber: String)

//Liste de contacts
val contactsList = listOf(
    Contact("John Doe", "123-456-7890"),
    Contact("Jane Smith", "555-123-4567"),
    Contact("Alice Johnson", "987-654-3210")
)

//Afficher la liste des contacts
@Composable
fun ContactsTab() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(contactsList.size) { index ->
            ContactItem(contact = contactsList[index])
        }
    }
}

//Afficher le nom et le numéro d un contact
@Composable
fun ContactItem(contact: Contact) {
    Text(text = "${contact.name}: ${contact.phoneNumber}")
}

//Onglet des reponses auto
@Composable
fun AutoResponsesTab() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Reponse automatique", style = MaterialTheme.typography.h5)
    }
}

//Onglet d envoi
@Composable
fun SendTab() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Envoie", style = MaterialTheme.typography.h5)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Repondeur2Theme {
        ContactsTab()
    }
}