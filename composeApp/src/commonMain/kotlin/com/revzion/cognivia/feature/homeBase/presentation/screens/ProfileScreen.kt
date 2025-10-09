package com.revzion.cognivia.feature.homeBase.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.icon_avatar
import cognivia.composeapp.generated.resources.icon_camera
import cognivia.composeapp.generated.resources.icon_right
import com.revzion.cognivia.app.primaryBlue
import com.revzion.cognivia.app.surfaceBlue
import com.revzion.cognivia.core.utils.Horizontal
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfileScreen(navController: NavController,pv: PaddingValues) {
    Box(modifier=Modifier.fillMaxSize().background(color = surfaceBlue),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = pv.calculateTopPadding()+ 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)) {
            TopRowProfile()
            ImagePickerSection()
            SelectionItems()
        }
    }
}

@Composable
fun SelectionItems() {
    Column(modifier=Modifier.fillMaxWidth().background(primaryBlue)) {
        SelectionItemBody(text="Favourites", onClick = {})
        SelectionItemBody(text="Edit Account", onClick = {})
        SelectionItemBody(text="Settings and Privacy", onClick = {})
        SelectionItemBody(text="Help", onClick = {})
    }
}

@Composable
fun SelectionItemBody(text:String,onClick:()->Unit){
    Row(modifier=Modifier.fillMaxWidth().clickable(onClick = {onClick()}),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text=text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp,
            color = Color.White,
            modifier=Modifier.padding(vertical = 20.dp, horizontal = Horizontal)
        )
        Icon(
            painter = painterResource(Res.drawable.icon_right),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = Horizontal).size(16.dp),
            tint=Color.White
        )
    }
}

@Composable
fun ImagePickerSection() {
    Box(modifier=Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(Res.drawable.icon_avatar),
            contentDescription = null,
            modifier = Modifier.size(76.dp).clip(CircleShape).border(2.dp, primaryBlue,shape= CircleShape)
        )
        Box(modifier=Modifier.size(24.dp)
            .offset(x=(24).dp, y=(-24).dp)
            .clip(CircleShape)
            .background(color = primaryBlue)){
            Icon(
                painter = painterResource(Res.drawable.icon_camera),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().padding(6.dp),
                tint=Color.White
            )
        }
    }
}

@Composable
fun TopRowProfile(){
    Row(modifier=Modifier.fillMaxWidth().padding(horizontal = Horizontal),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text="Account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 28.sp
        )

    }
}