package com.hepeng;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

/**
 * @author hp.he
 * @date 2019/8/29 17:37
 */
public class TestParamPrintAgentMain {

    public static void main(String[] args) throws Exception {
        // args[0]传入的是某个jvm进程的pid
        String targetPid = "";
        List<VirtualMachineDescriptor> descriptorList = VirtualMachine.list();

        for (VirtualMachineDescriptor descriptor : descriptorList) {
            if (descriptor.displayName().contains("TestTimerConsumer")) {
                targetPid = descriptor.id();
                VirtualMachine vm = VirtualMachine.attach(targetPid);

                vm.loadAgent("D:/hplegend/codes/myAgent/jvmagent/agent_core/target/agent_core.jar",
                        "toagent");
            }
        }

    }

}
