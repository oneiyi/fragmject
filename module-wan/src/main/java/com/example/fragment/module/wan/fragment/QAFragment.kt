package com.example.fragment.module.wan.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragment.library.base.model.BaseViewModel
import com.example.fragment.library.common.fragment.RouterFragment
import com.example.fragment.module.wan.databinding.QaFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class QAFragment : RouterFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): QAFragment {
            return QAFragment()
        }
    }

    private var _binding: QaFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun initView() {
        //TabLayout与ViewPager2
        val tabName = arrayOf("问答", "广场")
        binding.viewpager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return tabName.size
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> QAQuizFragment.newInstance()
                    1 -> QASquareFragment.newInstance()
                    else -> throw ArrayIndexOutOfBoundsException("length=${itemCount}; index=$position")
                }
            }
        }
        TabLayoutMediator(binding.tabLayout, binding.viewpager2) { tab, position ->
            tab.text = tabName[position]
        }.attach()
    }

    override fun initViewModel(): BaseViewModel? {
        return null
    }

}