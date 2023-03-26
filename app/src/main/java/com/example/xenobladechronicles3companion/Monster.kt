package com.example.xenobladechronicles3companion

data class Monster (val name : String,
                    val urlName : String,
                    val level : Int,
                    val area : String,
                    val location : String,
                    val unique : Boolean,
                    val defeated : Boolean){
    /*
    XENOBLADE WIKI API FOR EVERY MONSTER IN THE GAME
    https://xenoblade.fandom.com/api.php?action=parse&page=List_of_Enemies_in_Xenoblade_Chronicles_3&format=json

    XENOBLADE WIKI API FOR EVERY STANDARD SIDE QUEST IN THE GAME
    https://xenoblade.fandom.com/api.php?action=parse&page=Quest_(XC3)&format=json

    XENOBLADE WIKI API FOR EVERY HERO QUEST IN THE GAME
    https://xenoblade.fandom.com/api.php?action=parse&page=Hero_Quest&format=json

    These are notes for future API implementation so every single monster/quest/etc. doesn't need to be put into the app manually. Will save immensely on storage.
     */
}