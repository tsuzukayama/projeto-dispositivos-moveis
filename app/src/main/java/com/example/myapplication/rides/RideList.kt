package com.example.myapplication.rides

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.R

class RideList :AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ride_list)
        populateRides()
    }

    private fun populateRides() {
        listView = findViewById(R.id.list_rides)

        listView.adapter = RideAdapter()

        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(App.context, RideDetailsPassenger::class.java)
            startActivity(intent)
        }
        listView.setMultiChoiceModeListener(object: AbsListView.MultiChoiceModeListener {
            override fun onItemCheckedStateChanged(mode: ActionMode, pos: Int, id: Long, checked: Boolean) {
                mode.title = listView.checkedItemCount.toString() + " rides"
            }

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {

                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return true
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode) {
            }
        })
    }

    override fun onResume() {
        super.onResume()

        (listView.adapter as BaseAdapter).notifyDataSetChanged()
    }
}