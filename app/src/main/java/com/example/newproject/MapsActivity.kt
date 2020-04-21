package com.example.newproject

import android.os.Bundle
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var customStateAdapter: CustomStateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.activityMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        customStateAdapter = CustomStateAdapter(
            supportFragmentManager, lifecycle, arrayListOf(
                CollectItemsProduct{hideView()},
                CollectItemsProduct{hideView()},
                CollectItemsProduct{hideView()}))

        val viewPager2 = findViewById<ViewPager2>(R.id.activityViewPager2)
        viewPager2?.adapter = customStateAdapter

        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        dotsIndicator.setViewPager2(viewPager2)

        val button = findViewById<ImageButton>(R.id.collectItemsSecondMoreInfo)
        button.setOnClickListener {
            showView()
        }
    }

    fun hideView() {
        val layout: View = findViewById(R.id.layout)
        val parent: ViewGroup = findViewById(R.id.parent)

        val transition: Transition = Slide(Gravity.BOTTOM)
        transition.duration = 600
        transition.addTarget(R.id.layout)

        TransitionManager.beginDelayedTransition(parent, transition)
        layout.visibility = View.GONE
    }

    fun showView() {
        val layout: View = findViewById(R.id.layout)
        val parent: ViewGroup = findViewById(R.id.parent)

        val transition: Transition = Slide(Gravity.BOTTOM)
        transition.duration = 300
        transition.addTarget(R.id.layout)

        TransitionManager.beginDelayedTransition(parent, transition)
        layout.visibility = View.VISIBLE
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
