package com.example.qrcodebank.ui.main

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.qrcodebank.R

class MainScreen(val viewModel: MainViewModel) {

    lateinit var context: Context
    lateinit var activity: Activity
    lateinit var lifeCycle: LifecycleOwner

    @Composable
    fun MyScaffold() {
        context = LocalContext.current
        activity = LocalActivity.current!!
        lifeCycle = LocalLifecycleOwner.current
        Scaffold(
            topBar = { Title() },
            floatingActionButton = { ActionButton() },
            content = { LazyGrid(it) })
    }

    @Composable
    fun LazyGrid(it: PaddingValues) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
                .padding(top = it.calculateTopPadding())
        ) {
            items(100) { position ->
                Row {
                    MyListItem(
                        position = position,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
    @Composable

    fun Title() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.background),
                            colorResource(R.color.white)
                        )
                    )
                )
                .padding(top = 30.dp)
        ) {
            Text(
                "QRCode Bank",
                modifier = Modifier
                    .padding(bottom = 8.dp),
                fontSize = 24.sp
            )
        }
    }

@Composable
fun ActionButton() {
    val context = LocalContext.current
    SmallFloatingActionButton(
        onClick = { addQrCode(context) },
        shape = CircleShape,
        containerColor = colorResource(R.color.background),
        modifier = Modifier
            .size(120.dp)
            .padding(bottom = 50.dp, end = 50.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            colorResource(R.color.white),
                            colorResource(R.color.background),
                        )
                    )
                )
        ) {
            Text("+", fontSize = 30.sp)
        }
    }
}

    private fun addQrCode(context: Context) {

    }

    @Composable
    fun MyListItem(
        position: Int = 0
    ) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.Gray)
                .padding(2.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.White)
                .clickable { Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show() }
        ) {
            Image(
                painter = painterResource(R.drawable.holder),
                "$position",
                modifier = Modifier
                    .padding(4.dp)

            )
            Text(
                "$position БарашкинсБарашкинс",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
    }
}