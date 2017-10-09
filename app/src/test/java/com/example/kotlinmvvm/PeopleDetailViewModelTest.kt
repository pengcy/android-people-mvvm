package com.example.kotlinmvvm

import android.view.View
import com.example.kotlinmvvm.data.FakeRandomUserGeneratorAPI
import com.example.kotlinmvvm.data.models.People
import com.example.kotlinmvvm.viewmodel.PeopleDetailViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Notes for Mac!!
 *
 *
 * If you are on a Mac, you will probably need to configure the
 * default JUnit test runner configuration in order to work around a bug where IntelliJ / Android
 * Studio
 * does not set the working directory to the module being tested. This can be accomplished by
 * editing
 * the run configurations, Defaults -> JUnit and changing the working directory value to
 * $MODULE_DIR$
 *
 *
 * You have to specify  sdk < 23 (Robolectric does not support API level 23.)
 *
 *
 * https://github.com/robolectric/robolectric/issues/1648
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class PeopleDetailViewModelTest {

    private var peopleDetailViewModel: PeopleDetailViewModel? = null
    private lateinit var people: People

    @Before
    fun setUpDetailViewModelTest() {
        people = FakeRandomUserGeneratorAPI.people
        peopleDetailViewModel = PeopleDetailViewModel(people)
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetURLPictureProfile() {
        assertEquals(people!!.picture!!.large, peopleDetailViewModel!!.pictureProfile)
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetUserName() {
        assertEquals(people!!.userName!!.userName, peopleDetailViewModel!!.userName)
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetCell() {
        assertEquals(people!!.cell, peopleDetailViewModel!!.cell)
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetMail() {
        assertEquals(people!!.mail, peopleDetailViewModel!!.email)
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetGender() {
        assertEquals(people!!.gender, peopleDetailViewModel!!.gender)
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetAddress() {
        val fakeAddress = people!!.location!!.street + " " + people!!.location!!.city + " " + people!!.location!!.state
        assertEquals(fakeAddress, peopleDetailViewModel!!.address)
    }

    @Test
    @Throws(Exception::class)
    fun givenTheEmailVisibilityVisible() {
        assertEquals(View.VISIBLE, peopleDetailViewModel!!.emailVisibility)
    }

    @Test
    @Throws(Exception::class)
    fun givenTheEmailVisibilityGone() {
        people!!.mail = null
        assertEquals(View.GONE, peopleDetailViewModel!!.emailVisibility)
    }
}