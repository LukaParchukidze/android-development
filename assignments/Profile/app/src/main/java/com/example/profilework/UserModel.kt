package com.example.profilework

import android.os.Parcel
import android.os.Parcelable

class UserModel(var parcel_firstname:String, var parcel_lastname:String, var parcel_email:String, var parcel_birthYear:Int, var parcel_gender:String) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(parcel_firstname)
        parcel.writeString(parcel_lastname)
        parcel.writeString(parcel_email)
        parcel.writeInt(parcel_birthYear)
        parcel.writeString(parcel_gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }

}