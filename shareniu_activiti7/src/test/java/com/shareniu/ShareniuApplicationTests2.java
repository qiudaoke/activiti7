package com.shareniu;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.payloads.StartProcessPayload;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 分享牛
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareniuApplicationTests2 {




    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;


    @Test
    public void deploy() {

        //创建一个部署对象
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name("分享牛请假流程");
        Deployment deployment = null;
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("processes/leave.bpmn20.xml");
            deployment = repositoryService.createDeployment()
                .name("请假流程")
                //指定zip格式的文件完成部署
                .addInputStream("shareniu.bpmn",in)
                .deploy();//完成部署



    }
    @WithMockUser(username="admin",roles={"ACTIVITI_USER"})
    @Test
    public void start() {

        String processDefinitionId="leave:2:32bd7d18-8da5-11e9-a715-7ab66d766be2";
        String processDefinitionKey="leave";
        String name="";
        String businessKey="123";
        Map<String, Object> variables=new HashMap<>();
        StartProcessPayload startProcessPayload=new StartProcessPayload(processDefinitionId,processDefinitionKey,name,businessKey,variables);
        ProcessInstance pi = processRuntime.start(startProcessPayload);
        System.out.println(pi);
    }



    /**查询当前人的个人任务*/
    @WithMockUser(username="admin",roles={"ACTIVITI_USER"})
    @Test
    public void findTask(){

        Page<org.activiti.api.task.model.Task> tasks = taskRuntime.tasks(Pageable.of(0, 1));


    }
    /**查询当前人的个人任务*/
    @Test
    public void completeTask(){
        Map map=new HashMap();
        map.put("day",4);
       taskService.complete("908d9a57-8da5-11e9-ba3f-7ab66d766be2",map);
    }

}
