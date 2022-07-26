package br.thales.cinemov

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(fragmentActivity : FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    var arrayFragments = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return arrayFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        lateinit var fragment : Fragment
        when(position){
            0 -> fragment = arrayFragments[0]
            1 -> fragment = arrayFragments[1]
            2 -> fragment = arrayFragments[2]
            3 -> fragment = arrayFragments[3]
        }

        return fragment
    }

}