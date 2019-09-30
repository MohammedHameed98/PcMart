package com.example.pcmart.Utilites

import android.os.Parcel
import android.os.Parcelable
class ItemDataIntent constructor (var name : String? ,var price : String? ,var image : String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemDataIntent> {
        override fun createFromParcel(parcel: Parcel): ItemDataIntent {
            return ItemDataIntent(parcel)
        }

        override fun newArray(size: Int): Array<ItemDataIntent?> {
            return arrayOfNulls(size)
        }
    }
}