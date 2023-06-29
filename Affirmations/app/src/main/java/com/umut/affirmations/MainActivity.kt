package com.umut.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.affirmations.adapter.ItemAdapter
import com.umut.affirmations.data.DataSource
import com.umut.affirmations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myDataset = DataSource().loadAffirmations()
        val recyclerView = binding.recyclerView

        recyclerView.adapter = ItemAdapter(this, myDataset)
        recyclerView.setHasFixedSize(true)  // only for the performance improvement,
                                            // Use this setting if you know that changes in content do not change the layout size of the RecyclerView
    }
}