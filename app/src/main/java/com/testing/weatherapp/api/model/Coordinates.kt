package com.testing.weatherapp.api.model
import com.google.gson.annotations.SerializedName

data class Coordinates(
    val response: Response?
)

data class Response(
    @SerializedName("GeoObjectCollection") val geoObjectCollection: GeoObjectCollection
)

data class GeoObjectCollection(
    val featureMember: List<FeatureMember>?
)

data class FeatureMember(
    @SerializedName("GeoObject") val geoObject: GeoObject?
)

data class GeoObject(
    val metaDataProperty: MetaDataProperty?,
    @SerializedName("Point") val point: Point?
)

data class Point(
    val pos: String?
)

data class MetaDataProperty(
    @SerializedName("GeocoderMetaData") val geocoderMetaData: GeocoderMetaData?
)

data class GeocoderMetaData(
    val text: String?
)