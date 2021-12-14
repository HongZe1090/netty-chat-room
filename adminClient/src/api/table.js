import request from '@/utils/request'

export function getUserList(params) {
  return request({
    url: 'http://localhost:8081/log/getAllLog',
    method: 'get',
    params
  })
}

export function getEvaList(params) {
  return request({
    url: '/vue-admin-template/evaTable/list',
    method: 'get',
    params
  })
}

export function getevaluateList(params) {
  return request({
    url: '/vue-admin-template/evaluateTable/list',
    method: 'get',
    params
  })
}

export function getBaseList(params) {
  return request({
    url: '/vue-admin-template/baseTable/list',
    method: 'get',
    params
  })
}

export function getProList(params) {
  return request({
    url: '/vue-admin-template/professionTable/list',
    method: 'get',
    params
  })
}

export function getJobList(params) {
  return request({
    url: '/vue-admin-template/jobTable/list',
    method: 'get',
    params
  })
}

export function getJobProList(params) {
  return request({
    url: '/vue-admin-template/job-rofessionTable/list',
    method: 'get',
    params
  })
}

export function getSysList(params) {
  return request({
    url: '/vue-admin-template/SystemTable/list',
    method: 'get',
    params
  })
}

export function getPropertyList(params) {
  return request({
    url: '/vue-admin-template/Property/list',
    method: 'get',
    params
  })
}
