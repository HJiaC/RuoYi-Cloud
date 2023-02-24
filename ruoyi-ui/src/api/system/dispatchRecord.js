import request from '@/utils/request'

// 查询人员调度记录列表
export function listDispatchRecord(query) {
  return request({
    url: '/system/dispatchRecord/list',
    method: 'get',
    params: query
  })
}

// 查询人员调度记录详细
export function getRecord(dispatchId) {
  return request({
    url: '/system/dispatchRecord/' + dispatchId,
    method: 'get'
  })
}

// 新增人员调度记录
export function addDispatchRecord(data) {
  return request({
    url: '/system/dispatchRecord',
    method: 'post',
    data: data
  })
}

// 修改人员调度记录
export function updateRecord(data) {
  return request({
    url: '/system/dispatchRecord',
    method: 'put',
    data: data
  })
}

// 删除人员调度记录
export function delRecord(dispatchId) {
  return request({
    url: '/system/dispatchRecord/' + dispatchId,
    method: 'delete'
  })
}
