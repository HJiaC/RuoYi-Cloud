package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysTask;
import com.ruoyi.system.service.ISysTaskService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * @filename: SysTaskController
 * @author: HXJ
 * @date: 2023/2/16 3:20 PM
 * @version: V1.0
 * @description: Web端任务中心请求控制
 */
@RestController
@RequestMapping("/task")
public class SysTaskController extends BaseController
{
    @Autowired
    private ISysTaskService sysTaskService;

    /**
     * 查询Web端任务中心任务列表
     */
    @RequiresPermissions("system:task:list")
    @GetMapping("/list")
    public TableDataInfo list(SysTask sysTask)
    {
        startPage();
        List<SysTask> list = sysTaskService.selectSysTaskList(sysTask);
        return getDataTable(list);
    }

    /**
     * 导出Web端任务中心任务列表
     */
    @RequiresPermissions("system:task:export")
    @Log(title = "Web端任务中心任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTask sysTask)
    {
        List<SysTask> list = sysTaskService.selectSysTaskList(sysTask);
        ExcelUtil<SysTask> util = new ExcelUtil<SysTask>(SysTask.class);
        util.exportExcel(response, list, "Web端任务中心任务数据");
    }

    /**
     * 获取Web端任务中心任务详细信息
     */
    @RequiresPermissions("system:task:query")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(sysTaskService.selectSysTaskByTaskId(taskId));
    }

    /**
     * 新增Web端任务中心任务
     */
    @RequiresPermissions("system:task:add")
    @Log(title = "Web端任务中心任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTask sysTask)
    {
        return toAjax(sysTaskService.insertSysTask(sysTask));
    }

    /**
     * 修改Web端任务中心任务
     */
    @RequiresPermissions("system:task:edit")
    @Log(title = "Web端任务中心任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTask sysTask)
    {
        return toAjax(sysTaskService.updateSysTask(sysTask));
    }

    /**
     * 删除Web端任务中心任务
     */
    @RequiresPermissions("system:task:remove")
    @Log(title = "Web端任务中心任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(sysTaskService.deleteSysTaskByTaskIds(taskIds));
    }
}