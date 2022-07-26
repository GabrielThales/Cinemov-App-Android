package br.thales.cinemov

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.thales.cinemov.Infra.Interfaces.AvaliacaoEventListener
import br.thales.cinemov.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    companion object{
        var intentsCount = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        createNotificationChannel()
        Log.i("Cadastro-1","Pta tudo errado")
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        if (intent != null){
            viewModel.listFav = intent.getBooleanExtra("listFav", false)
        }

        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        val intent = Intent(this, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val timeNow = System.currentTimeMillis()
        val tenSeconds = 1000 * 10

        val firingCal = Calendar.getInstance()
        val currentCal = Calendar.getInstance()

        firingCal.set(Calendar.HOUR, 20)
        firingCal.set(Calendar.MINUTE, 0)
        firingCal.set(Calendar.SECOND, 0)

        var intendedTime = firingCal.timeInMillis
        val currentTime = currentCal.timeInMillis

        if (intendedTime >= currentTime){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_DAY, pendingIntent)
        }else{
            // set from next day
            // you might consider using calendar.add() for adding one day to the current day
            firingCal.add(Calendar.DAY_OF_MONTH, 1);
            intendedTime = firingCal.getTimeInMillis();

            alarmManager.setRepeating(AlarmManager.RTC, intendedTime, AlarmManager.INTERVAL_DAY, pendingIntent);
        }

//        alarmManager.set(AlarmManager.RTC_WAKEUP,
//        timeNow + tenSeconds, pendingIntent)


        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager2

        val arrayFragments = arrayListOf<Fragment>(RecomendacoesFragment.newInstance(),/*FeedFragment.newInstance(),*/ PesquisaFragment.newInstance(), UsuarioFragment.newInstance())
        val adapter = TabAdapter(this)
        adapter.arrayFragments = arrayFragments


        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, possition ->
            viewPager2.setCurrentItem(tab.position, true)
            if (possition == 0) tab.setText("Filmes")
            //if (possition == 1) tab.setText("Avaliações")
            if (possition == 1) tab.setText("Pesquisa")
            if (possition == 2) tab.setText("Usuário")

        }.attach()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = MenuInflater(this)
        inflate.inflate(R.menu.right_menu, menu)

        return true
    }

    override fun onClick(v: View?) {

    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "cinemovReminderChannel"
            val description = "Canal para Cinemov Reminder"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val chanel = NotificationChannel("cinemovNotify", name, importance)
            chanel.description = description

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(chanel)

        }
    }

}