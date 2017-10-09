package com.example.kotlinmvvm.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kotlinmvvm.data.models.People
import com.example.kotlinmvvm.view.PeopleDetailActivity


class ItemPeopleViewModel(private var people: People, private val context: Context) : BaseObservable() {

    val fullName: String?
        get() {
            people.fullName = people.name!!.title + "." + people.name!!.firts + " " + people.name!!.last
            return people.fullName
        }

    val cell: String?
        get() = people.cell

    val mail: String?
        get() = people.mail

    val pictureProfile: String?
        get() = people.picture!!.medium

    fun onItemClick(view: View) {
        context.startActivity(PeopleDetailActivity.launchDetail(view.context, people))
    }

    fun setPeople(people: People) {
        this.people = people
        notifyChange()
    }

    companion object {

        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}