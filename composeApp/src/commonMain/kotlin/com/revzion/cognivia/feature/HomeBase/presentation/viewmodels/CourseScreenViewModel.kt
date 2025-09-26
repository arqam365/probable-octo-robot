package com.revzion.cognivia.feature.HomeBase.presentation.viewmodels

import androidx.lifecycle.ViewModel
import cognivia.composeapp.generated.resources.Res
import cognivia.composeapp.generated.resources.course_banner_placeholder
import cognivia.composeapp.generated.resources.demo_banner
import cognivia.composeapp.generated.resources.icon_avatar
import com.revzion.cognivia.core.enums.CourseFilter
import com.revzion.cognivia.feature.HomeBase.domain.CourseDisplayItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CourseScreenViewModel: ViewModel() {

    private val _searchText= MutableStateFlow("")
    val searchText=_searchText.asStateFlow()

    fun onSearchTextChange(text: String){
        _searchText.value=text
    }

    private val _selectedFilter= MutableStateFlow(CourseFilter.All)
    val selectedFilter=_selectedFilter.asStateFlow()

    fun onSelectedFilterChange(filter: CourseFilter){
        _selectedFilter.value=filter
    }


    private val _courseList= MutableStateFlow<List<CourseDisplayItem>>(courseItemList)
    val courseList=_courseList.asStateFlow()

    fun onCourseListChange(list: List<CourseDisplayItem>){
        _courseList.value=list
    }

}

val courseItem= CourseDisplayItem(name = "Kotlin Multiplatform Basics", rating = 4.5, price = "12000", banner = Res.drawable.demo_banner, owner = "Sharad Singh", ownerImage = Res.drawable.icon_avatar, duration = 12.0)
val courseItemList= listOf(courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem,courseItem)
