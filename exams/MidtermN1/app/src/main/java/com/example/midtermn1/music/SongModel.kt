package com.example.midtermn1.music

import android.os.Parcel
import android.os.Parcelable

class SongModel {
    var data = ArrayList<Data>()

    class Data {
        var band: String = ""

        var songs = ArrayList<Song>()
    }

    class Song() : Parcelable {
        var title: String = ""

        constructor(parcel: Parcel) : this() {
            title = parcel.readString()!!
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(title)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Song> {
            override fun createFromParcel(parcel: Parcel): Song {
                return Song(parcel)
            }

            override fun newArray(size: Int): Array<Song?> {
                return arrayOfNulls(size)
            }
        }
    }
}
