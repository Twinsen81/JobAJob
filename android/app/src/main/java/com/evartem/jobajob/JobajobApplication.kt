package com.evartem.jobajob

import android.app.Application
import com.evartem.jobajob.di.AppComponent

class JobajobApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponent.init(this)
    }
}