package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.dto.SysTaskRecordOperation;
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
import com.ruoyi.system.domain.SysTaskRecord;
import com.ruoyi.system.service.ISysTaskRecordService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * @filename: SysTaskRecordController
 * @author: HXJ
 * @date: 2023/2/16 4:03 PM
 * @version: V1.0
 * @description: 任务记录Controller
 */
@RestController
@RequestMapping("/taskRecord")
public class SysTaskRecordController extends BaseController
{
    @Autowired
    private ISysTaskRecordService sysTaskRecordService;

    /**
     * 查询任务记录列表
     */
    @RequiresPermissions("system:record:list")
    @GetMapping("/list")
    public TableDataInfo list(SysTaskRecord sysTaskRecord)
    {
        startPage();
        List<SysTaskRecord> list = sysTaskRecordService.selectSysTaskRecordList(sysTaskRecord);
        return getDataTable(list);
    }

    /**
     * 导出任务记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "任务记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTaskRecord sysTaskRecord)
    {
        List<SysTaskRecord> list = sysTaskRecordService.selectSysTaskRecordList(sysTaskRecord);
        ExcelUtil<SysTaskRecord> util = new ExcelUtil<SysTaskRecord>(SysTaskRecord.class);
        util.exportExcel(response, list, "任务记录数据");
    }

    /**
     * 获取任务记录详细信息
     */
    @RequiresPermissions("system:record:query")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(sysTaskRecordService.selectSysTaskRecordByRecordId(recordId));
    }

    /**
     * 新增任务记录
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "任务记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTaskRecord sysTaskRecord)
    {
        return toAjax(sysTaskRecordService.insertSysTaskRecord(sysTaskRecord));
    }

    /**
     * 修改任务记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "任务记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTaskRecordOperation sysTaskRecordOperation)
    {
        return toAjax(sysTaskRecordService.updateSysTaskRecord(sysTaskRecordOperation));
    }

    /**
     * 删除任务记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "任务记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(sysTaskRecordService.deleteSysTaskRecordByRecordIds(recordIds));
    }
}
