package com.example.calculatetip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatetip.ui.theme.CalculateTipTheme

class MainActivity : ComponentActivity() {
    var b:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateTipTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column {

                        ShowTipCard(b)
                        Spacer(modifier = Modifier.size(20.dp))
                        b=MainCard()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable

fun GreetingPreview() {
    CalculateTipTheme {
//        Greeting("Android")
//        Column {
//
//            ShowTipCard(b)
//            Spacer(modifier = Modifier.size(20.dp))
//             b=MainCard()
//        }
    }

}


@Composable
fun ShowTipCard(a:Int=0 ) {


    Card (modifier= Modifier
        .fillMaxWidth(1f)
        .padding(20.dp)
        .fillMaxHeight(.20f)
        .clickable(true) { },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors =CardDefaults.cardColors(Color(0xFF224193)),

        ) {

        Column(modifier = Modifier.fillMaxSize(1f)
            ,verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            //1St TEXT
            Text(
                text = "Total Per Person",
                color = Color(0xFFEDC400),
                fontSize = 20.sp,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            //2ND TEXT
            Text(text = "$${a}",
                color = Color(0xFFEDC400),
                fontSize = 30.sp,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCard():Int {

    var split_person = remember { mutableStateOf(1) }
    var bill_amount = remember { mutableStateOf(0) }
    var total_per_person = remember { mutableStateOf(0) }
    var tip by remember { mutableStateOf(0f) }

    //claculation part
    total_per_person.value = bill_amount.value + ( (bill_amount.value * tip.toInt())/100  )
    total_per_person.value /= split_person.value
    var total_tip = (bill_amount.value * tip.toInt())/100


    Card (modifier= Modifier
        .fillMaxWidth(1f)
        .padding(15.dp)
        .fillMaxHeight(.58f)
        .clickable(true) { },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Color.White)
        ) {

        //CArd ER Column
        Column {
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth(1f)
                .padding(10.dp),
                value = bill_amount.value.toString(),
                onValueChange = { bill_amount.value = it.toInt() },
                singleLine = true,
                label = {Text("Bill amount",fontWeight = FontWeight.ExtraBold)},
                textStyle = TextStyle(Color.Black, fontWeight =FontWeight.SemiBold),
                placeholder = {Text(text = "Enter the bill",fontWeight = FontWeight.Normal)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                )


            Spacer(modifier = Modifier.size(25.dp))

            //1ST Row split er increase
            Row (modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 15.dp),) {

                Text("Split",fontWeight = FontWeight.ExtraBold)

                Row (modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    //DECRESER (-) (dsknmks)
                    Card(
                        modifier = Modifier.size(25.dp),
                        shape = RoundedCornerShape(50.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        onClick = { if(split_person.value>=2) split_person.value -=1 }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_remove_24),
                            contentDescription = "",
                            modifier=Modifier.fillMaxSize(),
                        )
                    }

                    Spacer(modifier = Modifier.size(10.dp))



                    Text(text = "${split_person.value}",fontWeight = FontWeight.Bold)
                    
                    Spacer(modifier = Modifier.size(10.dp))

                    //INCRESER (+)
                    Card(
                        modifier = Modifier.size(25.dp),
                        shape = RoundedCornerShape(100.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        onClick = { split_person.value +=1 }
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = "",
                            modifier=Modifier.fillMaxSize(),

                        )
                    }
                }
            }


            Spacer(modifier = Modifier.size(25.dp))


            Row (modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 15.dp),
                ) {

                Text("Tip",fontWeight = FontWeight.ExtraBold)

                Row (modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.Center) {
                Text("$$total_tip",fontWeight = FontWeight.ExtraBold) }

            }

            Spacer(modifier = Modifier.size(25.dp))


            //TIP PERCENTAGE
            Row (modifier = Modifier.fillMaxWidth(1f),
                  horizontalArrangement = Arrangement.Center
                ) {
                     Text(text = "${tip.toInt()}%",fontWeight = FontWeight.Bold)
                  }

            Spacer(modifier = Modifier.size(25.dp))

            Slider ( value = tip, onValueChange = { tip = it },
                modifier = Modifier.padding(start=5.dp,end=5.dp),
                valueRange = 0f..100f,
                steps = 5,
            )

        }

    }
    return total_per_person.value
}