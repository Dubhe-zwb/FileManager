package com.zwb.filemanager.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.zwb.filemanager.model.MainViewModel
import com.zwb.filemanager.ui.home.HomePage

@Composable
fun PageNavigation(mainViewModel: MainViewModel) {
    val uiStates = mainViewModel.uiStates.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (homePage) = createRefs()

        when (uiStates.value.pageFlag) {
            0 -> HomePage(modifier = Modifier.constrainAs(homePage) {
                width = Dimension.matchParent
                height = Dimension.matchParent
            })
        }

    }


}