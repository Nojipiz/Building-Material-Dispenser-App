package com.ferropaz.materialDispenser.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferropaz.materialDispenser.data.model.FloorManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EasyFloorViewModel: ViewModel() {

    val quantities = MutableLiveData<ArrayList<Int>>()
    val floorManager = FloorManager();

    init {
        quantities.value = arrayListOf(0,0,0)
    }

    fun calculate(squareMeter: Editable){
        CoroutineScope(Dispatchers.Default).launch {
            val list = floorManager.calculate(squareMeter)
            quantities.postValue(list)
        }
    }
    
}
