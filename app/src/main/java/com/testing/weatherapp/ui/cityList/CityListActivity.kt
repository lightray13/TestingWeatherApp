package com.testing.weatherapp.ui.cityList

import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.testing.weatherapp.R
import com.testing.weatherapp.api.adapters.CityListAdapter
import com.testing.weatherapp.api.adapters.OnItemClickCallback
import com.testing.weatherapp.databinding.ActivityCityListBinding
import com.testing.weatherapp.ui.main.MainActivity
import com.testing.weatherapp.util.LocationRequests
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_city_list.*

@AndroidEntryPoint
class CityListActivity : AppCompatActivity(), OnItemClickCallback {

    private lateinit var binding: ActivityCityListBinding

    private val viewModel: CityListViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var cityListAdapter = CityListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_list)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initializeViews()
        observeViewModel()
    }

    private fun initializeViews() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        cityListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cityListAdapter
        }

        onBackPressedIconImageView.setOnClickListener {
            returnToMainActivity()
        }

        cityListSearchIconImageView.setOnClickListener {
            val text = enterCityEditText.text.toString()
            if (text.isNotEmpty()) {
                viewModel.insertCityEntityToList(text)
                enterCityEditText.setText("")
                hideKeyboard()
            } else {
                showMessage("Ввведите название города")
            }
        }

        currentLocationTextView.setOnClickListener {
            getUserLocation()
        }
    }

    private fun observeViewModel() {
        viewModel.cityListData.observe(this) {
            cityListAdapter.updateList(it)
        }
        viewModel.toastSuccess.observe(this) {
            it?.let { showMessage(it) }
        }
        viewModel.toastError.observe(this) {
            it?.let { showMessage(it) }
        }
    }

    override fun onItemClick(lat: String, lon: String) {
        viewModel.setCoordinates(lat, lon)
        returnToMainActivity()
    }

    override fun onDeleteIconClick(name: String) {
        viewModel.deleteCityEntityFromList(name)
    }

    private fun returnToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getUserLocation() {
        if (LocationRequests.checkPermission(this)) {
            getLocation()
        } else {
            LocationRequests.requestPermission(this)
        }
    }

    private fun getLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                val latitude =  location?.latitude
                val longitude = location?.longitude
                viewModel.setCoordinates(latitude.toString(), longitude.toString())
                returnToMainActivity()
            }
    }

    private fun hideKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}