package com.ferropaz.materialDispenser.ui.view.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.ferropaz.materialDispenser.R
import androidx.fragment.app.viewModels
import com.ferropaz.materialDispenser.databinding.FragmentConcreteBinding
import com.ferropaz.materialDispenser.ui.viewmodel.ConcreteViewModel
import com.ferropaz.materialDispenser.utilities.Utilities
import java.util.Calendar

class ConcreteFragment : Fragment() {

    private var _binding: FragmentConcreteBinding? = null
    private val binding get() = _binding!!
    
    companion object{
        private const val WEB_PAGE = "https://nojipiz.github.io/"
    }

    private val concreteViewModel : ConcreteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentConcreteBinding.inflate(inflater, container, false)
        val view = binding.root
        componentsInit()
        setObservers()
        return view
    }

    private fun setObservers(){
        concreteViewModel.quantities.observe(viewLifecycleOwner) {
            setQuantities(it)
        }
        concreteViewModel.packageQuantities.observe(viewLifecycleOwner) {
            setPackageQuantities(it)
        }
    }

    private fun setQuantities(list:List<Double>){
        binding.cementAmount.text = Utilities.formatterInteger(list[0])
        binding.sandAmount.text = Utilities.formatterDecimal(list[1])
        binding.gravelAmount.text = Utilities.formatterDecimal(list[2])
        binding.waterAmount.text = Utilities.formatterDecimal(list[3])
    }

    private fun setPackageQuantities(list:List<Double>){
        binding.cementPackage.text = Utilities.formatterInteger(list[0])
        binding.sandPackage.text = Utilities.formatterInteger(list[1])
        binding.gravelPackage.text = Utilities.formatterInteger(list[2])
    }

    private fun componentsInit(){
        ArrayAdapter.createFromResource(this.requireContext(), R.array.building_types, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.buildingTypeSpinner.adapter = adapter
            binding.buildingTypeSpinner.onItemSelectedListener = SpinnerActivity(this)
        }

        ArrayAdapter.createFromResource(this.requireContext(), R.array.strength_types, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.strengthSpinner.adapter = adapter
            binding.strengthSpinner.onItemSelectedListener = SpinnerActivity(this)
        }

        binding.metersField.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = calculate()
        })

        binding.nojipizBrand.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(WEB_PAGE))
            startActivity(browserIntent)
        }
        binding.strengthSpinner.setSelection(3)

        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()
        binding.ferropazBrand.text = getString(R.string.brand_text, currentYear)
    }

    fun calculate(){
        concreteViewModel.calculate(binding.metersField.text, binding.buildingTypeSpinner.selectedItemPosition, binding.strengthSpinner.selectedItemPosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class SpinnerActivity(fragment: ConcreteFragment) : Activity(), AdapterView.OnItemSelectedListener {

    private val fragment = fragment

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        fragment.calculate()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}