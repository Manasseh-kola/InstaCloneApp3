package com.example.instacloneapp3.presentation.mock_data

import com.example.instacloneapp3.R


class PostsRepo {
    private val posts = listOf(
        Posts(
            1, (100..500).random(),
            R.drawable.story1,"Roses are red \uD83C\uDF39 \uD83C\uDF39 \uD83C\uDF39",
            "drake",
            R.drawable.story1
        ),
        Posts(
            2, (100..500).random(),
            R.drawable.story2,"Violets are blue \uD83D\uDC99",
            "Lebron",
            R.drawable.story2
        ),
        Posts(
            3, (100..500).random(),
            R.drawable.story3,"You smell like poo \uD83D\uDCA9 \uD83D\uDCA9",
            "ChefCurry",
            R.drawable.story3
        ),
        Posts(
            4, (100..500).random(),
            R.drawable.story4,"But I Still love you ❤️",
            "Andrew_Wommack",
            R.drawable.story4
        ),
        Posts(
            5, (100..500).random(),
            R.drawable.story5,"Oranges are orange \uD83C\uDF4A",
            "Dr Kenyon",
            R.drawable.story5
        ),
        Posts(
            6, (100..500).random(),
            R.drawable.story6,"Courage rhymes with orange \uD83D\uDFE0",
            "Apostle Paul",
            R.drawable.story6
        ),
        Posts(
            7, (100..500).random(),
            R.drawable.story7,"Orange also rhymes with orange \uD83D\uDFE0 ",
            "Joseph Prince",
            R.drawable.story7
        ),
    )

    fun getPosts () : List<Posts> {
        return posts
    }
}