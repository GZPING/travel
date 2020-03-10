/*
package com.gd.travel.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author : GD
 * @date :2020/2/21 : 16:11
 *//*

@Configuration
public class RabbitMqConfig {

    */
/*************************************************互动数据配置开始**********************************************//*

    */
/**
     * 互动 ROUTE KEY
     *//*

    public static final String WEIBO_INTERACTIVE_ROUT_KEY = "com.wenwo.cloud.business.cms.weibo_interactive_rout_key";

    */
/**
     * 互动 EXCHANGE
     *//*

    public static final String WEIBO_INTERACTIVE_EXCHANGE = "com.wenwo.cloud.business.cms.weibo_interactive_exchange";

    */
/**
     * 互动 QUEUE
     *//*

    public static final String WEIBO_INTERACTIVE_QUEUE = "com.wenwo.cloud.business.cms.weibo_interactive_queue";
    */
/**
     * 互动 死信队列 key
     *//*

    public static final String WEIBO_INTERACTIVE_DELAY_KEY = "com.wenwo.cloud.business.cms.weibo_interactive_delay_key";
    */
/**
     * 互动 死信队列exchange
     *//*

    public static final String WEIBO_INTERACTIVE_DELAY_EXCHANGE = "com.wenwo.cloud.business.cms.weibo_interactive_delay_exchange";
    */
/**
     * 互动 死信队列queue
     *//*

    public static final String WEIBO_INTERACTIVE_DELAY_QUEUE = "com.wenwo.cloud.business.cms.weibo_interactive_delay_queue";

    */
/**
     * interactive的调用插入queue
     *
     * @return Exchange
     *//*

    @Bean
    public Exchange interactiveExchange() {
        return new DirectExchange(WEIBO_INTERACTIVE_EXCHANGE);
    }

    */
/**
     * interactive的调用插入queue
     *
     * @return Queue
     *//*

    @Bean
    public Queue interactiveQueue() {
        return new Queue(WEIBO_INTERACTIVE_QUEUE);
    }

    */
/**
     * Binding
     *
     * @param interactiveExchange
     * @param interactiveQueue
     * @return
     *//*

    @Bean
    public Binding interactiveBinding(Exchange interactiveExchange, Queue interactiveQueue) {
        return BindingBuilder.bind(interactiveQueue).to(interactiveExchange).with(WEIBO_INTERACTIVE_ROUT_KEY).noargs();
    }

    */
/**
     *延迟交换
     * @return
     *//*

    @Bean
    public Exchange interactiveDelayExchange() {
        return new DirectExchange(WEIBO_INTERACTIVE_DELAY_EXCHANGE);
    }

    */
/**
     * 死信队列
     * @return Exchange
     *//*

    @Bean
    public Queue interactiveDelayQueue() {
        Map<String, Object> arguments=new HashMap<>();
        arguments.put("x-dead-letter-exchange", WEIBO_INTERACTIVE_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", WEIBO_INTERACTIVE_ROUT_KEY);
        return new Queue(WEIBO_INTERACTIVE_DELAY_QUEUE,true,false, false, arguments);
    }

    */
/**
     * 延迟消费队列绑定
     * @param interactiveDelayExchange
     * @param interactiveDelayQueue
     * @return
     *//*

    @Bean
    public Binding interactiveDelayBinding(Exchange interactiveDelayExchange, Queue interactiveDelayQueue) {
        return BindingBuilder.bind(interactiveDelayQueue).to(interactiveDelayExchange)
                .with(WEIBO_INTERACTIVE_DELAY_KEY).noargs();
    }
    */
/*************************************************互动数据配置结束**********************************************//*


    */
/*************************************************微博投放配置开始**********************************************//*


    */
/**
     * 微博投放key
     *//*

    public static final String LAUNCH_WEIBO_DELAY_KEY = "com.wenwo.cloud.business.cms.launch_weibo_delay_key";
    */
