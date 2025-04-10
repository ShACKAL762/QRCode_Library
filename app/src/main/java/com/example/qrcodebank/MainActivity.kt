package com.example.qrcodebank

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrcodebank.ui.theme.QRCodeBankTheme
import kotlin.math.max

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRCodeBankTheme {
                MainColumn()
            }
        }
    }
}

@Preview(widthDp = 340, heightDp = 700)
@Composable
fun MainColumn() {
    Column() {
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            items(100) { position ->

                println("Build item at position $position")

                Row {
                    MyListItem(
                        position = position,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
AddButton(Alignment.BottomEnd)
}

@Composable
fun AddButton(alignment: Alignment){
    val context = LocalContext.current
    Box(contentAlignment = alignment,
        modifier =
        Modifier.fillMaxSize(fraction = 1f)){
        SmallFloatingActionButton (
            onClick = {Toast.makeText(
                context,
                "Тут будет создание нового кур кода",
                Toast.LENGTH_SHORT).show()},
            shape = CircleShape,
            containerColor = colorResource(R.color.background),
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 50.dp, end = 50.dp)) {
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
            ){
            Text("+", fontSize = 30.sp)
        }
        }

    }
}
@Preview(widthDp = 150, heightDp = 150)
@Composable
fun MyListItem(
    position: Int = 0,
    color: Color = Color.Gray,
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.Gray)
            .padding(2.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.White)
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
                .padding(start = 8.dp))
    }
}
