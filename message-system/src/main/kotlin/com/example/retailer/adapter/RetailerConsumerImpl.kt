package com.example.retailer.adapter

import com.example.retailer.api.distributor.OrderInfo
import com.example.retailer.service.OrderService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired

class RetailerConsumerImpl : RetailerConsumer {
    @Autowired
    private lateinit var orderService: OrderService

    @RabbitListener(queues = ["retailer"])
    override fun receiveUpdate(message: String) {
        val mapper = ObjectMapper()
        val info = mapper.readValue(message, OrderInfo::class.java)
        println("hello beautiful " + info.orderId)
        orderService.updateOrderInfo(info)
    }
}