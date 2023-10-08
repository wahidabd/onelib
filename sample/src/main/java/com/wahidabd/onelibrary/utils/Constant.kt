package com.wahidabd.onelibrary.utils

import com.wahidabd.onelibrary.domain.base.TestModel

object Constant {

    const val MIN_LENGTH: Int = 10
    const val MAX_LENGTH: Int = 13

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
             ),
             TestModel(
                 7,
                 "Notification",
                 "Base notification"
             ),
             TestModel(
                 8,
                 "Rx Local Database",
                 "Base Local Database With Rx"
             ),
             TestModel(
                 9,
                 "Multi State View",
                 "Multi State View"
             ),
             TestModel(
                 10,
                 "Rx Paging",
                 "Base RxPagingAdapter"
             ),
             TestModel(
                 11,
                 "Firestore",
                 "Base Firestore Database"
             ),
             TestModel(
                 12,
                 "Realtime Database",
                 "Base Realtime Database"
             ),
             TestModel(
                 13,
                 "Passive Validation",
                 "Passive Validation"
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