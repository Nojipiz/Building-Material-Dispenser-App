package com.ferropaz.materialDispenser.data.model

import android.text.Editable

class ConcreteManager {

    private val packageSize = arrayListOf(50.0, 0.08333333, 0.0833333)

    private var thicknessList = hashMapOf(
        0 to 0.05, //Easy Roof
        1 to 0.1,   //Full Plate
        2 to 0.1   //Collaborator Plate
    )
    private var strengthList = hashMapOf(
        0 to arrayListOf(210.0, 0.50, 1.0, 160.0), //1500psi
        1 to arrayListOf(260.0, 0.63, 0.84, 170.0), //2000psi
        2 to arrayListOf(300.0, 0.48, 0.96, 170.0), //2500psi
        3 to arrayListOf(350.0, 0.56, 0.84, 180.0), //3000psi
        4 to arrayListOf(420.0, 0.67, 0.67, 220.0), //3500psi
    )

    fun calculate(squareMeter: Editable, buildingType: Int, strengthType: Int): Array<List<Double>>{
        var squareMeters = 0.0
        if(squareMeter.toString().isNotEmpty() && squareMeter.toString().isNotBlank() )
            squareMeters = squareMeter.toString().toDouble()
        val thickness = thicknessList.get(buildingType)
        val cubicMeters = squareMeters*thickness!!
        return calculateAmounts(cubicMeters, strengthType)
    }

    private fun calculateAmounts(cubicMeters:Double, strengthType: Int) : Array<List<Double>>{
        val strengthValues = strengthList[strengthType]
        val quantities = strengthValues?.let {
            arrayListOf(
                strengthValues[0].times(cubicMeters),
                strengthValues[1].times(cubicMeters),
                strengthValues[2].times(cubicMeters),
                strengthValues[3].times(cubicMeters)
            )
        } ?: emptyList()
        val packageQuantities = strengthValues?.let {
            arrayListOf(
                it[0] / packageSize[0],
                it[1] / packageSize[1],
                it[2] / packageSize[2]
            )
        } ?: emptyList()

        return arrayOf(quantities, packageQuantities)
    }
}