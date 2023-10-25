package com.zwb.filemanager.ui.home.select

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.zwb.filemanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectFunction(modifier: Modifier) {
    ConstraintLayout(modifier = modifier) {
        val (explore, picture, video, audio, file) = createRefs()

        val initTextHint = remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(modifier = Modifier
            .constrainAs(explore) {
                start.linkTo(parent.start, margin = 20.dp)
                end.linkTo(parent.end, margin = 20.dp)
                top.linkTo(parent.top, margin = 20.dp)
                width = Dimension.matchParent
                height = Dimension.value(60.dp)

            },
            maxLines = 1,
            value = initTextHint.value,
            placeholder = { Text(text = stringResource(id = R.string.explore_hint)) },
            onValueChange = { newValue ->
                initTextHint.value = newValue
            })


    }
}