/**
     * 微博投放exchange
     *//*

    public static final String LAUNCH_WEIBO_DELAY_EXCHANGE = "com.wenwo.cloud.business.cms.launch_weibo_delay_exchange";
    */
/**
     * 微博投放queue
     *//*

    public static final String LAUNCH_WEIBO_DELAY_QUEUE = "com.wenwo.cloud.business.cms.launch_weibo_delay_queue";

    */
/**
     * 微博投放key
     *//*

    public static final String LAUNCH_WEIBO_COMMON_KEY = "com.wenwo.cloud.business.cms.launch_weibo_common_key";

    */
/**
     * common exchange
     *//*

    public static final String LAUNCH_WEIBO_COMMON_EXCHANGE = "com.wenwo.cloud.business.cms.launch_weibo_common_exchange";
    */
/**
     * common queue
     *//*

    public static final String LAUNCH_WEIBO_COMMON_QUEUE = "com.wenwo.cloud.business.cms.launch_weibo_common_queue";


    */
/**
     *普通交换
     * @return
     *//*

    @Bean
    public Exchange commonExchange() {
        return new DirectExchange(LAUNCH_WEIBO_COMMON_EXCHANGE);
    }

    */
/**
     *延迟交换
     * @return
     *//*

    @Bean
    public Exchange delayExchange() {
        return new DirectExchange(LAUNCH_WEIBO_DELAY_EXCHANGE);
    }



    */
/**
     * 普通队列
     * @return Exchange
     *//*

    @Bean
    public Queue commonQueue() {
        return new Queue(LAUNCH_WEIBO_COMMON_QUEUE);
    }

    */
/**
     * 死信队列
     * @return Exchange
     *//*

    @Bean
    public Queue delayQueue() {
        Map<String, Object> arguments=new HashMap<>();
        arguments.put("x-dead-letter-exchange", LAUNCH_WEIBO_COMMON_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", LAUNCH_WEIBO_COMMON_KEY);
        return new Queue(LAUNCH_WEIBO_DELAY_QUEUE,true,false, false, arguments);
    }

    */
/**
     *死信队列
     * @param commonExchange
     * @param commonQueue
     * @return
     *//*

    @Bean
    public Binding commonBind(Exchange commonExchange, Queue commonQueue) {
        return BindingBuilder.bind(commonQueue).to(commonExchange).with(LAUNCH_WEIBO_COMMON_KEY).noargs();
    }

    */
/**
     * 延迟消费队列绑定
     * @param delayExchange
     * @param delayQueue
     * @return
     *//*

    @Bean
    public Binding launchWeiboDelayBinding(Exchange delayExchange, Queue delayQueue) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(LAUNCH_WEIBO_DELAY_KEY).noargs();
    }
    */
/*************************************************微博投放配置结束**********************************************//*


    */
/*************************************************通知医端投放结果配置开始**********************************************//*

    */
/**
     * ROUTE KEY
     *//*

    public static final String LAUNCH_WEIBO_RESULT_DOCTOR_KEY = "com.wenwo.cloud.business.cms.launch_weibo_result_doctor_key";

    */
/**
     * EXCHANGE
     *//*

    public static final String LAUNCH_WEIBO_RESULT_DOCTOR_EXCHANGE = "com.wenwo.cloud.business.cms.launch_weibo_result_doctor_exchange";

    */
/**
     * QUEUE
     *//*

    public static final String LAUNCH_WEIBO_RESULT_DOCTOR_QUEUE = "com.wenwo.cloud.business.cms.launch_weibo_result_doctor_queue";

    */
/**
     * interactive的调用插入queue
     *
     * @return Exchange
     *//*

    @Bean
    public Exchange launchResultDoctorExchange() {
        return new DirectExchange(LAUNCH_WEIBO_RESULT_DOCTOR_EXCHANGE);
    }

    */
/**
     * interactive的调用插入queue
     *
     * @return Queue
     *//*

    @Bean
    public Queue launchResultDoctorQueue() {
        return new Queue(LAUNCH_WEIBO_RESULT_DOCTOR_QUEUE);
    }

    */
