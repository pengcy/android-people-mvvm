package com.example.kotlinmvvm.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.example.kotlinmvvm.PeopleApplication
import com.example.kotlinmvvm.R
import com.example.kotlinmvvm.data.api.PeopleFactory
import com.example.kotlinmvvm.data.api.PeopleResponse
import com.example.kotlinmvvm.data.models.People
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import java.util.*

class PeopleViewModel(private var context: Context) : Observable() {

    var peopleProgress: ObservableInt
    var peopleRecycler: ObservableInt
    var peopleLabel: ObservableInt
    var messageLabel: ObservableField<String>

    private val peopleList: MutableList<People>
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    init {
        this.peopleList = ArrayList<People>()
        peopleProgress = ObservableInt(View.GONE)
        peopleRecycler = ObservableInt(View.GONE)
        peopleLabel = ObservableInt(View.VISIBLE)
        messageLabel = ObservableField(context.getString(R.string.default_loading_people))
    }

    fun onClickFabLoad(view: View) {
        initializeViews()
        fetchPeopleList()
    }

    //It is "public" to show an example of test
    fun initializeViews() {
        peopleLabel.set(View.GONE)
        peopleRecycler.set(View.GONE)
        peopleProgress.set(View.VISIBLE)
    }

    private fun fetchPeopleList() {

        val peopleApplication = PeopleApplication.create(context)
        val peopleService = peopleApplication.peopleService

        val disposable = peopleService!!.fetchPeople(PeopleFactory.RANDOM_USER_URL)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<PeopleResponse> { peopleResponse ->
                    changePeopleDataSet(peopleResponse.peopleList)
                    peopleProgress.set(View.GONE)
                    peopleLabel.set(View.GONE)
                    peopleRecycler.set(View.VISIBLE)
                }, Consumer<Throwable> { throwable ->
                    throwable.printStackTrace()
                    messageLabel.set(context.getString(R.string.error_loading_people))
                    peopleProgress.set(View.GONE)
                    peopleLabel.set(View.VISIBLE)
                    peopleRecycler.set(View.GONE)
                })

        compositeDisposable!!.add(disposable)
    }

    private fun changePeopleDataSet(peoples: List<People>?) {
        if(peoples == null) return

        peopleList.addAll(peoples)
        setChanged()
        notifyObservers()
    }

    fun getPeopleList(): List<People> {
        return peopleList
    }

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null
//        context = null
    }
}
