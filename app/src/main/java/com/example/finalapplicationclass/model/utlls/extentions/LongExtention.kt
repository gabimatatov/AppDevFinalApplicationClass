package com.example.finalapplicationclass.model.utlls.extentions

import java.util.Date
import com.google.firebase.Timestamp

val Long.toFirebaseTimestamp: Timestamp
    get() {
        return Timestamp(Date())
    }