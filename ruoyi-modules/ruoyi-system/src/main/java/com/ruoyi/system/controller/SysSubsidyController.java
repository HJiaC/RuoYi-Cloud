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
import com.ruoyi.system.domain.SysSubsidy;
import com.ruoyi.system.service.ISysSubsidyService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * @filename: SysMoneyController
 * @author: HXJ
 * @date: 2023/2/19 3:41 PM
 * @version: V1.0
 * @description: 补贴发放请求处理
 */
@RestController
@RequestMapping("/subsidy")
public class SysSubsidyController extends BaseController
{
    @Autowired
    private ISysSubsidyService sysSubsidyService;

    /**
     * 查询补贴发放列表
     */
    @RequiresPermissions("system:subsidy:list")
    @GetMapping("/list")
    public TableDataInfo list(SysSubsidy sysSubsidy)
    {
        startPage();
        List<SysSubsidy> list = sysSubsidyService.selectSysSubsidyList(sysSubsidy);
        return getDataTable(list);
    }

    /**
     * 导出补贴发放列表
     */
    @RequiresPermissions("system:subsidy:export")
    @Log(title = "补贴发放", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSubsidy sysSubsidy)
    {
        List<SysSubsidy> list = sysSubsidyService.selectSysSubsidyList(sysSubsidy);
        ExcelUtil<SysSubsidy> util = new ExcelUtil<SysSubsidy>(SysSubsidy.class);
        util.exportExcel(response, list, "补贴发放数据");
    }

    /**
     * 获取补贴发放详细信息
     */
    @RequiresPermissions("system:subsidy:query")
    @GetMapping(value = "/{subsidyId}")
    public AjaxResult getInfo(@PathVariable("subsidyId") Long subsidyId)
    {
        return success(sysSubsidyService.selectSysSubsidyBySubsidyId(subsidyId));
    }

    /**
     * 新增补贴发放
     */
    @RequiresPermissions("system:subsidy:add")
    @Log(title = "补贴发放", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSubsidy sysSubsidy)
    {
        return toAjax(sysSubsidyService.insertSysSubsidy(sysSubsidy));
    }

    /**
     * 修改补贴发放
     */
    @RequiresPermissions("system:subsidy:edit")
    @Log(title = "补贴发放", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSubsidy sysSubsidy)
    {
        return toAjax(sysSubsidyService.updateSysSubsidy(sysSubsidy));
    }

    /**
     * 删除补贴发放
     */
    @RequiresPermissions("system:subsidy:remove")
    @Log(title = "补贴发放", businessType = BusinessType.DELETE)
    @DeleteMapping("/{subsidyIds}")
    public AjaxResult remove(@PathVariable Long[] subsidyIds)
    {
        return toAjax(sysSubsidyService.deleteSysSubsidyBySubsidyIds(subsidyIds));
    }

}
