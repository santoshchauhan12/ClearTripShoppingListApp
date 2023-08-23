package com.android.shoppingcart.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.shoppingcart.model.ShoppingItem
import com.android.shoppingcart.repository.ShoppingRepository

class ShoppingViewModel: ViewModel() {

    lateinit var shoppingListLiveData: MutableLiveData<List<ShoppingItem>>
    var shoppingRepository: ShoppingRepository?= null
    init {
        shoppingRepository = ShoppingRepository()
    }

    fun getShoppingList() {

        shoppingListLiveData.value = shoppingRepository?.getShoppingList()
    }
}