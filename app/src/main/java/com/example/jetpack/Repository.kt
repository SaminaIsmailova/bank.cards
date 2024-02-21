package com.example.jetpack

import com.example.jetpack.model.CardsListModel

class Repository {
    private var list=ArrayList<CardsListModel>()
    fun getData():List<CardsListModel>{
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
}