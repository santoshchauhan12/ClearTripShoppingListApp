package com.android.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.shoppingcart.ViewModel.ShoppingViewModel
import com.android.shoppingcart.model.ShoppingItem
import com.android.shoppinglist.views.ShoppingListAdapter
import kotlinx.android.synthetic.main.activity_main.rv_shopping_list
import kotlinx.android.synthetic.main.view_shopping_item.*


class MainActivity : AppCompatActivity() {
    lateinit var shoppingViewModel: ShoppingViewModel

    var shoppingList = listOf<ShoppingItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModels()
        initUi()
        getShoppingList()
    }

    private fun initUi() {
        var shoppingListAdapter = ShoppingListAdapter(shoppingList)
        rv_shopping_list.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_shopping_list.adapter = shoppingListAdapter
    }

    private fun getShoppingList() {
        shoppingViewModel.shoppingListLiveData.observe(this) {

            shoppingList = it
        }
    }

    private fun initViewModels() {
        shoppingViewModel = ShoppingViewModel()
    }
}