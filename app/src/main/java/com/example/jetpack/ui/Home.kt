package com.example.jetpack.ui

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
import com.example.jetpack.MyList
import com.example.jetpack.R
import com.example.jetpack.ViewModel

class Home {
    private val viewModel = ViewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreen(onClick: () -> Unit) {
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
                itemsIndexed(viewModel.addData()) { _, item ->
                    MyList().ListItem(data = item)
                }
            }
        }
    }
}