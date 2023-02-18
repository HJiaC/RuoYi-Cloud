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
import com.ruoyi.system.domain.SysScheduleRecord;
import com.ruoyi.system.service.ISysScheduleRecordService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * @filename: SysScheduleRecordController
 * @author: HXJ
 * @date: 2023/2/16 3:56 PM
 * @version: V1.0
 * @description: 人员调度记录Controller
 */
@RestController
@RequestMapping("/scheduleRecord")
public class SysScheduleRecordController extends BaseController
{
    @Autowired
    private ISysScheduleRecordService sysScheduleRecordService;

    /**
     * 查询人员调度记录列表
     */
    @RequiresPermissions("system:record:list")
    @GetMapping("/list")
    public TableDataInfo list(SysScheduleRecord sysScheduleRecord)
    {
        startPage();
        List<SysScheduleRecord> list = sysScheduleRecordService.selectSysScheduleRecordList(sysScheduleRecord);
        return getDataTable(list);
    }

    /**
     * 导出人员调度记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "人员调度记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysScheduleRecord sysScheduleRecord)
    {
        List<SysScheduleRecord> list = sysScheduleRecordService.selectSysScheduleRecordList(sysScheduleRecord);
        ExcelUtil<SysScheduleRecord> util = new ExcelUtil<SysScheduleRecord>(SysScheduleRecord.class);
        util.exportExcel(response, list, "人员调度记录数据");
    }

    /**
     * 获取人员调度记录详细信息
     */
    @RequiresPermissions("system:record:query")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Long scheduleId)
    {
        return success(sysScheduleRecordService.selectSysScheduleRecordByScheduleId(scheduleId));
    }

    /**
     * 新增人员调度记录
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "人员调度记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysScheduleRecord sysScheduleRecord)
    {
        return toAjax(sysScheduleRecordService.insertSysScheduleRecord(sysScheduleRecord));
    }

    /**
     * 修改人员调度记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "人员调度记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysScheduleRecord sysScheduleRecord)
    {
        return toAjax(sysScheduleRecordService.updateSysScheduleRecord(sysScheduleRecord));
    }

    /**
     * 删除人员调度记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "人员调度记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable Long[] scheduleIds)
    {
        return toAjax(sysScheduleRecordService.deleteSysScheduleRecordByScheduleIds(scheduleIds));
    }
}
