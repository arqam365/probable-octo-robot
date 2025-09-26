package com.revzion.cognivia.core.Composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.icon_search
import com.revzion.cognivia.app.primaryBlue
import org.jetbrains.compose.resources.painterResource


@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    searchText: String,
    placeholderText: String = "",
    onTextChange: (String) -> Unit) {

    BasicTextField(
        value = searchText,
        onValueChange = { onTextChange(it) },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(horizontal = 20.dp).clip(CircleShape)
            .border(width = 1.5.dp,color = primaryBlue, shape = CircleShape)
            .background(Color.White),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                )
                {
                    Icon(
                        painter = painterResource(Res.drawable.icon_search),
                        contentDescription = "Search",
                        modifier = Modifier.padding(start = 20.dp, end = 12.dp).size(18.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                        if (searchText.isEmpty()) {
                            Text(
                                text = placeholderText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                            )
                        }
                        innerTextField()
                    }
                }
            }
        }
    )

}