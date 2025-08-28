package com.example.weatherapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.model.City
import androidx.compose.material3.IconButton
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.model.MainViewModel
import com.example.weatherapp.ui.nav.Route
import coil.compose.AsyncImage
import com.example.weatherapp.ui.nav.BottomNavItem.HomeButton.icon

@Composable
fun ListPage(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val cityList = viewModel.cities
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(cityList, key = { it.name })
        { city ->
            LaunchedEffect(city.name) {
                if (city.weather == null) {
                    viewModel.loadWeather(city.name)
                }
            }
            CityItem(
                city = city,
                onClick = {
                    viewModel.city = city
                    viewModel.page = Route.Home
                },
                onClose = {
                    viewModel.remove(city)
                },
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun CityItem(
    city: City,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage( model = city.weather?.imgUrl, modifier = Modifier.size(75.dp),
            error = painterResource(id = R.drawable.loading),
            contentDescription = "Imagem" )
        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier,
                text = city.name,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier,
                text = city.weather?.desc?:"carregando...",
                fontSize = 16.sp
            )
        }
        Icon(
            imageVector = icon,
            contentDescription = "Monitorada?",
            modifier = Modifier.size(32.dp).clickable(enabled=viewModel.city != null){
                viewModel.update(
                    viewModel.city!!.copy(
                        isMonitored = !viewModel.city!!.isMonitored))
            }
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}