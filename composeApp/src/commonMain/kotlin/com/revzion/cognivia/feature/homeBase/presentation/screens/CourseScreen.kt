package com.revzion.cognivia.feature.homeBase.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.arrow_icon
import cognivia.composeapp.generated.resources.icon_avatar
import com.revzion.cognivia.app.primaryBlue
import com.revzion.cognivia.app.surfaceBlue
import com.revzion.cognivia.core.Composables.SearchBox
import com.revzion.cognivia.core.enums.CourseFilter
import com.revzion.cognivia.core.utils.Horizontal
import com.revzion.cognivia.feature.homeBase.domain.CourseDisplayItem
import com.revzion.cognivia.feature.homeBase.presentation.viewmodels.CourseScreenViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun CourseScreen(navController: NavController, pv: PaddingValues){

    val viewModel: CourseScreenViewModel = koinInject()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val selectedTab by viewModel.selectedFilter.collectAsStateWithLifecycle()
    val courseList by viewModel.courseList.collectAsStateWithLifecycle()

    Box(modifier=Modifier.fillMaxSize().background(color = surfaceBlue),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = pv.calculateTopPadding()+ 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {
            TopRowCourse()
            SearchSection(searchText=searchText, onSearchTextChange = {it-> viewModel.onSearchTextChange(it)})
            TabsSelection(selectedTab=selectedTab, onSelectedTabChange = {it-> viewModel.onSelectedFilterChange(it)}, courseList=courseList,pv=pv)
        }
    }
}

@Composable
fun CourseListDisplay(
    courseList: List<CourseDisplayItem>,
    onClick: (CourseDisplayItem) -> Unit,
    pv: PaddingValues
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(horizontal = Horizontal - 4.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(courseList) {
            Surface(
                modifier = Modifier.fillMaxWidth()
                    .clickable(onClick = { onClick(it) }, indication = null, interactionSource = MutableInteractionSource()),
                color = Color.White,
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 3.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(it.banner),
                        contentDescription = null,
                        modifier = Modifier.size(72.dp).clip(RoundedCornerShape(16.dp))
                    )
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = it.name,
                            maxLines = 1,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 16.sp
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painter = painterResource(it.ownerImage),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp).clip(CircleShape)
                                    .border(0.5.dp, primaryBlue, shape = CircleShape)
                            )
                            Text(
                                text = it.owner,
                                maxLines = 1,
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "₹" + it.price,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 16.sp,
                                color = primaryBlue,
                            )
                            Surface(
                                color = primaryBlue,
                                contentColor = Color.White,
                                shape = CircleShape,
                            ) {
                                Text(
                                    text = it.duration.toString() + " hours",
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                                    fontSize = 10.sp,
                                    lineHeight = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(pv.calculateBottomPadding() + 8.dp))
        }
    }

}

@Composable
fun TabHeader(heading: String, onSeeAll: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = Horizontal), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(heading,
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
}

@Composable
fun TabsSelection(
    selectedTab: CourseFilter,
    onSelectedTabChange: (CourseFilter) -> Unit,
    courseList: List<CourseDisplayItem>,
    pv: PaddingValues
) {

    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)){
        TabHeader(heading="Available Courses", onSeeAll={})
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = Horizontal)
        ) {
            CourseFilter.entries.forEach { chips ->
                Surface(
                    modifier = Modifier.widthIn(min = 76.dp),
                    color = if (selectedTab == chips) primaryBlue else Color.White,
                    contentColor = if (selectedTab == chips) Color.White else Color.Black,
                    onClick = { onSelectedTabChange(chips) },
                    shape = CircleShape
                ) {
                    Text(
                        text = chips.label,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                    )
                }
            }

        }
        CourseListDisplay(courseList=courseList, onClick = {

        },pv=pv)
    }

}

@Composable
fun SearchSection(searchText: String, onSearchTextChange: (String) -> Unit) {
    Box(modifier=Modifier.fillMaxWidth()){
        SearchBox(
            searchText = searchText,
            onTextChange = {onSearchTextChange(it)},
            placeholderText = "Find Course"
        )
    }
}

@Composable
fun TopRowCourse(){
    Row(modifier=Modifier.fillMaxWidth().padding(horizontal = Horizontal),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Text(
            text="Course",
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 28.sp
        )

        Image(
            painter = painterResource(Res.drawable.icon_avatar),
            contentDescription = null,
            modifier = Modifier.size(36.dp).clip(CircleShape).border(2.dp, primaryBlue,shape=CircleShape))

    }
}