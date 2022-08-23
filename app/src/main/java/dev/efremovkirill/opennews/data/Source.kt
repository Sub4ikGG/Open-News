package dev.efremovkirill.opennews.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Source(
    val id: String?,
    val name: String
): Serializable