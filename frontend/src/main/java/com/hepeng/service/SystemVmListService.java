package com.hepeng.service;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/30 14:23
 */
@Service
public class SystemVmListService {


    public void listAllVm() {
        List<VirtualMachineDescriptor> descriptorList = VirtualMachine.list();
        for (VirtualMachineDescriptor descriptor : descriptorList) {
            System.out.println(descriptor.displayName() + "--" + descriptor.id());
        }
    }
}
