package com.example.jetpack.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.jetpack.CardsList
import com.example.jetpack.R
import com.example.jetpack.Repository
import com.example.jetpack.model.CardsListModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
class HomeScreen {
    val repository=Repository()

    @Inject
    lateinit var viewModel:HomeViewModel
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowHomeContent(onClick: () -> Unit) {
        viewModel=HomeViewModel(repository = repository)
        Column {
            TopAppBar(modifier = Modifier
                .fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(colorResource(id = R.color.grey)),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Bank",
                        textAlign = TextAlign.Center,
                        color = colorResource(
                            id = R.color.white
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null,
                            tint = colorResource(id = R.color.white)
                        )
                    }
                })

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(viewModel.cardsList) { _, item ->
                    CardsList().ListItem(data = item)
                }
            }
        }
    }
}