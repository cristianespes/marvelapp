package com.cristianespes.marvelapp.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.database.Hero
import com.cristianespes.marvelapp.ui.common.basicDiffUtil
import com.cristianespes.marvelapp.ui.common.inflate
import com.cristianespes.marvelapp.ui.common.loadUrl
import kotlinx.android.synthetic.main.view_hero.view.*

class HeroesAdapter(private val listener: (Hero) -> Unit) : RecyclerView.Adapter<HeroesAdapter.ViewHolder>() {

    var heroes: List<Hero> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )


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
        fun bind(hero: Hero) {
            itemView.heroName.text = hero.name
            itemView.heroImage.loadUrl(hero.thumbnail ?: "")
        }
    }
}