package com.revzion.cognivia.feature.Login

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.arrow_icon
import cognivia.composeapp.generated.resources.check
import cognivia.composeapp.generated.resources.intro1
import cognivia.composeapp.generated.resources.intro3
import cognivia.composeapp.generated.resources.intro4
import cognivia.composeapp.generated.resources.visibility_off
import cognivia.composeapp.generated.resources.visibility_on
import com.revzion.cognivia.app.primaryBlue
import com.revzion.cognivia.app.surfaceBlue
import com.revzion.cognivia.auth.AuthViewModel
import com.revzion.cognivia.core.navigation.Routes
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

/* --------------------------- INTRO --------------------------- */

@Composable
fun IntroScreen(navController: NavController, pv: PaddingValues) {
    val images = listOf<DrawableResource>(
        Res.drawable.intro1, Res.drawable.intro4, Res.drawable.intro3, Res.drawable.intro4
    )
    val titles = listOf(
        "Online Learning Platform",
        "Learn on your Schedule",
        "Ready to find a Course",
        "Explore it Today!"
    )
    val descriptions = listOf(
        "Discover an all-in-one platform designed to make learning easy, accessible, and effective for everyone.",
        "Take control of your education with flexible courses you can fit into your daily routine, anytime and anywhere.",
        "Explore subjects that match your goals. Find the best sources and begin learning right away.",
        "Start your journey now and unlock endless opportunities to learn, grow, and achieve your goals."
    )

    val pagerState = rememberPagerState(pageCount = { images.size }, initialPage = 0)
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(surfaceBlue)
            .padding(bottom = pv.calculateBottomPadding()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .clip(RoundedCornerShape(bottomStart = 44.dp, bottomEnd = 44.dp))
                    .background(Color.White, RoundedCornerShape(bottomStart = 44.dp, bottomEnd = 44.dp))
            ) { page ->
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    painter = painterResource(images[page]),
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                AnimatedContent(targetState = pagerState.currentPage) { idx ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = titles[idx],
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = descriptions[idx],
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 28.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    NextButton(
                        currentState = pagerState.currentPage,
                        finalState = images.size - 1,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        onFinalClick = {
                            // Go to Auth (SignUp/Login) after intro
                            navController.navigate(Routes.Auth)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NextButton(
    currentState: Int,
    finalState: Int,
    onClick: () -> Unit,
    onFinalClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val progress by remember(currentState, finalState) {
        mutableStateOf(currentState.toFloat() / finalState.toFloat())
    }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        label = "Animated Progress",
        animationSpec = tween(durationMillis = 400)
    )

    Row(
        modifier = modifier
            .clip(CircleShape)
            .height(64.dp)
            .then(
                if (currentState == finalState) {
                    Modifier.clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) { onFinalClick() }
                } else {
                    Modifier.clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) { onClick() }
                }
            )
            .background(if (currentState == finalState) primaryBlue else Color.Transparent)
            .animateContentSize()
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                color = primaryBlue,
                trackColor = Color.White,
                strokeWidth = 2.dp,
                progress = { animatedProgress }
            )
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(primaryBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp)
                )
            }
        }

        if (currentState == finalState) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()) {
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

/* --------------------------- AUTH --------------------------- */

enum class AuthMode { SignUp, Login }

@Composable
fun AuthScreen(
    navController: NavController,
    mode: AuthMode = AuthMode.SignUp
) {
    val vm: AuthViewModel = koinViewModel()
    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()
    val loggedIn by vm.isLoggedIn.collectAsState()
    var currentMode by remember { mutableStateOf(mode) }
    var showSuccess by remember { mutableStateOf(false) }

    // ❌ REMOVE this whole block (it referenced mainNav and caused flicker)
    // LaunchedEffect(loggedIn) { ... }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(surfaceBlue)
            .systemBarsPadding()
    ) {
        when (currentMode) {
            AuthMode.SignUp -> SignUpForm(
                loading = loading,
                onSwitchToLogin = { currentMode = AuthMode.Login },
                onSubmit = { email, pass ->
                    vm.signUp(email, pass) { showSuccess = true }
                }
            )
            AuthMode.Login -> LoginForm(
                loading = loading,
                onSwitchToSignUp = { currentMode = AuthMode.SignUp },
                onSubmit = { email, pass ->
                    vm.login(email, pass) {
                        navController.navigate(
                            route = Routes.HomeGraph,
                            navOptions = navOptions {
                                popUpTo(Routes.Auth) { inclusive = true }
                                launchSingleTop = true
                            }
                        )
                    }
                },
                onGoogleIdToken = { idToken ->
                    vm.loginWithGoogle(idToken) {
                        navController.navigate(
                            route = Routes.HomeGraph,
                            navOptions = navOptions {
                                popUpTo(Routes.Auth) { inclusive = true }
                                launchSingleTop = true
                            }
                        )
                    }
                }
            )
        }

        if (showSuccess) {
            SuccessSheet(
                title = "Success",
                message = "Congratulations, you have completed your registration!",
                onDone = { showSuccess = false; currentMode = AuthMode.Login }
            )
        }
    }
}

