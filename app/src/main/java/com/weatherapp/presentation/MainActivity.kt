package com.weatherapp.presentation
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.weatherapp.presentation.ui.Screens
import com.weatherapp.presentation.ui.component.NavGraph
import com.weatherapp.presentation.ui.theme.DarkBlue
import com.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.weatherapp.presentation.viewmodel.WeatherViewModel
import com.weatherapp.utility.api_service.Connectivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            //api call
            if(Connectivity.hasInternetConnection(this)){
                viewModel.onGetWeatherCardData()
            }

        }

        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBlue
                ) {
                    val navController = rememberNavController()
                    val startDestination  = if(viewModel.state.onComplete){
                        Screens.Home.route
                    } else {
                        Screens.SplashScreen.route
                    }
                    NavGraph(navController = navController, viewModel = viewModel,startDestination)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherAppTheme {
        Surface(Modifier.fillMaxSize()) {

          // SplashScreenUI()
        }
    }
}