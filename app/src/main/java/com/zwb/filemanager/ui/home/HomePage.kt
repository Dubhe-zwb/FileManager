package com.zwb.filemanager.ui.home

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.zwb.filemanager.ui.home.select.SelectFunction
import com.zwb.filemanager.ui.home.storage.StorageList

@Composable
fun HomePage(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.background(Color.White)) {

        val (selectArea, storageList) = createRefs()

        SelectFunction(modifier = Modifier.constrainAs(selectArea) {
            width = Dimension.matchParent
            height = Dimension.wrapContent
        })

        StorageList(modifier = Modifier.constrainAs(storageList) {
            width= Dimension.matchParent
            top.linkTo(selectArea.bottom)
            bottom.linkTo(parent.bottom)
        })

    }
}