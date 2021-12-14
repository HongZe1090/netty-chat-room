const Mock = require('mockjs')

const data = Mock.mock({
  'items|30': [{
    id: '@id(10)',
    name: '@cname',
    propertiesJSON: '@sentence(10, 20)',
    created_time: '@datetime',
    modify_time: '@datetime'
  }]
})

module.exports = [
  {
    url: '/vue-admin-template/professionTable/list',
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
