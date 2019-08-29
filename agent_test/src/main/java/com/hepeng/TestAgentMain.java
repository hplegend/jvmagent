package com.hepeng;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/25 16:17
 */
public class TestAgentMain {
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
