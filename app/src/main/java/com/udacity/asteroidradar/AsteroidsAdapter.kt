package com.udacity.asteroidradar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.AsteroidBinding


class AsteroidsAdapter(val asteroidClick: AsteroidClick) : ListAdapter<Asteroid, AsteroidsAdapter.AsteroidVH>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidVH {
        return AsteroidVH.from(parent)
    }

    override fun onBindViewHolder(holder: AsteroidVH, position: Int) {
        holder.bind(getItem(position),asteroidClick)
    }

    class AsteroidVH private constructor(val bind: AsteroidBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun bind(asteroid: Asteroid, asteroidClick: AsteroidClick) {
            bind.asteroid = asteroid
            bind.aClick = asteroidClick
            bind.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AsteroidVH {
                return AsteroidVH(AsteroidBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false))
            }
        }

    }

    object diff : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

    }
}
class AsteroidClick(val asteroid: (Asteroid) -> Unit) {

    fun onClick(asteroid: Asteroid) = asteroid(asteroid)
}
