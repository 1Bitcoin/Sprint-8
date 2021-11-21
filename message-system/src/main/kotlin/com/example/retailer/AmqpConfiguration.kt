package com.example.retailer

import com.example.retailer.adapter.DistributorPublisher
import com.example.retailer.adapter.DistributorPublisherImpl
import com.example.retailer.adapter.RetailerConsumer
import com.example.retailer.adapter.RetailerConsumerImpl
import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AmqpConfiguration {

    @Bean
    fun distributorExchanger(): TopicExchange {
        return TopicExchange("distributor_exchange", true, false)
    }

    @Bean
    fun retailerQueue(): Queue {
        return Queue("retailer", false, false, true)
    }

    @Bean
    fun bindingRetailer(topic: TopicExchange, autoDeleteRetailerQueue: Queue): Binding {
        return BindingBuilder.bind(autoDeleteRetailerQueue)
            .to(topic)
            .with("retailer.1Bitcoin.#")
    }

    @Bean
    fun consumer(): RetailerConsumer {
        return RetailerConsumerImpl()
    }

    @Bean
    fun publisher(): DistributorPublisher {
        return DistributorPublisherImpl()
    }
}