package com.wahidabd.onelibrary.utils

import com.wahidabd.onelibrary.data.TestModel

object Constant {

    fun testData(): List<TestModel> =
         listOf(
            TestModel(
                1,
                "Recycler Activity",
                "Base Recycler",
            ),
             TestModel(
                 2,
                 "Async Recycler Activity",
                 "Async Base Recycler",
             ),
             TestModel(
                 3,
                 "View Pager 2",
                 "Base View Pager 2",
             ),
             TestModel(
                 4,
                 "Api with RxJava",
                 "Base implement api with rx",
             ),
             TestModel(
                 5,
                 "Image Picker",
                 "Base image picker with imagepickerutils"
             ),
             TestModel(
                 6,
                 "Permission",
                 "Test base permission"
             )
        )

    fun dataList(): List<String> =
        listOf(
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
            "Lorem ipsum dolor is amet",
        )


}