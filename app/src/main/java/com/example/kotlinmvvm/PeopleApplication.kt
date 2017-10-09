package com.example.kotlinmvvm

import android.app.Application
import android.content.Context
import com.example.kotlinmvvm.data.api.PeopleFactory
import com.example.kotlinmvvm.data.api.PeopleService
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class PeopleApplication : Application() {

    var peopleService: PeopleService? = null
        get() {
            if (field == null) {
                this.peopleService = PeopleFactory.create()
            }

            return field
        }
    private var scheduler: Scheduler? = null

    fun subscribeScheduler(): Scheduler? {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }

        return scheduler
    }

    fun setScheduler(scheduler: Scheduler) {
        this.scheduler = scheduler
    }

    companion object {

        private operator fun get(context: Context): PeopleApplication {
            return context.applicationContext as PeopleApplication
        }

        fun create(context: Context): PeopleApplication {
            return PeopleApplication[context]
        }
    }
}