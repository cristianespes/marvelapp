package com.cristianespes.marvelapp.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.Character
import kotlinx.android.synthetic.main.view_hero.view.*
import kotlin.properties.Delegates

class HerosAdapter(private val listener: (Character) -> Unit) : RecyclerView.Adapter<HerosAdapter.ViewHolder>() {

    var heroes: List<Character> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].id == new[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_hero, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = heroes[position]
        holder.bind(hero)
        holder.itemView.setOnClickListener { listener(hero) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(hero: Character) {
            itemView.heroName.text = hero.name
            itemView.heroImage.loadUrl(hero.thumbnail?.path.plus(".${hero.thumbnail?.extension}"))
        }
    }
}