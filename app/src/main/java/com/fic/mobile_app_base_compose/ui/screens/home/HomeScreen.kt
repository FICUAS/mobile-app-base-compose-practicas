package com.fic.mobile_app_base_compose.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.fic.mobile_app_base_compose.R

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_column)),
        horizontalAlignment = Alignment.Start


    ) {
        Text(
            text= stringResource(R.string.home_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Row() {
            Text(
                text = stringResource(R.string.home_email),
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_text))
            )
        }

    }
}