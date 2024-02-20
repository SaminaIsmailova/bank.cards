package com.example.jetpack

import androidx.lifecycle.ViewModel
import com.example.jetpack.model.CardsListModel
import com.example.jetpack.ui.Registration.Companion.DATABASENAME
import com.google.firebase.Firebase
import com.google.firebase.database.database

class ViewModel : ViewModel() {
    private val list = ArrayList<CardsListModel>()
    fun addData(): ArrayList<CardsListModel> {
        list.add(CardsListModel(R.drawable.cards_platinum, "Насиза Эмиль", 10000))
        list.add(CardsListModel(R.drawable.classic_demir, "Шеховцов Дмитрий", 352467))
        list.add(CardsListModel(R.drawable.elcard, "Сидоренко Екатерина", 532667))
        list.add(CardsListModel(R.drawable.elcard_kyrgyz, "Юшкова Екатерина", 452637))
        list.add(CardsListModel(R.drawable.gold_kyrgyz, "Алымкулова Майрам", 432532))
        list.add(CardsListModel(R.drawable.unionpay_diamond, "Келдибекова Даткайым", 643643460))
        list.add(CardsListModel(R.drawable.unionpay_gold, "Акылбеков Нурсултан", 643634))
        list.add(CardsListModel(R.drawable.visa_classic_card, "Решетов Андрей", 346343))
        list.add(CardsListModel(R.drawable.visa_gold, "Александр Резнов", 634634))
        list.add(CardsListModel(R.drawable.visa_infinity, "Виктор Баринов", 6346363))
        return list
    }

    fun getData() {

    }
}
