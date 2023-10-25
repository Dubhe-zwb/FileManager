package com.zwb.filemanager.utils.server

data class GeneratePhoto(
    val jobid: String,
    val status: String,
    val message: String,
    val version: String
)
data class JobStatus(
    val outputs: Outputs,
    val status: String
)

data class Outputs(
    val img1: String
)
data class Result(
    val image: String,
    val status: String
)