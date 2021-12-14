const Mock = require('mockjs')

const data = Mock.mock({
  'items|30': [{
    id: '@id(10)',
    profId: 'int',
    jobId: 'int',
    proplmpCoef: 'int',
    created_time: '@datetime',
    modify_time: '@datetime'
  }]
})

module.exports = [
  {
    url: '/vue-admin-template/job-rofessionTable/list',
    type: 'get',
    response: config => {
      const items = data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  }
]
