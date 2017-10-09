package com.example.kotlinmvvm

import android.view.View
import com.example.kotlinmvvm.data.FakeRandomUserGeneratorAPI
import com.example.kotlinmvvm.data.api.PeopleService
import com.example.kotlinmvvm.data.models.People
import com.example.kotlinmvvm.databinding.PeopleActivityBinding
import com.example.kotlinmvvm.viewmodel.PeopleViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
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
@Config(constants = BuildConfig::class, sdk = intArrayOf(21), manifest = Config.NONE)
class PeopleViewModelTest {

    @Mock private val peopleService: PeopleService? = null

    private var peopleViewModel: PeopleViewModel? = null

    @Mock private val peopleActivityBinding: PeopleActivityBinding? = null

    @Before
    fun setUpMainViewModelTest() {
        // inject the mocks
        MockitoAnnotations.initMocks(this)

        // Mock the PeopleService so we don't call the Random User Generator API (we are simulating only a call to the api)
        // and all observables will now run on the same thread
        val peopleApplication = RuntimeEnvironment.application as PeopleApplication
        peopleApplication.peopleService = peopleService
        peopleApplication.setScheduler(Schedulers.trampoline())

        peopleViewModel = PeopleViewModel(peopleApplication)
    }

    @Test
    @Throws(Exception::class)
    fun simulateGivenTheUserCallListFromApi() {
        val peoples = FakeRandomUserGeneratorAPI.peopleList
        doReturn(Observable.just<List<People>>(peoples)).`when`(peopleService)!!.fetchPeople(URL_TEST)
    }

    @Test
    @Throws(Exception::class)
    fun ensureTheViewsAreInitializedCorrectly() {
        peopleViewModel!!.initializeViews()
        assertEquals(View.GONE, peopleViewModel!!.peopleLabel.get())
        assertEquals(View.GONE, peopleViewModel!!.peopleRecycler.get())
        assertEquals(View.VISIBLE, peopleViewModel!!.peopleProgress.get())
    }

    companion object {

        private val URL_TEST = "http://api.randomuser.me/?results=10&nat=en"
    }
}