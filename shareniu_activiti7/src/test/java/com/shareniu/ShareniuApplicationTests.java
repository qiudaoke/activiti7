package com.shareniu;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;
/**
 * @author 分享牛
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareniuApplicationTests {





    @Autowired
    private RuntimeService runtimeService;
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

    @Test
    public void start() {

        String processDefinitionKey ="leave";//每一个流程有对应的一个key这个是某一个流程内固定的写在bpmn内的
        HashMap<String, Object> variables=new HashMap<>();

        ProcessInstance instance = runtimeService
                .startProcessInstanceByKey(processDefinitionKey,variables);

        System.out.println("流程实例ID:"+instance.getId());
        System.out.println("流程定义ID:"+instance.getProcessDefinitionId());
    }


    /**查询当前人的个人任务*/
    @Test
    public void findTask(){
        List<Task> list = taskService.createTaskQuery()//创建任务查询对象
                .list();
        if(list!=null && list.size()>0){
            for(Task task:list){
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间:"+task.getCreateTime());
                System.out.println("任务的办理人:"+task.getAssignee());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
            }
        }
    }
    /**查询当前人的个人任务*/
    @Test
    public void completeTask(){
        Map map=new HashMap();
        map.put("day",4);
       taskService.complete("908d9a57-8da5-11e9-ba3f-7ab66d766be2",map);
    }

}
