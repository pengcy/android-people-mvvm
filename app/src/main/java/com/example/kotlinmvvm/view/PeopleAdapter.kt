package com.example.kotlinmvvm.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kotlinmvvm.R
import com.example.kotlinmvvm.data.models.People
import com.example.kotlinmvvm.databinding.ItemPeopleBinding
import com.example.kotlinmvvm.viewmodel.ItemPeopleViewModel

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder>() {

    private var peopleList: List<People>? = null

    init {
        this.peopleList = emptyList<People>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapterViewHolder {
        val itemPeopleBinding = DataBindingUtil.inflate<ItemPeopleBinding>(LayoutInflater.from(parent.context), R.layout.item_people,
                parent, false)
        return PeopleAdapterViewHolder(itemPeopleBinding)
    }

    override fun onBindViewHolder(holder: PeopleAdapterViewHolder, position: Int) {
        holder.bindPeople(peopleList!![position])
    }

    override fun getItemCount(): Int {
        return peopleList!!.size
    }

    fun setPeopleList(peopleList: List<People>) {
        this.peopleList = peopleList
        notifyDataSetChanged()
    }

    class PeopleAdapterViewHolder(internal var mItemPeopleBinding: ItemPeopleBinding) : RecyclerView.ViewHolder(mItemPeopleBinding.itemPeople) {

        internal fun bindPeople(people: People) {
            if (mItemPeopleBinding.getPeopleViewModel() == null) {
                mItemPeopleBinding.setPeopleViewModel(
                        ItemPeopleViewModel(people, itemView.context))
            } else {
                mItemPeopleBinding.getPeopleViewModel()!!.setPeople(people)
            }
        }
    }
}