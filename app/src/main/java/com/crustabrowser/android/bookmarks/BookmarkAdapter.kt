package com.crustabrowser.android.bookmarks

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crustabrowser.android.Database
import com.crustabrowser.android.R
import kotlinx.android.synthetic.main.view_sites_adapter.view.*
import kotlinx.android.synthetic.main.view_tab_adapter.view.*
import kotlinx.android.synthetic.main.view_tab_adapter.view.address
import kotlinx.android.synthetic.main.view_tab_adapter.view.title

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    var bookmarks = mutableListOf<Bookmark>()

    init {
        AsyncTask.execute {
            val items = Database.db?.bookmarkDao()?.getAll()
            if (items != null) for (item in items) {
                val index = bookmarks.size
                bookmarks.add(item)
                notifyItemInserted(index)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_sites_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = bookmarks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = bookmarks[position]
        holder.view.title.text = item.title
        holder.view.address.text = item.address
        holder.view.extra_info.text = item.info
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}