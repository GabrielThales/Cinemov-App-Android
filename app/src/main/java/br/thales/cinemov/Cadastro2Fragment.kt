package br.thales.cinemov

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Cadastro2Fragment : Fragment() {

    companion object {
        fun newInstance() = Cadastro2Fragment()
    }

    private lateinit var viewModel: Cadastro2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.cadastro2_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Cadastro2ViewModel::class.java)
        // TODO: Use the ViewModel


//        val texto = view?.findViewById<TextView>(R.id.texto)
//
//        texto?.text = arguments?.get("teste").toString()

    }

}