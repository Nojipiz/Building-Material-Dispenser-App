package com.ferropaz.materialDispenser.data.model

import android.text.Editable
import kotlin.math.ceil

class FloorManager {

    private val constants = arrayListOf(1.0, 5.0, 0.071428)

    fun calculate(squareMeter: Editable):ArrayList<Int>{
        var squareMeters=0.0
        if(squareMeter.toString().isNotEmpty() && squareMeter.toString().isNotBlank() )
            squareMeters = squareMeter.toString().toDouble()
        val profiles = squareMeters.times(constants[0])
        val blocks = squareMeters.times(constants[1])
        val mesh = squareMeters.times(constants[2])
        return arrayListOf(ceil(profiles).toInt(), ceil(blocks).toInt() , ceil(mesh).toInt() )
    }
}