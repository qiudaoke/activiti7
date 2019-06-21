package com.shareniu.v2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.api.process.model.payloads.StartProcessPayload;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shareniu.v2.utils.RestMessgae;

/**
 * @author 分享牛
 */
@RestController
public class StartController {


    @Autowired
    private ProcessRuntime processRuntime;


    //http://127.0.0.1:8080/start?user=1&processKey=leave
    @GetMapping(path = "start")
    public RestMessgae start(@RequestParam("user") String userKey,
                             @RequestParam("processKey") String processKey) {
        HashMap<String, Object> variables=new HashMap<>(1);
        variables.put("userKey", userKey);

        RestMessgae restMessgae = new RestMessgae();
        org.activiti.api.process.model.ProcessInstance instance = null;
        try {

            String processDefinitionId="leave:2:32bd7d18-8da5-11e9-a715-7ab66d766be2";
            String processDefinitionKey="leave";
            String name="";
            String businessKey="123";
            StartProcessPayload startProcessPayload=new StartProcessPayload(processDefinitionId,processDefinitionKey,name,businessKey,variables);
             instance = processRuntime.start(startProcessPayload);
        } catch (Exception e) {
            restMessgae = RestMessgae.fail("启动失败", e.getMessage());
        }

        if (instance != null) {
            Map<String, String> result = new HashMap<>(2);
            // 流程实例ID
            result.put("processID", instance.getId());

            // 流程定义ID
            result.put("processDefinitionKey", instance.getProcessDefinitionId());
            restMessgae = RestMessgae.success("启动成功", result);
        }
        return restMessgae;
    }


//    @PostMapping(path = "searchByKey")
//    public RestMessgae searchProcessInstance(@RequestParam("processKey") String processDefinitionKey){
//        RestMessgae restMessgae = new RestMessgae();
//        List<ProcessInstance> runningList = new ArrayList<>();
//        try {
//            ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
//            runningList = processInstanceQuery.processDefinitionKey(processDefinitionKey).list();
//        } catch (Exception e) {
//            restMessgae = RestMessgae.fail("查询失败", e.getMessage());
//            e.printStackTrace();
//        }
//
//        int size = runningList.size();
//        if (size > 0) {
//            List<Map<String, String>> resultList = new ArrayList<>();
//            for (int i = 0; i < size; i++) {
//                ProcessInstance pi = runningList.get(i);
//                Map<String, String> resultMap = new HashMap<>(2);
//                // 流程实例ID
//                resultMap.put("processID", pi.getId());
//                // 流程定义ID
//                resultMap.put("processDefinitionKey", pi.getProcessDefinitionId());
//                resultList.add(resultMap);
//            }
//            restMessgae = RestMessgae.success("查询成功", resultList);
//        }
//        return restMessgae;
//    }
//
//
//    @PostMapping(path = "searchByID")
//    public RestMessgae searchByID(@RequestParam("processID") String processDefinitionID){
//        RestMessgae restMessgae  = new RestMessgae();
//        ProcessInstance pi = null;
//        try {
//            pi = runtimeService.createProcessInstanceQuery()
//                    .processInstanceId(processDefinitionID)
//                    .singleResult();
//        } catch (Exception e) {
//            restMessgae = RestMessgae.fail("查询失败", e.getMessage());
//            e.printStackTrace();
//        }
//
//        if (pi != null) {
//            Map<String, String> resultMap = new HashMap<>(2);
//            // 流程实例ID
//            resultMap.put("processID", pi.getId());
//            // 流程定义ID
//            resultMap.put("processDefinitionKey", pi.getProcessDefinitionId());
//            restMessgae = RestMessgae.success("查询成功", resultMap);
//        }
//        return restMessgae;
//    }
//
//    @PostMapping(path = "deleteProcessInstanceByKey")
//    public RestMessgae deleteProcessInstanceByKey(@RequestParam("processKey") String processDefinitionKey){
//        RestMessgae restMessgae = new RestMessgae();
//        List<ProcessInstance> runningList = new ArrayList<>();
//        try {
//            ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
//            runningList = processInstanceQuery.processDefinitionKey(processDefinitionKey).list();
//        } catch (Exception e) {
//            restMessgae = RestMessgae.fail("删除失败", e.getMessage());
//            e.printStackTrace();
//        }
//
//        int size = runningList.size();
//        if (size > 0) {
//            List<Map<String, String>> resultList = new ArrayList<>();
//            for (int i = 0; i < size; i++) {
//                ProcessInstance pi = runningList.get(i);
//                runtimeService.deleteProcessInstance(pi.getId(),"删除");
//            }
//            restMessgae = RestMessgae.success("删除成功", resultList);
//        }
//        return  restMessgae;
//    }
//
//    @PostMapping(path = "deleteProcessInstanceByID")
//    public RestMessgae deleteProcessInstanceByID(@RequestParam("processID") String processDefinitionID){
//        RestMessgae restMessgae = new RestMessgae();
//        try {
//            runtimeService.deleteProcessInstance(processDefinitionID,"删除" + processDefinitionID);
//        } catch (Exception e) {
//            restMessgae = RestMessgae.fail("删除失败", e.getMessage());
//            return  restMessgae;
//        }
//        restMessgae = RestMessgae.success("删除成功", "");
//        return  restMessgae;
//    }
}
