package com.example.kotlinmvvm

import android.content.Context
import android.content.Intent
import android.databinding.Observable
import android.view.View
import com.example.kotlinmvvm.data.models.Name
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import com.example.kotlinmvvm.data.models.People
import com.example.kotlinmvvm.data.models.Picture
import com.example.kotlinmvvm.viewmodel.ItemPeopleViewModel
import org.junit.Assert.assertEquals
import org.mockito.Matchers.anyInt
import org.mockito.Mockito.mock
import org.mockito.Mockito.any
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner

/**
 * Notes for Mac!!
 *
 *
 * If you are on a Mac, you will probably need to configure the
 * default JUnit test runner configuration in order to work around a bug where IntelliJ / Android
 * Studio
 * does not set the working directory to the module being tested. This can be accomplished by
 * editing the run configurations,
 * Run -> Edit Configurations... -> JUnit and changing the working directory value to
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
class ItemPeopleViewModelTest {

    private lateinit var peopleApplication: Context

    @Before
    fun setUpItemPeopleModelTest() {
        peopleApplication = RuntimeEnvironment.application
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetPeopleCell() {
        val people = People()
        people.cell = PEOPLE_CELL_TEST
        val itemPeopleViewModel = ItemPeopleViewModel(people, peopleApplication)
        assertEquals(people.cell, itemPeopleViewModel.cell)
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetPeopleMail() {
        val people = People()
        people.mail = PEOPLE_MAIL_TEST
        val itemPeopleViewModel = ItemPeopleViewModel(people, peopleApplication)
        assertEquals(people.mail, itemPeopleViewModel.mail)
    }

    @Ignore
    @Throws(Exception::class)
    fun shouldGetPeoplePicture() {
        val people = People()
        people.picture = Mockito.mock<Picture>(Picture::class.java)
        people.picture!!.large = PEOPLE_PICTURE_TEST
        val itemPeopleViewModel = ItemPeopleViewModel(people, peopleApplication)
        assertEquals(people.picture!!.large, itemPeopleViewModel.pictureProfile)
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetPeopleFullName() {
        val people = People()
        people.name = Name()
        people.name!!.title = PEOPLE_TITLE_TEST
        people.name!!.firts = PEOPLE_FIRST_TEST
        people.name!!.last = PEOPLE_LAST_TEST
        people.fullName = people.name!!.title + "." + people.name!!.firts + " " + people.name!!.last
        val itemPeopleViewModel = ItemPeopleViewModel(people, peopleApplication)
        assertEquals(people.fullName, itemPeopleViewModel.fullName)
    }

//    @Test
//    @Throws(Exception::class)
//    fun shouldStartPeopleDetailActivityOnItemClick() {
//        val people = People()
//        val mockContext = mock(Context::class.java)
//        val itemPeopleViewModel = ItemPeopleViewModel(people, mockContext)
//        itemPeopleViewModel.onItemClick(View(peopleApplication))
//        verify(mockContext).startActivity(any(Intent::class.java))
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun shouldNotifyPropertyChangeWhenSetPeople() {
//        val people = People()
//        val itemPeopleViewModel = ItemPeopleViewModel(people, peopleApplication)
//        val mockCallback = mock<Observable.OnPropertyChangedCallback>(Observable.OnPropertyChangedCallback::class.java)
//        itemPeopleViewModel.addOnPropertyChangedCallback(mockCallback)
//        itemPeopleViewModel.setPeople(people)
//        verify<Observable.OnPropertyChangedCallback>(mockCallback).onPropertyChanged(any<Observable>(Observable::class.java), anyInt())
//    }

    companion object {

        private val PEOPLE_CELL_TEST = "0177-6155420"
        private val PEOPLE_MAIL_TEST = "theodor.kaufmann@example.com"
        private val PEOPLE_PICTURE_TEST = "http://api.randomuser.me/portraits/women/39.jpg"
        private val PEOPLE_TITLE_TEST = "ms"
        private val PEOPLE_FIRST_TEST = "constance"
        private val PEOPLE_LAST_TEST = "fowler"
    }
}