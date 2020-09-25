package cn.wp.bigdata.dataanalysis.config;

import cn.wp.bigdata.dataanalysis.entity.TurnstileEvents;
import cn.wp.bigdata.dataanalysis.entity.TurnstileStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

@Slf4j
@Configuration
@EnableStateMachine
public class StatemachineConfigurer extends EnumStateMachineConfigurerAdapter<TurnstileStates, TurnstileEvents> implements InitializingBean {

    @Autowired
    BeanFactory beanFactory;
    private TurnstileStates state;


    @Override
    public void configure(StateMachineStateConfigurer<TurnstileStates, TurnstileEvents> states)
            throws Exception {
        states
                .withStates()
// 初始状态：Locked
                .initial(TurnstileStates.Locked)
                .states(EnumSet.allOf(TurnstileStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TurnstileStates, TurnstileEvents> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(TurnstileStates.Unlocked).target(TurnstileStates.Locked)
                .event(TurnstileEvents.COIN).action(customerPassAndLock())
                .and()
                .withExternal()
                .source(TurnstileStates.Locked).target(TurnstileStates.Unlocked)
                .event(TurnstileEvents.PUSH).action(turnstileUnlock())
        ;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<TurnstileStates, TurnstileEvents> config)
            throws Exception {
        config.withConfiguration()
                .machineId("turnstileStateMachine")
        ;
    }

    public Action<TurnstileStates, TurnstileEvents> turnstileUnlock() {
        return context -> System.out.println("解锁旋转门，以便游客能够通过");
    }

    public Action<TurnstileStates, TurnstileEvents> customerPassAndLock() {
        return context -> System.out.println("当游客通过，锁定旋转门");
    }

    public Action<TurnstileStates, TurnstileEvents> payAction() {
        return context -> System.out.println("支付订单，等待收货吧！");
    }

    public Action<TurnstileStates, TurnstileEvents> receiveAction() {
        return context -> System.out.println("已经收货了，订单结束！");
    }

    public Action<TurnstileStates, TurnstileEvents> cancelAction() {
        return context -> System.out.println("取消了订单，等待退款吧！");
    }

    public Action<TurnstileStates, TurnstileEvents> cancelAction2() {
        return context -> System.out.println("取消了订单，还得提醒你，你得自己联系客服退款");
    }


    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {

    }


    public StateMachine<TurnstileStates, TurnstileEvents> build(TurnstileStates states) throws Exception {
        log.info("Builder build.");
        StateMachineBuilder.Builder<TurnstileStates, TurnstileEvents> builder = StateMachineBuilder.builder();

        //配置配置信息
        builder.configureConfiguration()
                .withConfiguration().machineId("myState").autoStartup(true).beanFactory(beanFactory);
        //配置状态信息
        builder.configureStates().withStates().initial(states).states(EnumSet.allOf(TurnstileStates.class));
        //配置状态转换信息
        builder.configureTransitions()
                .withExternal()
                .source(TurnstileStates.Unlocked).target(TurnstileStates.Locked)
                .event(TurnstileEvents.COIN).action(customerPassAndLock())
                .and()
                .withExternal()
                .source(TurnstileStates.Locked).target(TurnstileStates.Unlocked)
                .event(TurnstileEvents.PUSH).action(turnstileUnlock())
                .and()
                .withExternal()
                .source(TurnstileStates.UNPAID).target(TurnstileStates.WAITING_FOR_RECEIVE)
                .event(TurnstileEvents.PAY).action(payAction())
                .and()
                .withExternal()
                .source(TurnstileStates.WAITING_FOR_RECEIVE).target(TurnstileStates.DONE)
                .event(TurnstileEvents.RECEIVE).action(receiveAction())
                .and()
                .withExternal()
                .source(TurnstileStates.WAITING_FOR_RECEIVE).target(TurnstileStates.CANCEL_BUYER)
                .event(TurnstileEvents.CANCEL).action(cancelAction()).action(cancelAction2());
        StateMachine stateMachine = builder.build();
        stateMachine.addStateListener(listener());
        return stateMachine;
    }

    public StateMachineListener<TurnstileStates, TurnstileEvents> listener() {
        return new StateMachineListenerAdapter<TurnstileStates, TurnstileEvents>() {
            @Override
            public void transition(Transition<TurnstileStates, TurnstileEvents> transition) {

                if (transition.getSource().getId() == TurnstileStates.UNPAID
                        && transition.getTarget().getId() == TurnstileStates.CANCEL_BUYER) {
                    log.info("订单创建，用户已取消");
                    return;
                }

                if (transition.getSource().getId() == TurnstileStates.UNPAID
                        && transition.getTarget().getId() == TurnstileStates.WAITING_FOR_RECEIVE) {
                    log.info("订单创建，已经支付了，等待收货吧~~~");
                    return;
                }

                if (transition.getTarget().getId() == TurnstileStates.UNPAID) {
                    log.info("订单创建，待支付");
                    return;
                }

                if (transition.getSource().getId() == TurnstileStates.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == TurnstileStates.DONE) {
                    log.info("用户已收货，订单完成");
                    return;
                }

                if (transition.getSource().getId() == TurnstileStates.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == TurnstileStates.CANCEL_BUYER) {
                    log.info("用户已付款，但是取消了，到取消状态吧~~~");
                    return;
                }


            }
        };
    }
}