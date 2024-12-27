package com.obsndy.prospectfarmersapp.state

import com.obsndy.prospectfarmersapp.models.Farmer
import java.util.UUID

class FarmerState(
    var id: String = "",
    var name: String = "",
    var phoneNumber: String = "",
    var location: String = "",
) {
    fun toFarmer(): Farmer {
        return Farmer(
            id = id.ifEmpty { UUID.randomUUID().toString() },
            name = name,
            phoneNumber = phoneNumber,
            location = location
        )
    }

    fun fromFarmer(farmer: Farmer) {
        id = farmer.id
        name = farmer.name
        phoneNumber = farmer.phoneNumber
        location = farmer.location
    }

}