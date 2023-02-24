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
import com.ruoyi.system.domain.SysDispatchRecord;
import com.ruoyi.system.service.ISysDispatchRecordService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * @filename: SysDispatchRecordController
 * @author: HXJ
 * @date: 2023/2/23 1:58 PM
 * @version: V1.0
 * @description: 人员调度记录Controller
 */
@RestController
@RequestMapping("/dispatchRecord")
public class SysDispatchRecordController extends BaseController
{
    @Autowired
    private ISysDispatchRecordService sysDispatchRecordService;

    /**
     * 查询人员调度记录列表
     */
    @RequiresPermissions("system:record:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDispatchRecord sysDispatchRecord)
    {
        startPage();
        List<SysDispatchRecord> list = sysDispatchRecordService.selectSysDispatchRecordList(sysDispatchRecord);
        return getDataTable(list);
    }

    /**
     * 导出人员调度记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "人员调度记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDispatchRecord sysDispatchRecord)
    {
        List<SysDispatchRecord> list = sysDispatchRecordService.selectSysDispatchRecordList(sysDispatchRecord);
        ExcelUtil<SysDispatchRecord> util = new ExcelUtil<SysDispatchRecord>(SysDispatchRecord.class);
        util.exportExcel(response, list, "人员调度记录数据");
    }

    /**
     * 获取人员调度记录详细信息
     */
    @RequiresPermissions("system:record:query")
    @GetMapping(value = "/{dispatchId}")
    public AjaxResult getInfo(@PathVariable("dispatchId") Long dispatchId)
    {
        return success(sysDispatchRecordService.selectSysDispatchRecordByDispatchId(dispatchId));
    }

    /**
     * 新增人员调度记录
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "人员调度记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDispatchRecord sysDispatchRecord)
    {
        return toAjax(sysDispatchRecordService.insertSysDispatchRecord(sysDispatchRecord));
    }

    /**
     * 修改人员调度记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "人员调度记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDispatchRecord sysDispatchRecord)
    {
        return toAjax(sysDispatchRecordService.updateSysDispatchRecord(sysDispatchRecord));
    }

    /**
     * 删除人员调度记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "人员调度记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dispatchIds}")
    public AjaxResult remove(@PathVariable Long[] dispatchIds)
    {
        return toAjax(sysDispatchRecordService.deleteSysDispatchRecordByDispatchIds(dispatchIds));
    }
}
