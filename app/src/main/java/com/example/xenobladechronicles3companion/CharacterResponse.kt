package com.example.xenobladechronicles3companion

import com.squareup.moshi.Json

class CharacterResponse {
    @Json(name="characters")
    lateinit var characterList : List<Character>
}