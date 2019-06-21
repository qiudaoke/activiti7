package com.shareniu.v2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskAdminRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shareniu.v2.utils.RestMessgae;

/**
 * @author 分享牛
 */
@RestController
public class TaskController {

    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private TaskAdminRuntime taskAdminRuntime;
    //http://127.0.0.1:8080/all-tasks
    @GetMapping("/all-tasks")
    public List<org.activiti.api.task.model.Task> getAllTasks() {
        Page<org.activiti.api.task.model.Task> tasks = taskAdminRuntime.tasks(Pageable.of(0, 10));

        for (org.activiti.api.task.model.Task task : tasks.getContent()) {
           // logger.info("\t> User Task: " + task);
        }

        return tasks.getContent();
    }
    //http://127.0.0.1:8080/complete-task?taskId=6587b943-8e42-11e9-b386-7ab66d766be2

    @GetMapping("/complete-task")
    public String completeTask(@RequestParam(value="taskId") String taskId,Map<String, Object> variables) {
        variables.put("day",3);
        taskRuntime.complete(TaskPayloadBuilder.complete()
            .withTaskId(taskId).withVariables(variables).build());
      //  logger.info(">>> Completed Task: " + taskId);

        return "Completed Task: " + taskId;
    }
}
