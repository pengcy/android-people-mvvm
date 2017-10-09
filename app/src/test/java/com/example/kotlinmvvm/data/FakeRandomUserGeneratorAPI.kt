package com.example.kotlinmvvm.data

import com.example.kotlinmvvm.data.models.Location
import com.example.kotlinmvvm.data.models.Login
import com.example.kotlinmvvm.data.models.People
import com.example.kotlinmvvm.data.models.Picture
import java.util.ArrayList

object FakeRandomUserGeneratorAPI {

    private val PEOPLE_CELL_TEST = "0177-6155420"
    private val PEOPLE_MAIL_TEST = "theodor.kaufmann@example.com"
    private val PEOPLE_PICTURE_TEST = "http://api.randomuser.me/portraits/women/39.jpg"
    private val PEOPLE_TITLE_TEST = "ms"
    private val PEOPLE_FIRST_TEST = "constance"
    private val PEOPLE_LAST_TEST = "fowler"
    private val PEOPLE_STREET_TEST = "9193 brock rd"
    private val PEOPLE_CITY_TEST = "flatrock"
    private val PEOPLE_STATE_TEST = "prince edward island"
    private val PEOPLE_USER_NAME_TEST = "organicgoose874"

    val peopleList: List<People>
        get() {
            val peoples = ArrayList<People>()
            for (i in 0..9) {
                peoples.add(people)
            }
            return peoples
        }

    val people: People
        get() {
            val people = People()
            people.picture = Picture()
            people.location = Location()
            people.userName = Login()
            people.userName!!.userName = PEOPLE_USER_NAME_TEST
            people.fullName = "$PEOPLE_TITLE_TEST.$PEOPLE_FIRST_TEST $PEOPLE_LAST_TEST"
            people.cell = PEOPLE_CELL_TEST
            people.mail = PEOPLE_MAIL_TEST
            people.picture!!.large = PEOPLE_PICTURE_TEST
            people.location!!.street = PEOPLE_STREET_TEST
            people.location!!.city = PEOPLE_CITY_TEST
            people.location!!.state = PEOPLE_STATE_TEST
            return people
        }
}