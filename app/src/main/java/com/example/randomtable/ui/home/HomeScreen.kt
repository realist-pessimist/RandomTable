package com.example.randomtable.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomtable.R
import com.example.randomtable.ui.home.model.Cell
import com.example.randomtable.util.replace
import com.example.randomtable.util.shuffled
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
        }
    ) {
        var columns by remember {
            mutableStateOf(
                List(100) { Random.nextInt() }
            )
        }
        LazyColumn(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = columns, key = { it }) {
                var rows by remember {
                    mutableStateOf(
                        List(10) { Cell() }
                    )
                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 1000
                            )
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(items = rows, key = { it.id }) {
                        Card(
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(0.5.dp, Color(0x80cbcbd4)),
                            backgroundColor = Color(0xffF9F9F9)
                        ) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = "${it.number}",
                                fontSize = 12.sp
                            )
                        }
                    }
                    Timer("UpdateCellTick", false).schedule(1000) {
                        val randomCellIndex = (rows.indices).random()
                        val randomCell = rows[randomCellIndex]
                        val newCell = randomCell.copy(number = (0..500).random())
                        rows = rows.replace(newCell) { it == randomCell }
                    }
                }
            }
            Timer("ShuffleColumnTick", false).schedule(1000) {
                columns = columns.toMutableList().shuffled()
            }
        }
    }
}