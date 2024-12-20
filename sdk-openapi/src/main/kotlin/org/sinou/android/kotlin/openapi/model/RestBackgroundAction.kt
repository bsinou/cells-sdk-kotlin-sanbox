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

import org.sinou.android.kotlin.openapi.model.JobsTaskStatus

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param jobUuid 
 * @param name 
 * @param canPause 
 * @param canStop 
 * @param endTime 
 * @param hasProgress 
 * @param label 
 * @param progress 
 * @param startTime 
 * @param status 
 * @param statusMessage 
 */


data class RestBackgroundAction (

    @Json(name = "JobUuid")
    val jobUuid: kotlin.String,

    @Json(name = "Name")
    val name: kotlin.String,

    @Json(name = "CanPause")
    val canPause: kotlin.Boolean? = null,

    @Json(name = "CanStop")
    val canStop: kotlin.Boolean? = null,

    @Json(name = "EndTime")
    val endTime: kotlin.Int? = null,

    @Json(name = "HasProgress")
    val hasProgress: kotlin.Boolean? = null,

    @Json(name = "Label")
    val label: kotlin.String? = null,

    @Json(name = "Progress")
    val progress: kotlin.Float? = null,

    @Json(name = "StartTime")
    val startTime: kotlin.Int? = null,

    @Json(name = "Status")
    val status: JobsTaskStatus? = JobsTaskStatus.Unknown,

    @Json(name = "StatusMessage")
    val statusMessage: kotlin.String? = null

) {


}

