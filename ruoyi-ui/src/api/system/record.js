import request from '@/utils/request'

// 查询任务记录列表
export function listRecord(query) {
  return request({
    url: '/system/taskRecord/list',
    method: 'get',
    params: query
  })
}

// 查询任务记录详细
export function getRecord(recordId) {
  return request({
    url: '/system/taskRecord/' + recordId,
    method: 'get'
  })
}

// 新增任务记录
export function addRecord(data) {
  return request({
    url: '/system/taskRecord',
    method: 'post',
    data: data
  })
}

// 修改任务记录
export function updateRecord(data) {
  return request({
    url: '/system/taskRecord',
    method: 'put',
    data: data
  })
}

// 删除任务记录
export function delRecord(recordId) {
  return request({
    url: '/system/taskRecord/' + recordId,
    method: 'delete'
  })
}
