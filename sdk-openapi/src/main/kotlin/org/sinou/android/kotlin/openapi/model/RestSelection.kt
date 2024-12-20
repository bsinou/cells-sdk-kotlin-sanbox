/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.sinou.android.kotlin.openapi.model

import org.sinou.android.kotlin.openapi.model.RestNode

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Request to create a selection from a list of nodes.
 *
 * @param nodes 
 * @param uuid 
 */


data class RestSelection (

    @Json(name = "Nodes")
    val nodes: kotlin.collections.List<RestNode>,

    @Json(name = "Uuid")
    val uuid: kotlin.String? = null

) {


}

