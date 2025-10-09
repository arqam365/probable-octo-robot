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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revzion.cognivia.app.primaryBlue
import com.revzion.cognivia.app.surfaceBlue
import com.revzion.cognivia.core.utils.Horizontal
import com.revzion.cognivia.feature.homeBase.presentation.viewmodels.SectionDisplayItem
import com.revzion.cognivia.feature.homeBase.presentation.viewmodels.SectionScreenViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun SectionScreen(
    navController: NavController,
    pv: PaddingValues,
    onSectionClick: (SectionDisplayItem) -> Unit = {}
) {
    // Inject viewmodel
    val viewModel: SectionScreenViewModel = koinInject()

    // Collect state from ViewModel
    val courseTitle by viewModel.courseTitle.collectAsStateWithLifecycle()
    val avatarRes by viewModel.avatarRes.collectAsStateWithLifecycle()
    val sections by viewModel.sectionList.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = surfaceBlue),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = pv.calculateTopPadding() + 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopRowSection(title = courseTitle, avatarRes = avatarRes)
            // (Optional) add intro / progress / buttons here

            SectionListDisplay(
                sections = sections,
                onClick = onSectionClick,
                pv = pv
            )
        }
    }
}

@Composable
fun TopRowSection(title: String, avatarRes: DrawableResource) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Horizontal),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 28.sp
        )

        // Avatar
        Image(
            painter = painterResource(avatarRes),
            contentDescription = "avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(2.dp, primaryBlue, shape = CircleShape)
                .clickable { /* navigate to profile/settings if needed */ }
        )
    }
}

@Composable
fun SectionListDisplay(
    sections: List<SectionDisplayItem>,
    onClick: (SectionDisplayItem) -> Unit,
    pv: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Horizontal),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sections) { section ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onClick(section) }, indication = null, interactionSource = MutableInteractionSource()),
                color = Color.White,
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Left: chapter number / icon
                    Surface(
                        modifier = Modifier.size(44.dp),
                        shape = CircleShape,
                        color = if (section.isLocked) Color.LightGray else primaryBlue,
                        contentColor = Color.White
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = section.id,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    // Middle: title + meta
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = section.title,
                            maxLines = 1,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "${section.durationMinutes} min",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    // Right: lock / play text (replace with Icon if desired)
                    if (section.isLocked) {
                        Text(
                            text = "Locked",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    } else {
                        Text(
                            text = "Play",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = primaryBlue
                        )
                    }
                }
            }
        }

        // bottom spacer to respect navbars / insets
        item {
            Spacer(modifier = Modifier.height(pv.calculateBottomPadding() + 8.dp))
        }
    }
}