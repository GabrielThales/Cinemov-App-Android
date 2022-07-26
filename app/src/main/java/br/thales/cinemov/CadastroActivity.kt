package br.thales.cinemov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import br.thales.cinemov.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    lateinit var binding: ActivityCadastroBinding
    lateinit var viewModel: CadastroActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(CadastroActivityViewModel::class.java)
        Log.i("Cadastro0","Passando pelo Activity Cadastro ")

    }
}