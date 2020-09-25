package cn.enn.bigdata.dataanalysis.service.impl;

import cn.enn.bigdata.dataanalysis.config.StatemachineConfigurer;
import cn.enn.bigdata.dataanalysis.entity.TurnstileEvents;
import cn.enn.bigdata.dataanalysis.entity.TurnstileStates;
import cn.enn.bigdata.dataanalysis.service.StatemachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineAccessor;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//public class StatemachineImpl implements CommandLineRunner, StatemachineService {
public class StatemachineImpl implements StatemachineService {

    @Autowired
    private StateMachine<TurnstileStates, TurnstileEvents> stateMachine;
    @Autowired
    private StatemachineConfigurer statemachineConfigurer;


    //    @Override
    public void run(String... strings) throws Exception {
        stateMachine.start();
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        stateMachine.stop();
    }

    @Override
    public void runService() {
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void runDeal() throws Exception {
        stateMachine = statemachineConfigurer.build(TurnstileStates.UNPAID);
        State<TurnstileStates, TurnstileEvents> state = stateMachine.getState();
        boolean sentEvent = stateMachine.sendEvent(TurnstileEvents.PAY);
        state = stateMachine.getState();
        sentEvent = stateMachine.sendEvent(TurnstileEvents.RECEIVE);
        state = stateMachine.getState();
        StateMachineAccessor<TurnstileStates, TurnstileEvents> smAccessor = stateMachine.getStateMachineAccessor();
        List<StateMachineAccess<TurnstileStates, TurnstileEvents>> smRegions = smAccessor.withAllRegions();
        for (StateMachineAccess<TurnstileStates, TurnstileEvents> region : smRegions) {
            region.resetStateMachine(new DefaultStateMachineContext<>(TurnstileStates.DONE, null, null, null));
        }

        stateMachine = statemachineConfigurer.build(TurnstileStates.WAITING_FOR_RECEIVE);
        sentEvent = stateMachine.sendEvent(TurnstileEvents.CANCEL);

//        TcpBridgeServer tcpBridgeServer;
    }
}