/**
     * Binding
     *
     * @param launchResultDoctorExchange
     * @param launchResultDoctorQueue
     * @return
     *//*

    @Bean
    public Binding launchResultDoctorBinding(Exchange launchResultDoctorExchange, Queue launchResultDoctorQueue) {
        return BindingBuilder.bind(launchResultDoctorQueue).to(launchResultDoctorExchange)
                .with(LAUNCH_WEIBO_RESULT_DOCTOR_KEY).noargs();
    }
    */
/*************************************************通知医端投放结果配置结束**********************************************//*


    */
/*************************************************套餐入库内容状态通知配置开始**********************************************//*

    */
/**
     * ROUTE KEY
     *//*

    public static final String PK_COLLECTOIN_STATE_CHANGE_KEY = "business_pk_meal_content_state_change_queue_key";

    */
/**
     * EXCHANGE
     *//*

    public static final String PK_COLLECTOIN_STATE_CHANGE_EXCHANGE = "business_pk_meal_content_state_change_exchange";

    */
/**
     * QUEUE
     *//*

    public static final String PK_COLLECTOIN_STATE_CHANGE_QUEUE = "business_pk_meal_content_state_change_queue";

    */
/**
     * interactive的调用插入queue
     *
     * @return Exchange
     *//*

    @Bean
    public Exchange pkStateExchange() {
        return new DirectExchange(PK_COLLECTOIN_STATE_CHANGE_EXCHANGE);
    }

    */
/**
     * interactive的调用插入queue
     *
     * @return Queue
     *//*

    @Bean
    public Queue pkStateQueue() {
        return new Queue(PK_COLLECTOIN_STATE_CHANGE_QUEUE);
    }

    */
/**
     * Binding
     *
     * @param pkStateExchange
     * @param pkStateQueue
     * @return
     *//*

    @Bean
    public Binding pkStateBinding(Exchange pkStateExchange, Queue pkStateQueue) {
        return BindingBuilder.bind(pkStateQueue).to(pkStateExchange)
                .with(PK_COLLECTOIN_STATE_CHANGE_QUEUE).noargs();
    }
    */
/*************************************************套餐入库内容状态通知配置结束**********************************************//*


    */
/*************************************************医端入库内容状态通知配置开始**********************************************//*

    */
/**
     * ROUTE KEY
     *//*

    public static final String DOCTOR_COLLECTOIN_STATE_CHANGE_KEY = "com.wenwo.cloud.business.cms.doctor_content_state_change_queue_key";

    */
/**
     * EXCHANGE
     *//*

    public static final String DOCTOR_COLLECTOIN_STATE_CHANGE_EXCHANGE = "com.wenwo.cloud.business.cms.doctor_content_state_change_exchange";

    */
/**
     * QUEUE
     *//*

    public static final String DOCTOR_COLLECTOIN_STATE_CHANGE_QUEUE = "com.wenwo.cloud.business.cms.doctor_content_state_change_queue";

    */
/**
     * queue
     *
     * @return Exchange
     *//*

    @Bean
    public Exchange doctorStateExchange() {
        return new DirectExchange(DOCTOR_COLLECTOIN_STATE_CHANGE_EXCHANGE);
    }

    */
/**
     * queue
     *
     * @return Queue
     *//*

    @Bean
    public Queue doctorStateQueue() {
        return new Queue(DOCTOR_COLLECTOIN_STATE_CHANGE_QUEUE);
    }

    */
/**
     * Binding
     *
     * @param doctorStateExchange
     * @param doctorStateQueue
     * @return
     *//*

    @Bean
    public Binding doctorStateBinding(Exchange doctorStateExchange, Queue doctorStateQueue) {
        return BindingBuilder.bind(doctorStateQueue).to(doctorStateExchange)
                .with(DOCTOR_COLLECTOIN_STATE_CHANGE_QUEUE).noargs();
    }
    */
/*************************************************医端入库内容状态通知配置结束**********************************************//*


}
*/
