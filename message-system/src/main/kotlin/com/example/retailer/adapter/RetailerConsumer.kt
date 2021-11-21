package com.example.retailer.adapter


interface RetailerConsumer {
    fun receiveUpdate(message: String)
}