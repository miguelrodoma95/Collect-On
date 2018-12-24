package com.miwoapp.collecton.Model

import android.arch.lifecycle.ViewModel

/**
 * Created by Miguel on 12/24/2018.
 */
class Collectible : ViewModel() {

    var name: String? = null
    var theme: String? = null
    var collection: String? = null
    var series:String? = null
    var image_url: String? = null

    //price mxn/usd
}