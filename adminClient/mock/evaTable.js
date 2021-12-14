const Mock = require('mockjs')

const data = Mock.mock({
  'items|30': [{
    id: '@id(10)',
    personId: '@id(10)',
    evalId: '@id(10)',
    // title: '@sentence(10, 20)',
    // 'status|1': ['published', 'draft', 'deleted'],
    'completeTime|1-30': 1,
    propertiesJSON: '@sentence(10, 20)',
    'scoreJSON|1-100': 1,
    created_time: '@datetime',
    modify_time: '@datetime'
  }]
})

module.exports = [
  {
    url: '/vue-admin-template/evaTable/list',
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
