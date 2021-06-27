package com.ferropaz.materialDispenser.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferropaz.materialDispenser.data.model.FloorManager

class EasyFloorViewModel: ViewModel() {

    val quantities = MutableLiveData<ArrayList<Int>>()
    val floorManager = FloorManager();

    init {
        quantities.value = arrayListOf(0,0,0)
    }

    fun calculate(squareMeter: Editable){
        val list = floorManager.calculate(squareMeter)
        quantities.value = list
    }
}