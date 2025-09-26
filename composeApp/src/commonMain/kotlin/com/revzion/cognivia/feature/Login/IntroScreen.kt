package com.revzion.cognivia.feature.Login

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.arrow_icon
import cognivia.composeapp.generated.resources.intro1
import cognivia.composeapp.generated.resources.intro3
import cognivia.composeapp.generated.resources.intro4
import com.revzion.cognivia.app.primaryBlue
import com.revzion.cognivia.app.surfaceBlue
import com.revzion.cognivia.core.navigation.Routes
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun IntroScreen(navController: NavController, pv: PaddingValues){

    val images=listOf<DrawableResource>(Res.drawable.intro1,Res.drawable.intro4,Res.drawable.intro3,
        Res.drawable.intro4)
    val titles=listOf("Online Learning " +
            "Platform", "Learn on your " +
            "Schedule","Ready to find " +
            "a Course","Explore it " +
            "Today!")
    val descriptions = listOf(
        "Discover an all-in-one platform designed to make learning easy, accessible, and effective for everyone.",
        "Take control of your education with flexible courses you can fit into your daily routine, anytime and anywhere.",
        "Explore subjects that match your goals. Find the best sources and begin learning right away.",
        "Start your journey now and unlock endless opportunities to learn, grow, and achieve your goals."
    )

    val pagerState= rememberPagerState(
        pageCount = {images.size}, initialPage = 0,
    )
    val scope=rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize().background(color = surfaceBlue).padding(bottom=pv.calculateBottomPadding()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6f)
                    .clip(RoundedCornerShape(bottomStart = 44.dp, bottomEnd = 44.dp))
                    .background(Color.White, RoundedCornerShape(bottomStart = 44.dp, bottomEnd = 44.dp))
            ) { page ->
                Image(
                    modifier = Modifier.fillMaxSize().padding(40.dp),
                    painter = painterResource(images[page]),
                    contentDescription = null
                )
            }
            Column(modifier=Modifier.weight(1f).padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)){
                AnimatedContent(
                    targetState = pagerState.currentPage
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(text = titles[it], fontWeight = FontWeight.Medium, fontSize = 24.sp,
                            textAlign = TextAlign.Center
                            )
                        Text(
                            text = descriptions[it],
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )

                    }
                }
                Box(modifier=Modifier.weight(1f).padding(bottom = 28.dp),
                    contentAlignment = Alignment.BottomCenter){
                    NextButton(
                        currentState = pagerState.currentPage,
                        finalState = images.size - 1,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        onFinalClick = {
                            navController.navigate(Routes.HomeGraph)
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun NextButton(
    currentState: Int, finalState: Int, onClick: () -> Unit, onFinalClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val progress by derivedStateOf {
        currentState.toFloat() / finalState.toFloat()
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress, label = "Animated Progress",
        animationSpec = tween(
            durationMillis = 400,
        )
    )

    Row(
        modifier = Modifier.clip(CircleShape).height(64.dp)
        .then(
            if (currentState == finalState) {
                Modifier.clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource()
                )
                { onFinalClick() }
            } else {
                Modifier.clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource()
                )
                { onClick() }
            }
        ).background(if (currentState==finalState) primaryBlue else Color.Transparent).animateContentSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
        )
        {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                color = primaryBlue,
                trackColor = Color.White,
                strokeWidth = 2.dp, progress = { animatedProgress })
            Box(
                modifier = Modifier.size(44.dp)
                    .clip(CircleShape).background(color = primaryBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize().padding(6.dp)

                )
            }
        }

        if (currentState == finalState) {
            Box(contentAlignment = Alignment.Center,modifier=Modifier.fillMaxHeight()) {
                Text(
                    text = "Start Your Journey",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 12.dp, end = 20.dp)
                )
            }
        }

    }
}