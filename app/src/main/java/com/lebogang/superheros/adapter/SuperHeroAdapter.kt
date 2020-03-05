package com.lebogang.superheros.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lebogang.superheros.database.SuperHeroEntity
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.lebogang.superheros.ui.MainActivity
import com.lebogang.superheros.R


class SuperHeroAdapter(mainActivity: MainActivity) : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {


    // Class variables for the List that holds task data and the Context
    private var mTaskEntries: List<SuperHeroEntity>? = null
    private var mContext: Context? = null

    /**
     * Constructor for the TaskAdapter that initializes the Context.
     *
     * @param context  the current Context
     * @param listener the ItemClickListener
     */
    fun SuperHeroAdapter(context: Context){
        mContext = context
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the adapter_view_item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the adapter_view_item changes in the data set unless the adapter_view_item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data adapter_view_item inside
     * this method and should not keep a copy of it. If you need the position of an adapter_view_item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * adapter_view_item at the given position in the data set.
     * @param position The position of the adapter_view_item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        // Determine the values of the wanted data
        val taskEntry = mTaskEntries!!.get(position)
        val description = taskEntry.name
        val priority = taskEntry.url

        //Set values
        holder.taskDescriptionView.setText(description)
        holder.updatedAtView.setText(priority)
        Glide.with(holder.imageView)
                .load(priority)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    /**
     * Returns the number of items to display.
     */
    override fun getItemCount(): Int {
        return mTaskEntries?.size ?: 0
    }

    fun getTasks(): List<SuperHeroEntity>? {
        return mTaskEntries
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    fun setTasks(taskEntries: List<SuperHeroEntity>) {
        mTaskEntries = taskEntries
        notifyDataSetChanged()
    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an adapter_view_item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        // Inflate the task_layout to a view
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_view_item, parent, false)

        return SuperHeroViewHolder(view)
    }

    // Inner class for creating ViewHolders
    inner class SuperHeroViewHolder
    /**
     * Constructor for the TaskViewHolders.
     *
     * @param itemView The view inflated in onCreateViewHolder
     */
    (itemView: View) : RecyclerView.ViewHolder(itemView){

        // Class variables for the task description and priority TextViews
        var taskDescriptionView: TextView
        var updatedAtView: TextView
        var priorityView: TextView
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.image)
            taskDescriptionView = itemView.findViewById(R.id.tv_id)
            updatedAtView = itemView.findViewById(R.id.tv_name)
            priorityView = itemView.findViewById(R.id.tv_url)

        }
    }
}


