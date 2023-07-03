/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?, private val dataset: DataSource, private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val dogs = dataset.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {

        val textViewName: TextView = view!!.findViewById(R.id.text_dog_name)
        val textViewAge: TextView = view!!.findViewById(R.id.text_dog_age)
        val textViewHobbies: TextView = view!!.findViewById(R.id.text_dog_hobbies)
        val imageView: ImageView = view!!.findViewById(R.id.image_dog)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogCardViewHolder {

        val adapterLayout: View? = if (layout == 3) {
            LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }

        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = dataset.dogs.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val item = dogs[position]
        val resources = context?.resources
        holder.textViewName.text = item.name
        holder.textViewAge.text = resources!!.getString(R.string.dog_age, item.age)
        holder.textViewHobbies.text = resources!!.getString(R.string.dog_hobbies, item.hobbies)
        holder.imageView.setImageResource(item.imageResourceId)
        // TODO: Set the image resource for the current dog
        // TODO: Set the text for the current dog's name
        // TODO: Set the text for the current dog's age
        // TODO: Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}
