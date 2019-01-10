package com.hepeng.service;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/30 14:22
 */
@Service
public class SystemVmAttachService{


    public void attach(String vmId) {

        VirtualMachine vm = null;
        try {
            vm = VirtualMachine.attach(vmId);
            vm.loadAgent("D:/githubProject/jvmagent/agent_core/target/agent_core.jar",
                    "toagent");
            vm.detach();
        } catch (AttachNotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AgentInitializationException e) {
            e.printStackTrace();
        } catch (AgentLoadException e) {
            e.printStackTrace();
        }


    }

}
