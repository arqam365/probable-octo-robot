package com.revzion.cognivia.feature.homeBase.presentation.viewmodels

import androidx.lifecycle.ViewModel
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.icon_avatar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for Sections (chapters) of a course.
 * Follows the same style as your CourseScreenViewModel.
 */
class SectionScreenViewModel : ViewModel() {

    // Optional: title for the screen / course
    private val _courseTitle = MutableStateFlow("Kotlin Multiplatform Basics")
    val courseTitle = _courseTitle.asStateFlow()

    fun onCourseTitleChange(title: String) {
        _courseTitle.value = title
    }
    // Optional: avatar for the screen / course
    private val _avatarRes = MutableStateFlow(Res.drawable.icon_avatar)
    val avatarRes = _avatarRes.asStateFlow()

    // Sections list (chapters)
    private val _sectionList = MutableStateFlow<List<SectionDisplayItem>>(sectionItemList)
    val sectionList = _sectionList.asStateFlow()

    fun onSectionListChange(list: List<SectionDisplayItem>) {
        _sectionList.value = list
    }

    // Update a single section (e.g., unlock / mark completed)
    fun updateSection(updated: SectionDisplayItem) {
        _sectionList.value = _sectionList.value.map { if (it.id == updated.id) updated else it }
    }
}

/**
 * Simple Section model — if you already have a domain model, move this into domain package
 * and import it instead (like CourseDisplayItem).
 */
data class SectionDisplayItem(
    val id: String,
    val title: String,
    val durationMinutes: Int = 0,
    val isLocked: Boolean = false
)

/**
 * Sample section data — replace with real data from repository / API
 */
val sectionItem1 = SectionDisplayItem(id = "1", title = "Introduction & Overview", durationMinutes = 8, isLocked = false)
val sectionItem2 = SectionDisplayItem(id = "2", title = "Installation & Setup", durationMinutes = 15, isLocked = false)
val sectionItem3 = SectionDisplayItem(id = "3", title = "Core Concepts", durationMinutes = 24, isLocked = false)
val sectionItem4 = SectionDisplayItem(id = "4", title = "Advanced Techniques", durationMinutes = 32, isLocked = true)
val sectionItem5 = SectionDisplayItem(id = "5", title = "Summary & Next Steps", durationMinutes = 10, isLocked = true)

val sectionItemList = listOf(
    sectionItem1,
    sectionItem2,
    sectionItem3,
    sectionItem4,
    sectionItem5,
    sectionItem1.copy(id = "6", title = "Bonus: Interop Tips", durationMinutes = 12),
    sectionItem1.copy(id = "7", title = "Hands-on Lab", durationMinutes = 40),
    sectionItem1.copy(id = "8", title = "Q&A and Resources", durationMinutes = 6)
)