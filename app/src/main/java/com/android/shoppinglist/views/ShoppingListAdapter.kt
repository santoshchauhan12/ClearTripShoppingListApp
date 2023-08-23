package com.android.shoppinglist.views

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.shoppingcart.model.ShoppingItem
import com.android.shoppinglist.views.viewholder.ShoppingListViewHolder

class ShoppingListAdapter(shoppingList: List<ShoppingItem>): RecyclerView.Adapter<ShoppingListViewHolder>() {

    var shopList: List<ShoppingItem>?= null

    init {
        shopList = shoppingList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {

        return shopList?.size!!
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {


    }
}