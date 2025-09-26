package com.revzion.cognivia.feature.HomeBase.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.arrow_icon
import cognivia.composeapp.generated.resources.banner_demo
import cognivia.composeapp.generated.resources.course_banner_placeholder
import cognivia.composeapp.generated.resources.icon_avatar
import cognivia.composeapp.generated.resources.icon_star
import cognivia.composeapp.generated.resources.icon_unfilled_star
import com.revzion.cognivia.app.primaryBlue
import com.revzion.cognivia.app.surfaceBlue
import com.revzion.cognivia.core.Composables.SearchBox
import com.revzion.cognivia.core.utils.formatMoney
import com.revzion.cognivia.feature.HomeBase.domain.CourseDisplayItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(navController: NavController, pv: PaddingValues, drawerState: DrawerState){
    val courseCategories = listOf("Web Development", "Mobile Development", "Data Science", "Machine Learning", "Artificial Intelligence", "Cloud Computing", "Cybersecurity", "UI/UX Design", "Game Development", "Blockchain", "DevOps", "Digital Marketing", "Business Analytics", "Software Testing", "Programming Languages")
    val courseItem= CourseDisplayItem(name = "Kotlin Multiplatform Basics", rating = 4.5, price = "12000", banner = Res.drawable.course_banner_placeholder, owner = "Sharad Singh", ownerImage = Res.drawable.icon_avatar, duration = 2.5)
    val courseItemList= listOf(courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem)
    val scope= rememberCoroutineScope()

        Box(
            modifier = Modifier.fillMaxSize().background(color = surfaceBlue),
            contentAlignment = Alignment.Center
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                stickyHeader {
                    IntroBanner(
                        pv = pv,
                        name = "Sharad",
                        openDrawer={ scope.launch{ drawerState.open() }}
                    )
                }

                item {
                    BannerItem()
                }

                item {
                    CategorySection(labels = courseCategories, onClick = {})
                }

                item {
                    TopCoursesSection(onClick = {}, courseItemList)
                }

                item {
                    Spacer(
                        modifier = Modifier.padding(bottom = pv.calculateBottomPadding() + 8.dp)
                    )
                }
            }
        }
}

@Composable
fun TopCoursesSection(onClick: () -> Unit, courseItemList: List<CourseDisplayItem>) {

    val chunkedItems= courseItemList.chunked(2)

    Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp))
    {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Top Courses",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.Black
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "See All",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = primaryBlue,
                    modifier = Modifier.clickable(onClick = {})
                )

                Icon(
                    painter = painterResource(Res.drawable.arrow_icon),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp).clickable(onClick = {}),
                    tint = primaryBlue,
                )
            }
        }
        Column(modifier=Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)){
            chunkedItems.forEach {
                Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    it.forEach {item->
                        CourseItem(courseDisplayItem = item, onClick = {onClick()})
                    }
                }
            }
        }
    }
}

@Composable
fun CourseItem(courseDisplayItem: CourseDisplayItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier.width(160.dp).clickable(onClick = { onClick() }, indication = null, interactionSource = MutableInteractionSource()),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(courseDisplayItem.banner),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(104.dp).clip(RoundedCornerShape(10)),
            contentScale = ContentScale.Crop
        )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = courseDisplayItem.rating.toString(),
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )

            Row(horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically) {
                repeat(courseDisplayItem.rating.toInt()) {
                    Image(
                        painter = painterResource(Res.drawable.icon_star),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                }
                repeat(5-(courseDisplayItem.rating.toInt())){
                    Image(
                        painter = painterResource(Res.drawable.icon_unfilled_star),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
        Text(
            text = courseDisplayItem.name,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Image(
                painter = painterResource(courseDisplayItem.ownerImage),
                contentDescription = null,
                modifier = Modifier.size(14.dp).clip(CircleShape).border(0.5.dp, primaryBlue,shape=CircleShape)
            )
            Text(
                text = courseDisplayItem.owner,
                maxLines = 1,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Color.Black
            )
        }

        Text(
            text= "₹"+ formatMoney(courseDisplayItem.price),
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xffFF9D42)
        )
    }
}

@Composable
fun CategorySection(labels: List<String>, onClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text("Categories",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.Black)

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically){
                Text(
                    "See All",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = primaryBlue,
                    modifier = Modifier.clickable(onClick={})
                )

                Icon(
                    painter = painterResource(Res.drawable.arrow_icon),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp).clickable(onClick={}),
                    tint = primaryBlue,
                )
            }
        }

        LazyRow(){
            item{
                FlowRow(maxItemsInEachRow = labels.size / 2, maxLines = 2,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    labels.forEach {
                        CourseCategoryLabelItem(label = it, onClick = { onClick })
                    }
                }
            }
        }
    }
}

@Composable
fun CourseCategoryLabelItem(label: String, onClick: (String) -> Unit) {
    Surface(
        color = Color.White,
        border = BorderStroke(1.5.dp, Color.Black),
        shape = RoundedCornerShape(30),
        modifier = Modifier.clip(RoundedCornerShape(30)).clickable(onClick={onClick(label)}),
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
fun BannerItem() {
    Box(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 28.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(20))
            .background(Color.White, RoundedCornerShape(20))
    ) {
        Image(
            painter = painterResource(Res.drawable.banner_demo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)){
            Text(
                text = "Explore",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xffFF9D42),
            )
            Icon(
                painter = painterResource(Res.drawable.arrow_icon),
                modifier = Modifier.size(16.dp),
                contentDescription = null,
                tint = Color(0xffFF9D42)
            )
        }
    }
}

@Composable
fun IntroBanner(pv: PaddingValues, name: String, openDrawer: () -> Job){

    var searchText by remember { mutableStateOf("") }

    Box(modifier=Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = (pv.calculateTopPadding()+30.dp)),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    painter = painterResource(Res.drawable.icon_avatar),
                    contentDescription = null,
                    modifier = Modifier.size(52.dp).clip(CircleShape)
                        .border(2.dp, primaryBlue,shape=CircleShape)
                        .clickable(onClick = {openDrawer()})
                )
                Column() {
                    Text("Hi, $name",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp)
                    Text("Let’s start learning",
                        lineHeight = 12.sp,
                        fontSize = 12.sp)
                }
            }
            Box(modifier = Modifier.fillMaxWidth().offset(y=(27).dp)){
                SearchBox(
                    searchText = searchText,
                    onTextChange = {
                        searchText = it
                    },
                    placeholderText = "Search here..."
                )
            }
        }
    }
}