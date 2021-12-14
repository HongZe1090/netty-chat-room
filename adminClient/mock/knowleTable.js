const Mock = require('mockjs')

const data = Mock.mock({
  'items|30': [{
    id: '@id(5)',
    name: '@cname',
    knowProperty: '@sentence(10, 20)',
    type1: '@sentence(8)',
    type2: '@sentence(8)',
    created_time: '@datetime',
    modify_time: '@datetime'
  }]
})

module.exports = [
  {
    url: '/vue-admin-template/knowleTable/list',
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
