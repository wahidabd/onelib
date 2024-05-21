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
                "Recycler Adapter",
                "Recycler Adapter",
            ),
            TestModel(
                3,
                "View Pager 2",
                "Base View Pager 2",
            ),
            TestModel(
                4,
                "Api with Coroutine",
                "Base implement api with rx",
            ),
            TestModel(
                5,
                "Notification",
                "Base notification"
            ),
            TestModel(
                6,
                "Rx Local Database",
                "Base Local Database With Rx"
            ),
            TestModel(
                7,
                "Multi State View",
                "Multi State View"
            ),
            TestModel(
                8,
                "Rx Paging",
                "Base RxPagingAdapter"
            ),
            TestModel(
                9,
                "Firebase Firestore Database",
                "Base Firestore Database"
            ),
            TestModel(
                10,
                "Firebase Realtime Database",
                "Base Realtime Database"
            ),
            TestModel(
                11,
                "Passive Validation",
                "Passive Validation"
            ),
            TestModel(
                12,
                "Reactive Validation",
                "Reactive Validation"
            ),
            TestModel(
                13,
                "Selectable Adapter",
                "Selectable Adapter"
            ),
            TestModel(
                14,
                "Bottom Sheet",
                "Bottom Sheet"
            ),
            TestModel(
                15,
                "Filterable Adapter",
                "Filterable Adapter"
            ),
            TestModel(
                16,
                "Dialog",
                "Dialog"
            ),
            TestModel(
                17,
                "Firebase Auth",
                "Firebase Auth"
            ),
            TestModel(
                18,
                "Firebase Storage",
                "Firebase Storage"
            ),

        ).sortedBy { it.title }

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