@Composable
private fun SignUpForm(
    loading: Boolean,
    onSwitchToLogin: () -> Unit,
    onSubmit: (email: String, password: String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var passVisible by remember { mutableStateOf(false) }
    var agree by remember { mutableStateOf(false) }

    val emailValid = remember(email) { EMAIL_REGEX.matches(email.trim()) }
    val canSubmit = emailValid && pass.isNotBlank() && agree

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Sign Up", fontSize = 36.sp, fontWeight = FontWeight.Bold)
        Text("Enter your details below & free sign up", fontSize = 16.sp, color = Color(0xFF6D6D6D))

        FieldLabel("Your  Email")
        PillTextField(
            value = email,
            onValueChange = { email = it },
            trailing = {
                AnimatedVisibility(visible = emailValid) {
                    Icon(painter = painterResource(Res.drawable.check), contentDescription = null)
                }
            }
        )

        FieldLabel("Password")
        PillTextField(
            value = pass,
            onValueChange = { pass = it },
            isPassword = true,
            passwordVisible = passVisible,
            onTogglePassword = { passVisible = !passVisible },
        )

        PrimaryButton(
            text = if (loading) "Creating..." else "Create account",
            enabled = canSubmit && !loading,
            onClick = { onSubmit(email, pass) }   // ⟵ uses lambda from AuthScreen
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = agree, onCheckedChange = { agree = it })
            Spacer(Modifier.width(4.dp))
            Text(
                buildAnnotatedString {
                    append("By creating an account you have to agree with our ")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append("term") }
                    append(" & ")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append("condition") }
                    append(".")
                },
                fontSize = 14.sp, color = Color(0xFF6D6D6D)
            )
        }

        Spacer(Modifier.weight(1f))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Already have an account ?  ", color = Color(0xFF6D6D6D))
            Text(
                text = "Log in",
                color = primaryBlue,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onSwitchToLogin() }
            )
        }
    }
}

@Composable
private fun LoginForm(
    loading: Boolean,
    onSwitchToSignUp: () -> Unit,
    onSubmit: (email: String, password: String) -> Unit,
    onGoogleIdToken: (idToken: String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var passVisible by remember { mutableStateOf(false) }
    val emailValid = remember(email) { EMAIL_REGEX.matches(email.trim()) }
    val canSubmit = emailValid && pass.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Log In", fontSize = 36.sp, fontWeight = FontWeight.Bold)

        FieldLabel("Your  Email")
        PillTextField(
            value = email,
            onValueChange = { email = it },
            trailing = {
                AnimatedVisibility(visible = emailValid) {
                    Icon(painter = painterResource(Res.drawable.arrow_icon), contentDescription = null)
                }
            }
        )

        FieldLabel("Password")
        PillTextField(
            value = pass,
            onValueChange = { pass = it },
            isPassword = true,
            passwordVisible = passVisible,
            onTogglePassword = { passVisible = !passVisible },
            trailingText = {
                Text(
                    "Forget password?",
                    fontSize = 14.sp,
                    color = Color(0xFF8C8C8C),
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .clickable { /* navigate to reset */ }
                )
            }
        )

        PrimaryButton(
            text = if (loading) "Logging in..." else "Log In",
            enabled = canSubmit && !loading,
            onClick = { onSubmit(email, pass) }   // ⟵ uses lambda from AuthScreen
        )

        Spacer(Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Don’t have an account? ", color = Color(0xFF6D6D6D))
            Text(
                "Sign up",
                color = primaryBlue,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onSwitchToSignUp() }
            )
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally)
        ) {
            SocialCircle("G") { /* Google */ }
            SocialCircle("f") { /* Facebook */ }
        }
    }
}

/* ---------------------- shared UI bits ---------------------- */

@Composable
private fun FieldLabel(text: String) {
    Text(text, fontSize = 16.sp, color = Color(0xFF6D6D6D), modifier = Modifier.padding(top = 4.dp))
}

@Composable
private fun PillTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePassword: (() -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    trailingText: (@Composable () -> Unit)? = null,
) {

    val eyeRes = if (passwordVisible) Res.drawable.visibility_off else Res.drawable.visibility_on

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, Color(0xFFE4E6EB), CircleShape)
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isPassword) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
                singleLine = true,
                placeholder = { Text("email@example.com", color = Color(0xFFB8B8B8)) },
                colors = textFieldColorsNoIndicator()
            )
        } else {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                placeholder = { Text("••••••••••", color = Color(0xFFB8B8B8)) },
                colors = textFieldColorsNoIndicator()
            )
            IconButton(onClick = { onTogglePassword?.invoke() }) {
                Icon(painter = painterResource(eyeRes), contentDescription = null, tint = Color(0xFF6D6D6D))
            }
        }

        trailingText?.invoke()
        if (!isPassword) {
            Spacer(Modifier.width(6.dp))
            trailing?.invoke()
        }
    }
}

@Composable
private fun PrimaryButton(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = primaryBlue),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(CircleShape)
    ) { Text(text, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White) }
}

@Composable
private fun SocialCircle(label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, Color(0xFFE4E6EB), CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) { Text(label, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3B3B3B)) }
}

@Composable
private fun SuccessSheet(title: String, message: String, onDone: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(surfaceBlue.copy(alpha = 0.95f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.82f)
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(primaryBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(Res.drawable.arrow_icon), contentDescription = null, tint = Color.White)
            }
            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
            Text(message, fontSize = 14.sp, color = Color(0xFF6D6D6D), textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = onDone,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = primaryBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) { Text("Done", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold) }
        }
    }
}

/* helpers */
private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun textFieldColorsNoIndicator() = TextFieldDefaults.colors(
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    cursorColor = primaryBlue
)