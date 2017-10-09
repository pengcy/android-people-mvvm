package com.example.kotlinmvvm.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.kotlinmvvm.R
import com.example.kotlinmvvm.data.api.PeopleFactory
import com.example.kotlinmvvm.databinding.PeopleActivityBinding
import com.example.kotlinmvvm.viewmodel.PeopleViewModel
import java.util.*

class PeopleActivity : AppCompatActivity(), Observer {

    private var peopleActivityBinding: PeopleActivityBinding? = null
    private var peopleViewModel: PeopleViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        setSupportActionBar(peopleActivityBinding!!.toolbar)
        setupListPeopleView(peopleActivityBinding!!.listPeople)
        setupObserver(peopleViewModel)
    }

    private fun initDataBinding() {
        peopleActivityBinding = DataBindingUtil.setContentView(this, R.layout.people_activity)
        peopleViewModel = PeopleViewModel(this)
        peopleActivityBinding!!.setMainViewModel(peopleViewModel)
    }

    private fun setupListPeopleView(listPeople: RecyclerView) {
        val adapter = PeopleAdapter()
        listPeople.adapter = adapter
        listPeople.layoutManager = LinearLayoutManager(this)
    }

    fun setupObserver(observable: Observable?) {
        observable!!.addObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        peopleViewModel!!.reset()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_github) {
            startActivityActionView()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startActivityActionView() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PeopleFactory.PROJECT_URL)))
    }

    override fun update(observable: Observable, data: Any?) {
        if (observable is PeopleViewModel) {
            val peopleAdapter = peopleActivityBinding!!.listPeople.getAdapter() as PeopleAdapter
            val peopleViewModel = observable as PeopleViewModel
            peopleAdapter.setPeopleList(peopleViewModel.getPeopleList())
        }
    }
}