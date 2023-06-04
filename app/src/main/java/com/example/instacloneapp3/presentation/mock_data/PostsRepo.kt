package com.example.instacloneapp3.presentation.mock_data

import com.example.instacloneapp3.R


class PostsRepo {
    private val posts = listOf(
        Posts(
            1, (100..500).random(),
            R.drawable.story1,"Roses are red",
            "drake",
            R.drawable.story1
        ),
        Posts(
            2, (100..500).random(),
            R.drawable.story2,"Violets are blue",
            "Lebron",
            R.drawable.story2
        ),
        Posts(
            3, (100..500).random(),
            R.drawable.story3,"You smell like poo",
            "ChefCurry",
            R.drawable.story3
        ),
        Posts(
            4, (100..500).random(),
            R.drawable.story4,"But I Still love you",
            "Andrew_Wommack",
            R.drawable.story4
        ),
        Posts(
            5, (100..500).random(),
            R.drawable.story5,"Oranges are orange",
            "Dr Kenyon",
            R.drawable.story5
        ),
        Posts(
            6, (100..500).random(),
            R.drawable.story6,"Courage rhymes with orange",
            "Apostle Paul",
            R.drawable.story6
        ),
        Posts(
            7, (100..500).random(),
            R.drawable.story7,"Orange also rhymes with orange",
            "Joseph Prince",
            R.drawable.story7
        ),
    )

    fun getPosts () : List<Posts> {
        return posts
    }
}