package com.ferropaz.materialDispenser.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ferropaz.materialDispenser.R
import com.ferropaz.materialDispenser.databinding.ActivityMainBinding
import com.ferropaz.materialDispenser.ui.view.fragments.CatalogFragment
import com.ferropaz.materialDispenser.ui.view.fragments.ConcreteFragment
import com.ferropaz.materialDispenser.ui.view.fragments.EasyFloorFragment
import com.ferropaz.materialDispenser.ui.view.fragments.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tabsInit()
    }

    private fun tabsInit(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ConcreteFragment(), getString(R.string.tab_text_1))
        adapter.addFragment(EasyFloorFragment(), getString(R.string.tab_text_2))
        adapter.addFragment(CatalogFragment(), getString(R.string.tab_text_3))
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.concrete)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.measuring_tape)
        binding.tabs.getTabAt(2)!!.setIcon(R.drawable.libro)
    }
}