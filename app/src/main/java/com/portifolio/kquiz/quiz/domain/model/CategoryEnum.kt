package com.portifolio.kquiz.quiz.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class CategoryEnum(val categoryName: String) {

    GENERAL_KNOWLEDGE("General Knowledge"),
    ENTERTAINMENT_BOOKS("Books"),
    ENTERTAINMENT_FILM("Movies"),
    ENTERTAINMENT_MUSIC("Music"),
    ENTERTAINMENT_MUSICALS_THEATRES("Musicals & Theatres"),
    ENTERTAINMENT_TELEVISION("Television"),
    ENTERTAINMENT_VIDEO_GAMES("Video Games"),
    ENTERTAINMENT_BOARD_GAMES("Board Games"),
    SCIENCE_AND_NATURE("Science & Nature"),
    SCIENCE_COMPUTERS("Computers"),
    SCIENCE_MATHEMATICS("Mathematics"),
    MYTHOLOGY("Mythology"),
    SPORTS("Sports"),
    GEOGRAPHY("Geography"),
    HISTORY("History"),
    POLITICS("Politics"),
    ART("Art"),
    CELEBRITIES("Celebrities"),
    ANIMALS("Animals"),
    VEHICLES("Vehicles"),
    ENTERTAINMENT_COMICS("Comics"),
    SCIENCE_GADGETS("Gadgets"),
    ENTERTAINMENT_JAPANESE_ANIME_MANGA("Anime & Manga"),
    ENTERTAINMENT_CARTOON_ANIMATIONS("Cartoon & Animations")
}