package com.example.scalim

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class FoodWrapper(
    val product: Food
)

data class Food(
    @SerializedName("_id")
    val code: String?,
    @SerializedName("product_name")
    val genericName: String?,
    val date: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeString(genericName)
        parcel.writeString(date)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }
}

data class ProductsWrapper(val success: Boolean, val products: List<Food>)